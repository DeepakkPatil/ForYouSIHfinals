package com.aaks32173.sih2022new;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;


public class AddpostActivity extends AppCompatActivity {
    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;
    private Uri imageUri = null;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button postBtn;
    private StorageReference mStorage;
    private DatabaseReference mDatabase, database;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(mCurrentUser.getEmail())).child("Post");
        database = FirebaseDatabase.getInstance().getReference().child("Post");

        mSelectImage = (ImageButton) findViewById(R.id.imageBtn);
        mPostDesc = (EditText) findViewById(R.id.textDesc);
        mPostTitle = (EditText) findViewById(R.id.textTitle);
        postBtn = (Button) findViewById(R.id.postButton);
        mProgress = new ProgressDialog(this);

//
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startPosting();
                 Intent i= new Intent(getApplicationContext(),Showpost.class);
                 finish();
                 startActivity(i);

            }
        } );
    }

    private void startPosting() {


        mProgress.setMessage("Posting...");
        mProgress.show();

        String title_val = mPostTitle.getText().toString().trim();
        String desc_val = mPostDesc.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && imageUri != null) {
             StorageReference filepath = mStorage.child("BlogImages").child(encodeUserEmail(mCurrentUser.getEmail())+title_val);

            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddpostActivity.this,"Posted",Toast.LENGTH_SHORT).show();
                    Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                    DatabaseReference newPost = mDatabase.child(title_val);
                    DatabaseReference newPost1 = database.child(title_val);

                    newPost.child("title").setValue(title_val);
                            newPost.child("desc").setValue(desc_val);
                            newPost.child("likes").setValue("0") ;
                            newPost.child("image").setValue(downloadUri.toString());
                            newPost.child("email").setValue(mCurrentUser.getEmail());

                    newPost1.child("title").setValue(title_val);
                    newPost1.child("desc").setValue(desc_val);
                    newPost1.child("likes").setValue("0") ;
                    newPost1.child("image").setValue(downloadUri.toString());
                    newPost1.child("email").setValue(mCurrentUser.getEmail());
                           mProgress.dismiss();

           }
            });
        }
        else {
            Toast.makeText(this,"Fill all Details",Toast.LENGTH_SHORT).show();
        }
           }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
                imageUri = data.getData();
                mSelectImage.setImageURI(imageUri);
            }
        }

    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}
