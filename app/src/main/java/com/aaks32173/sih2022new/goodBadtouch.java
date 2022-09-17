package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class goodBadtouch extends AppCompatActivity {
    String videoUrl5 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/good_bad_touch%2Fgtbt6.mp4?alt=media&token=11340670-9d78-4c57-b821-29bb2b99d1cb";
    String videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/good_bad_touch%2Fgtbt5.mp4?alt=media&token=65f66740-3cb3-4d8a-a0e7-4b5d24d1d9b1";
    String videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/good_bad_touch%2Fgtbt4.mp4?alt=media&token=3d504041-c8c0-4080-90fc-e012706b5033";
    String videoUrl = "https://firebasestorage.googleapis." +
            "com/v0/b/sih2022-15182.appspot.com/o/good_bad_touch%2Fgtbt1.mp4?al" +
            "t=media&token=6e71a271-d701-4a13-b31a-15e7b5ef7581";
    String videoUrl2 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/good_bad_touch%2Fgtbt2.mp4?alt=media&token=6b3a72cd-d245-46cb-b690-6ed976734cb5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_badtouch);
        ImageButton btn1 = findViewById(R.id.btn1);
        ImageButton btn2 = findViewById(R.id.btn2);
        ImageButton btn3 = findViewById(R.id.btn3);
        ImageButton btn4 = findViewById(R.id.btn4);
        ImageButton btn5 = findViewById(R.id.btn5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vedioPlayer(videoUrl);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vedioPlayer(videoUrl2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vedioPlayer(videoUrl3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vedioPlayer(videoUrl4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vedioPlayer(videoUrl5);
            }
        });
    }
    private void vedioPlayer(String url){
        Intent intent = new Intent(this,vedioPlay.class);
        intent.putExtra("url", url);
        startActivity(intent);

    }
    }
