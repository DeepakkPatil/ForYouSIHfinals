package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class musicSleep extends AppCompatActivity {
    StorageReference storagereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_sleep);
        ImageView page1 = findViewById(R.id.sleepimg1);
        fetchthumbnails(1, page1);
        ImageView page2 = findViewById(R.id.sleepimg2);
        fetchthumbnails(2, page2);
        ImageView page3 = findViewById(R.id.sleepimg3);
        fetchthumbnails(3, page3);
        ImageView page4 = findViewById(R.id.sleepimg4);
        fetchthumbnails(4, page4);
        ImageView page5 = findViewById(R.id.sleepimg5);
        fetchthumbnails(5, page5);
        ImageView page6 = findViewById(R.id.sleepimg6);
        fetchthumbnails(6, page6);

    }

    void fetchthumbnails(int id, ImageView page) {
        //ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, R.layout.magazinepages, pages);

        storagereference = FirebaseStorage.getInstance().getReference("sleepMusic/" + "sleepimg"+id + ".jpg");
        try {
            File localfile = File.createTempFile("tempfile", ".jpg");
            storagereference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    page.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}