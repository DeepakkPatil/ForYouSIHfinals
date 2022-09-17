package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class meditation extends AppCompatActivity {
    String videoUrl;
    String videoUrl2;
    String videoUrl3;
    String videoUrl4;
    String videoUrl5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        LinearLayout btn1 = findViewById(R.id.meditation1);
        LinearLayout btn2 = findViewById(R.id.meditation2);
        LinearLayout btn3 = findViewById(R.id.meditation3);
        LinearLayout btn4 = findViewById(R.id.meditation4);
        LinearLayout btn5 = findViewById(R.id.meditation5);
        String group = getIntent().getExtras().getString("group");
        String age = getIntent().getExtras().getString("age");
        if(group.equals("SixthEight"))
        {
            videoUrl="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fmeditation%2Fvideoplayback%20(1).mp4?alt=media&token=ef6472ac-17f0-4177-9bbf-7be0f80df35b";
            videoUrl2="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fmeditation%2Fvideoplayback%20(2).mp4?alt=media&token=4b1ae455-f1cc-4f1b-87b2-cf124d1cdee9";
            videoUrl3="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fmeditation%2Fvideoplayback%20(3).mp4?alt=media&token=c55b2ceb-4124-48c2-9ad7-2a921527140e";
            videoUrl4="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fmeditation%2Fvideoplayback%20(4).mp4?alt=media&token=1d160094-cd50-4c83-bb58-8422f9a40a7b";
            videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fmeditation%2Fvideoplayback.mp4?alt=media&token=9ef5a8f0-e871-4cc5-a1ac-217c2e9bc304";
        }
        else
        {
            videoUrl="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstretching%203.mp4?alt=media&token=75496b2b-e45e-4bad-b4ee-450157f2d090";
            videoUrl2="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstretching%204.mp4?alt=media&token=57c2fe46-663c-49a9-a758-45c4c981c89f";
            videoUrl3="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstrtching%20exercises%202_Trim.mp4?alt=media&token=625e8cd6-a419-43da-9a82-305e8b037a68";
            videoUrl4="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fstretchings%2Fstrteching%20exercises1.mp4?alt=media&token=0c1a2832-3759-4751-8f3a-d8cf3a78d7b2";
            videoUrl5="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fmeditation%2Fvideoplayback.mp4?alt=media&token=9ef5a8f0-e871-4cc5-a1ac-217c2e9bc304";
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
        startActivity(intent);}
    }
