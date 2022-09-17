package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDiet extends AppCompatActivity {

    DatabaseReference dbref;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    RadioGroup radioGroup;
    RadioButton rb1,rb2;
    String diet;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_diet);


        next = findViewById(R.id.nextUserDiet);
//        Toast.makeText(this, "1",
//                Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        radioGroup=findViewById(R.id.radioGroup);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= mCurrentUser.getEmail();
                String final_email = encodeUserEmail(email);
//                Toast.makeText(view.getContext(), "2", Toast.LENGTH_SHORT).show();
                dbref = FirebaseDatabase.getInstance().getReference("UserInfo").child(final_email);
                dbref.child("userDiet").setValue(diet);
                Intent i = new Intent(UserDiet.this,Details.class);
                finish();
                startActivity(i);
            }
        });

    }

    public void rbclick(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        rb1 = findViewById(radioId);
        diet =rb1.getText().toString();
//        Toast.makeText(this, diet,
//                Toast.LENGTH_SHORT).show();
    }


    private String encodeUserEmail(String email) {
        return email.replace(".", ",");
    }
}