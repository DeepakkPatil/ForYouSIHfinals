package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class doanddont extends AppCompatActivity {
    StorageReference storagereference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanddont);
        String dos = "dos/";
        String dont = "donts/";
        ImageView page1 = findViewById(R.id.doimg1);
        getImages(1, page1, dos);
        ImageView page2 = findViewById(R.id.doimg2);
        getImages(2, page2, dos);
        ImageView page3 = findViewById(R.id.doimg3);
        getImages(3, page3, dos);
        ImageView page4 = findViewById(R.id.doimg4);
        getImages(4, page4, dos);
        ImageView page5 = findViewById(R.id.doimg5);
        getImages(5, page5, dos);
        ImageView dont1 = findViewById(R.id.dontimg1);
        getImages(1, dont1, dont);
        ImageView dont2 = findViewById(R.id.dontimg2);
        getImages(2, dont2, dont);
        ImageView dont3 = findViewById(R.id.dontimg3);
        getImages(3, dont3, dont);
        ImageView dont4 = findViewById(R.id.dontimg4);
        getImages(4, dont4, dont);
        ImageView dont5 = findViewById(R.id.dontimg5);
        getImages(5, dont5, dont);
    }

    void getImages(int id, ImageView page, String s)
    {
        storagereference = FirebaseStorage.getInstance().getReference(s+id+".jpeg");
        try {
            File localfile = File.createTempFile("tempfile", ".jpeg");
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