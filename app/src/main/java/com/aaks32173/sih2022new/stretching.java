package com.aaks32173.sih2022new;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class stretching extends AppCompatActivity {
    String videoUrl;
    String videoUrl2;
    String videoUrl3;
    String videoUrl4;
    String videoUrl5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretching);
        LinearLayout btn1 = findViewById(R.id.stretch1);
        LinearLayout btn2 = findViewById(R.id.stretch2);
        LinearLayout btn3 = findViewById(R.id.stretch3);
        LinearLayout btn4 = findViewById(R.id.stretch4);
        LinearLayout btn5 = findViewById(R.id.stretch5);
        String group = getIntent().getExtras().getString("group");
        if(group.equals("SixthEight"))
        {
            videoUrl="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fexercise%2Fvideoplayback%20(1).mp4?alt=media&token=579b48f5-ba9a-4598-9dcc-7983ab216893";
            videoUrl2="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fexercise%2Fvideoplayback%20(2).mp4?alt=media&token=1d14f5a1-ab58-4ade-b23f-ce966bf1ee2d";
            videoUrl3="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fexercise%2Fvideoplayback%20(3).mp4?alt=media&token=a4b71586-53ee-4c97-a771-2796c3f54a71";
            videoUrl4="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fexercise%2Fvideoplayback%20(4).mp4?alt=media&token=106f4d5e-3610-4f63-8899-da0e4dcb9364";
            videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fexercise%2Fvideoplayback%20(5).mp4?alt=media&token=aee20862-a48f-4be1-a4db-1ede902579b4";
        }
        else
        {
            videoUrl="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstretching%203.mp4?alt=media&token=75496b2b-e45e-4bad-b4ee-450157f2d090";
            videoUrl2="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstretching%204.mp4?alt=media&token=57c2fe46-663c-49a9-a758-45c4c981c89f";
            videoUrl3="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstrtching%20exercises%202_Trim.mp4?alt=media&token=625e8cd6-a419-43da-9a82-305e8b037a68";
            videoUrl4="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstrteching%20exercises1.mp4?alt=media&token=0c1a2832-3759-4751-8f3a-d8cf3a78d7b2";
            videoUrl5="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstretching%203.mp4?alt=media&token=75496b2b-e45e-4bad-b4ee-450157f2d090";
        }
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
        intent.putExtra("nxt", "stretching");
        startActivity(intent);}}