package lk.paradox.kekayan.fabfit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditprofileActivity extends AppCompatActivity {
    public static final int RC_PHOTO_PICKER = 1;
    //Declaration of UI elements
    private EditText mNameField, mAgeField, mWeightField,mHeightField;
    //Image View
    //String values Of the user values
    private String userID;
    private String mName;
    private String mProfileImageUrl;
    //Url of the Resuts
    private Uri resultUri;
    private com.mikhaellopez.circularimageview.CircularImageView myProfileImage;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        //Declaration of the view
        mNameField = findViewById(R.id.name);
        mAgeField = findViewById(R.id.age);
        mWeightField = findViewById(R.id.weight);
        mHeightField = findViewById(R.id.height);
        myProfileImage = findViewById(R.id.profileImage);

        Button mBack = findViewById(R.id.back);
        Button mConfirm = findViewById(R.id.confirm);


        //firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        Log.d("KEY", userID);
        //Database Reference
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        getUserInfo();
        myProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RC_PHOTO_PICKER);
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EditprofileActivity.this, MainActivity.class));
                finish();
                //return;
            }
        });
    }

    private void getUserInfo() {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("name") != null) {
                        mName = map.get("name").toString();
                        mNameField.setText(mName);
                    }
                    if (map.get("weight") != null) {
                        mWeightField.setText(map.get("weight").toString());
                    }
                    if (map.get("height") != null) {
                        mHeightField.setText(map.get("height").toString());
                    }
                    if (map.get("age") != null) {
                        mAgeField.setText(map.get("age").toString());
                    }
                    if (map.get("profileImageUrl") != null) {
                        mProfileImageUrl = map.get("profileImageUrl").toString();
                        Glide.with(getApplicationContext()).load(mProfileImageUrl).into(myProfileImage);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(listener);
    }

    private void saveUserInformation() {

        mName = mNameField.getText().toString();
        String mAge = mAgeField.getText().toString();
        String mWeight = mWeightField.getText().toString();
        String mHeight = mHeightField.getText().toString();
        Map userInfo = new HashMap();
        userInfo.put("name", mName);
        userInfo.put("age", mAge);
        userInfo.put("weight", mWeight);
        userInfo.put("height", mHeight);
        mDatabase.updateChildren(userInfo);
        if (resultUri != null) {
            StorageReference filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(userID);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filePath.putBytes(data);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("KEY", e.toString());
                    //finish();
                    return;
                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    Map newImage = new HashMap();
                    newImage.put("profileImageUrl", downloadUrl.toString());
                    mDatabase.updateChildren(newImage);
                    Log.i("KEY", "upload");
                    //finish();
                    return;
                }
            });
        } else {
            Log.i("KEY", "update eror");
            //finish();
        }

    }

    //Getting the image using the request code and set the uri to the
    // Photo uri and set the image to profile
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_PHOTO_PICKER) {
                final Uri imageUri = data.getData();
                resultUri = imageUri;
                myProfileImage.setImageURI(resultUri);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (FirebaseDatabase.getInstance() != null) {
            FirebaseDatabase.getInstance().goOnline();
        }
    }

    @Override
    public void onPause() {

        super.onPause();

        if (FirebaseDatabase.getInstance() != null) {
            FirebaseDatabase.getInstance().goOffline();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (FirebaseDatabase.getInstance() != null) {
            FirebaseDatabase.getInstance().goOffline();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (FirebaseDatabase.getInstance() != null) {
            FirebaseDatabase.getInstance().goOffline();
        }
    }
}
