package com.aaks32173.sih2022new;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aaks32173.sih2022new.ui.Post;
import com.aaks32173.sih2022new.ui.PostAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Showpost extends AppCompatActivity {
     RecyclerView recyclerView;
     StorageReference storageReference;
     DatabaseReference database;
     PostAdapter postAdapter;
     ArrayList<Post> list;
     Button addpost;
     Post post;
     Button refreshbtn ;
     ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpost);

        refreshbtn=findViewById(R.id.refreshbtn) ;
        toggleButton = findViewById(R.id.tgbutton);
        addpost = findViewById(R.id.addpostbtn);
        recyclerView = findViewById(R.id.post_list);
        database = FirebaseDatabase.getInstance().getReference("Post");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        storageReference = FirebaseStorage.getInstance().getReference();
//        Toast.makeText(Showpost.this, "4", Toast.LENGTH_SHORT).show();

        list = new ArrayList<Post>();
        postAdapter = new PostAdapter(this, list);
//        Toast.makeText(Showpost.this, "5", Toast.LENGTH_SHORT).show();

        recyclerView.setAdapter(postAdapter);
//        Toast.makeText(Showpost.this, "6", Toast.LENGTH_SHORT).show();

        refreshbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),refresh.class);
                finish();
                startActivity(i);

            }
        });
        addpost.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {
                                           Intent i=new Intent(getApplicationContext(),AddpostActivity.class);
                                           startActivity(i);
                                       }
                                   });

        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                Intent i= new Intent(getApplicationContext(),Showpost.class);
//                finish();
//                startActivity(i);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Toast.makeText(Showpost.this, "", Toast.LENGTH_SHORT).show();
                    post = dataSnapshot.getValue(Post.class);
                    list.add(post);
//                    Toast.makeText(Showpost.this, "7", Toast.LENGTH_SHORT).show();
                }
//                listener.onDataReceived(feededProducts);




                postAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void onDefault(View view)
    {

    }
}