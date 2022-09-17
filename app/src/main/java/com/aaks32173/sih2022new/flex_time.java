package com.aaks32173.sih2022new;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class flex_time extends AppCompatActivity {
    private FirebaseAuth mauth;
    DatabaseReference databaseReference;
    FirebaseUser Currentuser;
    FirebaseDatabase firebaseDatabase;
    final String[] age1 = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_time);
        LinearLayout yoga = findViewById(R.id.flextimeyoga);
        LinearLayout workout = findViewById(R.id.flextimeworkout);
        LinearLayout stretching = findViewById(R.id.flextimestretching);
        LinearLayout meditation = findViewById(R.id.flextimemeditation);

//        ImageButton yoga = findViewById(R.id.yogabtn);
//        ImageButton workout = findViewById(R.id.workoutbtn);
//        ImageButton stretching = findViewById(R.id.stretchingbth);
//        ImageButton meditation = findViewById(R.id.meditationbtn);
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("info");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String age = dataSnapshot.child("age").getValue().toString();
                age1[0] =age;
                if (parseInt(age) < 14 && parseInt(age) >= 11) {
//                    LinearLayout ly = findViewById(R.id.workout);
//                    ly.setVisibility(LinearLayout.GONE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openyogas();
            }
        });
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openworkouts();
            }
        });
        stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openstrerchings();
            }
        });
        meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmeditations();
            }
        });
    }
    private void openyogas(){
        Intent intent = new Intent(this,yoga.class);
        intent.putExtra("category", "exercises1");

        startActivity(intent);
    }
    private void openworkouts(){
        Intent intent = new Intent(this,workout.class);
        if(Integer.parseInt(age1[0])>=10 && Integer.parseInt(age1[0])<14) {
            intent.putExtra("group", "SixthEight");
            intent.putExtra("age", "10");
        }
        else
        {
            intent.putExtra("group", "ninth");
            intent.putExtra("age", "14");
        }
        startActivity(intent);
    }
    private void openstrerchings(){
        Intent intent = new Intent(this,stretching.class);
        if(Integer.parseInt(age1[0])>=10 && Integer.parseInt(age1[0])<14) {
            intent.putExtra("group", "SixthEight");
            intent.putExtra("age", "10");
        }
        else
        {
            intent.putExtra("group", "ninth");
            intent.putExtra("age", "14");
        }
        startActivity(intent);
    }
    private void openmeditations(){
        Intent intent = new Intent(this,meditation.class);
        if(Integer.parseInt(age1[0])>=10 && Integer.parseInt(age1[0])<14) {
            intent.putExtra("group", "SixthEight");
            intent.putExtra("age", "10");
        }
        else
        {
            intent.putExtra("group", "ninth");
            intent.putExtra("age", "14");
        }
        startActivity(intent);
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}