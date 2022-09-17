package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MensturalFourthFifthGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstural_fourth_fifth_group);
        LinearLayout btn1 = findViewById(R.id.btn1);
        LinearLayout btn2 = findViewById(R.id.btn2);
        LinearLayout btn3 = findViewById(R.id.btn3);
        LinearLayout btn4 = findViewById(R.id.btn4);
        LinearLayout btn5 = findViewById(R.id.btn5);
        LinearLayout btn6 = findViewById(R.id.btn6);
        LinearLayout btn7 = findViewById(R.id.btn7);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(9).mp4?alt=media&token=36063a6c-4b94-4dae-bc21-0d5eb5fc4bac";
                vedioPlayer(url);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(10).mp4?alt=media&token=9ecbc220-a0a7-427b-b126-7f4ecaa7a15f";
                vedioPlayer(url);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(11).mp4?alt=media&token=34c1ea0a-24e8-4e34-889c-2a5af3d40b97";
                vedioPlayer(url);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(12).mp4?alt=media&token=24e3d13b-3c14-4790-9e84-3c3d608a142b";
                vedioPlayer(url);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(13).mp4?alt=media&token=61701341-3818-441c-affa-2b67bb3e5710";
                vedioPlayer(url);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(14).mp4?alt=media&token=d306f168-3926-40f8-81c8-77629406cbe7";
                vedioPlayer(url);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/menstrual3rd-5th%2Fvideoplayback%20(15).mp4?alt=media&token=4a52046d-2233-4fc1-a313-6daa8cb81a89";
                vedioPlayer(url);
            }
        });
    }
    private void vedioPlayer(String url){
        Intent intent = new Intent(this,vedioPlay.class);
        intent.putExtra("url", url);
        intent.putExtra("nxt", "MensturalFourthFifthGroup");
        startActivity(intent);

    }
}