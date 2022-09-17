package com.aaks32173.sih2022new.ui;

import static java.sql.DriverManager.println;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaks32173.sih2022new.R;
import com.aaks32173.sih2022new.Showpost;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.postViewHolder> {

    Context context;
    ArrayList<Post> list;
    StorageReference storagereference;
    public String dbref2, dbref1, email;


    public PostAdapter(Context context, ArrayList<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_showpost_cardview, parent, false);
//        Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();

        return new postViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {


        Post post = list.get(position);
        holder.title.setText(post.getTitle());
        holder.tvlike.setText(post.getLikes());
//        holder.email_id.setText(post.getEmail());
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    int likes = Integer.parseInt(post.getLikes());
                    likes++;
//                    Toast.makeText(buttonView.getContext(), Integer.toString(likes), Toast.LENGTH_SHORT).show();
//                    holder.tvlike.setText(Integer.toString(likes));
//                    String a=Integer.toString(likes);
                    post.setLikes(Integer.toString(likes));
                    holder.tvlike.setText(post.getLikes());
                    int finalLikes = likes;
                    FirebaseDatabase.getInstance().getReference().child("Post").child(post.getTitle()).child("likes").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().setValue(Integer.toString(finalLikes));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Toast.makeText(buttonView.getContext(), "Liked", Toast.LENGTH_SHORT).show();


                } else {
                    int likes = Integer.parseInt(post.getLikes());
                    if (likes > 0)
                        likes--;
                    post.setLikes(Integer.toString(likes));
                    holder.tvlike.setText(post.getLikes());
                    int finalLikes = likes;
                    FirebaseDatabase.getInstance().getReference().child("Post").child(post.getTitle()).child("likes").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().setValue(Integer.toString(finalLikes));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Toast.makeText(buttonView.getContext(), "Disliked", Toast.LENGTH_SHORT).show();

                }
            }
        });


        FirebaseDatabase.getInstance().getReference().child("Post").child(post.getTitle()).child("email")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        email = dataSnapshot.getValue().toString();
                        holder.email_id.setText(email);
                        dbref2 = encodeUserEmail(email);
                        dbref1 = dbref2 + post.getTitle();
                        holder.desc.setText(post.getDesc());
                        putimg(dbref1, holder.image);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void putimg(String pos, ImageView page) {
        storagereference = FirebaseStorage.getInstance().getReference("BlogImages/" + pos);
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

    public static class postViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, email_id;
        ImageView image;
        ToggleButton toggleButton;
        TextView tvlike;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.post_title);
            desc = itemView.findViewById(R.id.post_desc);
            image = itemView.findViewById(R.id.post_img);
            toggleButton = itemView.findViewById(R.id.tgbutton);
            tvlike = itemView.findViewById(R.id.tvlike);
            email_id = itemView.findViewById(R.id.post_email_id);
        }
    }

    private String encodeUserEmail(String email) {
        return email.replace(".", ",");
    }
}