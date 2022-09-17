package com.aaks32173.sih2022new;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Interest extends AppCompatActivity implements QuantityListener {

    RecyclerView recyler_view ;
    QuantityAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>() ;
    FirebaseAuth mAuth;
    String age;
    FirebaseUser Currentuser;
    FirebaseDatabase firebaseDatabase;
    FirebaseDatabase firebaseDatabase2;
    DatabaseReference ref;
    DatabaseReference ref2;
    int exercisepointer=0;
    int musicpodcastpointer=0;
    int relaxinpointer=0;
    int wetimePointer=0;
    String update="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Currentuser = mAuth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("UserIntrest");
        ref2 =FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail()));
        age= getIntent().getExtras().getString("age");
        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        ImageView img3 = findViewById(R.id.img3);
        ImageView img4 = findViewById(R.id.img4);
        ImageView img5 = findViewById(R.id.img5);
        ImageView img6 = findViewById(R.id.img6);
        ImageView img7 = findViewById(R.id.img7);
        ImageView img8 = findViewById(R.id.img8);
        ImageView img9 = findViewById(R.id.img9);
        Button next = findViewById(R.id.next_Button);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               exercisepointer= addtoList("exercise", img1, exercisepointer);


            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              relaxinpointer=  addtoList("dancing", img2,relaxinpointer);

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaxinpointer =addtoList("indoorgames", img3, relaxinpointer);

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicpodcastpointer = addtoList("music", img4, musicpodcastpointer);

            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               relaxinpointer= addtoList("drawing", img5, relaxinpointer);

            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              wetimePointer =  addtoList("travel", img6, wetimePointer);

            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               relaxinpointer= addtoList("reading", img7, relaxinpointer);

            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               wetimePointer= addtoList("sports", img8, wetimePointer);

            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercisepointer=addtoList("yoga", img9, exercisepointer);
            }
        });
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                snapshot.child("exercise")
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })
        //setRecyclerView() ;

        Button button=(Button)findViewById(R.id.next_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<arrayList.size(); i++) {
                    update += arrayList.get(i) +" ";
                }
                getData();
                movetohome();
            }
        });
    }

    private void getData() {
            ref2.child("chat").setValue(update+"");
            ref.child("exercisee").setValue(exercisepointer+"");
            ref.child("musicpodcast").setValue(musicpodcastpointer+"");
            ref.child("relaxinactivities").setValue(relaxinpointer+"");
            ref.child("wetimee").setValue(wetimePointer+"");
            ref.child("nutrition").setValue(0+"");
    }
    private int addtoList(String s, ImageView v, int pointer) {
        if(arrayList.contains(s))
        {
            arrayList.remove(s);
            v.setBackgroundResource(R.color.white);
            pointer--;
        }
        else{
            arrayList.add(s);
            v.setBackgroundResource(R.color.blue);
            pointer++;
          //  Toast.makeText(this, arrayList.toString(),Toast.LENGTH_SHORT ).show();
        }
        return pointer;
    }

    private void movetohome() {

        Intent intent = new Intent(Interest.this, Details.class);
        intent.putExtra("age", age);
        startActivity(intent);
    }


//    private ArrayList<String> getQuantityData(){
//        ArrayList<String> arrayList = new ArrayList<>() ;
//        arrayList.add("Workout and Exercise");
//        arrayList.add("Dance");
//        arrayList.add("Indoor Games");
//        arrayList.add("Art & Sketching");
//        arrayList.add("Reading Books");
//        arrayList.add("Sports");
//        arrayList.add("Music");
//        arrayList.add("Travelling");
//        arrayList.add("Yoga & Meditation");
//
//        return arrayList ;
//    }
//    private void setRecyclerView() {
//        recyler_view.setHasFixedSize(true);
//        recyler_view.setLayoutManager(new LinearLayoutManager(this));
//        adapter=new QuantityAdapter(this,getQuantityData(),this) ;
//        recyler_view.setAdapter(adapter);
//    }

    @Override
    public void onQuantityChange(ArrayList<String> arrayList) {
        Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show();
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }

}