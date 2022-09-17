package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class refresh2 extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser Currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh2);

        mAuth = FirebaseAuth.getInstance();
        Currentuser = mAuth.getCurrentUser();


        Intent i=new Intent(getApplicationContext(),wetime.class);
        i.putExtra("email", encodeUserEmail(Currentuser.getEmail().toString()));
        finish();
        startActivity(i);
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}