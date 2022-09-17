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

public class diet extends AppCompatActivity {
    StorageReference storagereference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        ImageView food1 = findViewById(R.id.imagefood1);
        getfoodimg(1, food1);
        ImageView food2 = findViewById(R.id.imagefood2);
        getfoodimg(2, food2);
        ImageView food3 = findViewById(R.id.imagefood3);
        getfoodimg(3, food3);
        ImageView food4 = findViewById(R.id.imagefood4);
        getfoodimg(4, food4);
        ImageView food5 = findViewById(R.id.imagefood5);
        getfoodimg(5, food5);

    }
    void getfoodimg(int id, ImageView page)
    {
        //ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, R.layout.magazinepages, pages);

        storagereference = FirebaseStorage.getInstance().getReference("diet/"+id+".jpg");
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