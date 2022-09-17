package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class gtbtPanelFourthFifth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtbt_panel_fourth_fifth);
        Button btn1 = findViewById(R.id.opencomic);
        Button btn2 = findViewById(R.id.openvideos);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityviedos();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitycomics();
            }
        });
    }

    private void activityviedos(){
        Intent intent = new Intent(this,goodBadtouch.class);
        startActivity(intent);

    }
    private void activitycomics(){
        Intent intent = new Intent(this,good_bad_touch_comic.class);
        startActivity(intent);

    }
}