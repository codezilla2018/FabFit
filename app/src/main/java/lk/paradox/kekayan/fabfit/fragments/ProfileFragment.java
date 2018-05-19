package lk.paradox.kekayan.fabfit.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import lk.paradox.kekayan.fabfit.EditprofileActivity;
import lk.paradox.kekayan.fabfit.R;
import lk.paradox.kekayan.fabfit.SplashActivity;

public class ProfileFragment extends Fragment {
    public static final int RC_PHOTO_PICKER = 1;
    Button logout, editinfo;
    Intent intent;
    private Activity mActivity;
    //Declaration of UI elements
    private TextView mNameField;
    private Button mBack, mConfirm;
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
    private TextView mEmailField;
    //Image View
    //String values Of the user values
    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        logout = view.findViewById(R.id.signoutbtn);
        mNameField = view.findViewById(R.id.nametxt);
        mEmailField = view.findViewById(R.id.emailtxt);
        mProfileImage = view.
                findViewById(R.id.imagedp);
        editinfo = view.findViewById(R.id.editinfobtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), EditprofileActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(getActivity(), SplashActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        //Database Reference
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        mEmailField.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        getUserInfo();
    }
    private void getUserInfo() {
        if (getActivity() == null) {
            return;
        }
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
                        Glide.with(getContext()).load(mProfileImageUrl).into(mProfileImage);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mCustomerDatabase.addValueEventListener(listener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}