package com.aaks32173.sih2022new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

public class relaxingActivityHigher extends AppCompatActivity {

    private FirebaseAuth mauth;
    FirebaseUser Currentuser;
    String email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing_higher);
        ImageButton btn1 = findViewById(R.id.btn1);
        ImageButton btn2 = findViewById(R.id.btn2);
        ImageButton btn3 = findViewById(R.id.btn3);
        ImageButton btn4 = findViewById(R.id.btn4);
        ImageButton btn5 = findViewById(R.id.btn5);
        ImageButton btn6 = findViewById(R.id.btn6);

        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();

        email=encodeUserEmail(Currentuser.getEmail()).toString();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                increasecounter(email, "relaxingactivities");
                openWeb("https://bubblespop.netlify.app/");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                increasecounter(email, "relaxingactivities");
                openRiddle();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(relaxingActivityHigher.this, "This Option is under progress", Toast.LENGTH_SHORT).show();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increasecounter(email, "relaxingactivities");
                openWeb("https://in.pinterest.com/search" +
                        "/pins/?q=30%20day%20challenge&rs=srs&b" +
                        "_id=BKORT20M4hhYAAAAAAAAAADnXIsp6DsSY0UNo3OQNu" +
                        "Q7t2ByVfBVszigazLOkmwbcDiU0mJslpJuDGRjfq_U638&source_id" +
                        "=rlp_yG3iVaam");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoexercise();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                increasecounter(email, "relaxingactivities");
                openQuizzes();
            }
        });

    }

    private void gotoexercise() {
        Intent intent =  new Intent(relaxingActivityHigher.this, flex_time.class);
        startActivity(intent);
    }

    private void openRiddle() {
        Intent intent =  new Intent(relaxingActivityHigher.this, ActivityRiddles.class);
        startActivity(intent);
    }
    private void openQuizzes() {
        Intent intent =  new Intent(relaxingActivityHigher.this, relaxingQuizes.class);
        startActivity(intent);
    }
    private void gotojournalism() {
        Intent intent = new Intent(relaxingActivityHigher.this, relaxingJournaling.class);
        startActivity(intent);
    }
    public void openWeb(String url)
    {
        Intent intent = new Intent(relaxingActivityHigher.this, webView.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }



    private void increasecounter(String email, String trend) {
        LocalDate today = LocalDate.now();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("relaxinactivities").child("progress").getValue().toString();

                if (Integer.parseInt(progress) <= 90) {
                    int prg = Integer.parseInt(progress) + 10;
                    trending(trend);
                    reference1.child("relaxinactivities").child("progress").setValue(Integer.toString(prg));
                    week();
                    if(prg==100) {

//                        reward() ;
                    }
                } else if(Integer.parseInt(progress)==100){

                    int prg = 100;

                    reference1.child("relaxinactivities").child("progress").setValue(Integer.toString(prg));
//                    reward();
                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    private void trending(String trend)
    {        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String p = snapshot.child("trending").child(trend).getValue().toString();
                int p2 = Integer.parseInt(p);
                ref2.child("trending").child(trend).setValue(Integer.toString(10+p2));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void week()
    {        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("WEEKTODO");



        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("4").child("progress").getValue().toString();

                int prg = Integer.parseInt(progress) + 10;

                reference2.child("4").child("progress").setValue(Integer.toString(prg));


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }


}