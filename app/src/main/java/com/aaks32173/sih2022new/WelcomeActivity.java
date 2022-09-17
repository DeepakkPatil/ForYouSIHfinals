package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        final Button loginbtn_home = findViewById(R.id.loginbtn_home);
        loginbtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                movetologin();
            }
        });

        final Button registerbtn_home = findViewById(R.id.registerbtn_home);
        registerbtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoregister();
            }
        });

    }

    private void movetologin(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
    private void movetoregister(){
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }
}