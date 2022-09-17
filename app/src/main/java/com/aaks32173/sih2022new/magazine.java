package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class magazine extends AppCompatActivity {
    private ListView mListView;
    ProgressDialog progressDialog;
    StorageReference storagereference;

    //private ListView newDates;
    private ArrayList<ImageView> pages = new ArrayList<ImageView>();
   // private ArrayList<String> newDatess = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);
       // mListView = (ListView) findViewById(R.id.pageimg);

           ImageView page1 = findViewById(R.id.page1);
            fetchMag(1, page1);
        ImageView page2 = findViewById(R.id.page2);
        fetchMag(2, page2);
        ImageView page3 = findViewById(R.id.page3);
        fetchMag(3, page3);
        ImageView page4 = findViewById(R.id.page4);
        fetchMag(4, page4);
        ImageView page5 = findViewById(R.id.page5);
        fetchMag(5, page5);
        ImageView page6 = findViewById(R.id.page6);
        fetchMag(6, page6);
        ImageView page7 = findViewById(R.id.page7);
        fetchMag(7, page7);
        ImageView page8 = findViewById(R.id.page8);
        fetchMag(8, page8);
        ImageView page9 = findViewById(R.id.page9);
        fetchMag(9, page9);
        ImageView page10 = findViewById(R.id.page10);
        fetchMag(10, page10);
        ImageView page11 = findViewById(R.id.page11);
        fetchMag(11, page11);
        ImageView page12 = findViewById(R.id.page12);
        fetchMag(12, page12);
        ImageView page13 = findViewById(R.id.page13);
        fetchMag(13, page13);
        ImageView page14 = findViewById(R.id.page14);
        fetchMag(14, page14);

    }

    void fetchMag(int id, ImageView page)
    {
        //ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, R.layout.magazinepages, pages);

        storagereference = FirebaseStorage.getInstance().getReference("magazine/"+id+".jpeg");
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