package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Objects;

public class vedioPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_play);
        VideoView videoView = findViewById(R.id.videoView);

        String videoUrl = getIntent().getExtras().getString("url");
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
//        prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                prevActivity(nxt);
//            }
//        });
    }
    private void prevActivity(String nxt){
        if(Objects.equals(nxt, "yoga")) {
            Intent intent = new Intent(this, yoga.class);
            startActivity(intent);
        }
        else if(Objects.equals(nxt, "goodBadtouch"))
        {
            Intent intent = new Intent(this, goodBadtouch.class);
            startActivity(intent);
        }
        else if(Objects.equals(nxt, "workout"))
        {
            Intent intent = new Intent(this, workout.class);
            startActivity(intent);
        }
        else if(Objects.equals(nxt, "meditation"))
        {
            Intent intent = new Intent(this, meditation.class);
            startActivity(intent);
        }
        else if(Objects.equals(nxt, "stretching"))
        {
            Intent intent = new Intent(this, stretching.class);
            startActivity(intent);
        }
        else if(Objects.equals(nxt, "MensturalFourthFifthGroup"))
        {
            Intent intent = new Intent(this, MensturalFourthFifthGroup.class);
            startActivity(intent);
        }

    }
}