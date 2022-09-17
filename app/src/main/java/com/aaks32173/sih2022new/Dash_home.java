package com.aaks32173.sih2022new;
import com.aaks32173.sih2022new.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Dash_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_home);
       ImageButton button=(ImageButton)findViewById(R.id.imageButton11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoMenstralCycle();
            }
        });
        ImageButton button6=(ImageButton)findViewById(R.id.imageButton12);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoGtBt();
            }
        });
        ImageButton button5=(ImageButton)findViewById(R.id.imageButton5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoYoga();
            }
        });
        ImageButton button2=(ImageButton)findViewById(R.id.imageButton10);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoAddPostActivity();
            }
        });
        ImageButton button7=(ImageButton)findViewById(R.id.imageButton7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoSleep();
            }
        });
        ImageButton button8=(ImageButton)findViewById(R.id.imageButton8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoBot();
            }
        });
    }

    private void movetoBot() {
        Intent intent = new Intent(Dash_home.this, com.aaks32173.sih2022new.ui.MainActivity.class);
        startActivity(intent);
    }

    private void movetoAddPostActivity() {

        Intent intent = new Intent(Dash_home.this, Showpost.class);
        startActivity(intent);
    }

    private void movetoMenstralCycle() {

        Intent intent = new Intent(Dash_home.this, MainActivity.class);
        startActivity(intent);
    }
    private void movetoGtBt() {

        Intent intent = new Intent(Dash_home.this, good_bad_touch_panel.class);
        startActivity(intent);
    }
    private void movetoYoga() {

        Intent intent = new Intent(Dash_home.this, flex_time.class);
        startActivity(intent);
    }
    private void movetoSleep() {

        Intent intent = new Intent(Dash_home.this, getSleepDetails.class);
        startActivity(intent);
    }

}