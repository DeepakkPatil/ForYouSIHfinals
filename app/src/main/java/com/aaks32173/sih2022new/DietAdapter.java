package com.aaks32173.sih2022new;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.dietViewHolder> {

    StorageReference storagereference;
    Context context;
    ArrayList<diet_class> list;
    String title1;

    public DietAdapter(Context context, ArrayList<diet_class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public dietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.diet_cardview, parent, false);
        return new dietViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dietViewHolder holder, int position) {

        diet_class diet=list.get(position);
        holder.title.setText(diet.getTitle());
        holder.desc.setText(diet.getDesc());
        title1= diet.getTitle();
        putimg(title1, holder.image);
//        FirebaseDatabase.getInstance().getReference().child("");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void putimg(String pos, ImageView page) {
        storagereference = FirebaseStorage.getInstance().getReference("Food" ).child(pos+".jpg");

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

    public static class dietViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        ImageView image;

        public dietViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.diet_title);
            desc = itemView.findViewById(R.id.diet_desc);
            image = itemView.findViewById(R.id.diet_image);
        }
    }
}
