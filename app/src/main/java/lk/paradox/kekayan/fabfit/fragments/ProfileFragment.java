package lk.paradox.kekayan.fabfit.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import lk.paradox.kekayan.fabfit.R;

public class ProfileFragment extends Fragment {
    //String values Of the user values
    private String userID;
    private String mName;
    private String mPhone;
    private String mProfileImageUrl;
    private String mEmail;

    //Url of the Resuts
    private Uri resultUri;
    private com.mikhaellopez.circularimageview.CircularImageView mProfileImage;

    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;
    private ValueEventListener listener;

    private TextView mNameField;
    private TextView mEmailField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mNameField = view.findViewById(R.id.nametxt);
        mEmailField = view.findViewById(R.id.emailtxt);
        mProfileImage = view.findViewById(R.id.imagedp);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmailField.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        Log.d("KEY", userID);
        //Database Reference
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        getUserInfo();
    }

    private void getUserInfo() {
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("name") != null) {
                        mName = map.get("name").toString();
                        mNameField.setText(mName);
                    }
                    if (map.get("phone") != null) {
                        mPhone = map.get("phone").toString();
                    }
                    if (map.get("profileImageUrl") != null) {
                        mProfileImageUrl = map.get("profileImageUrl").toString();
                        Glide.with(getActivity()).load(mProfileImageUrl).into(mProfileImage);
                        Log.d("profileurl", mProfileImageUrl);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mCustomerDatabase.addValueEventListener(listener);
    }


}