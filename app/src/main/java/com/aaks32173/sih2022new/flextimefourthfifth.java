package com.aaks32173.sih2022new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.time.LocalDate;
public class flextimefourthfifth extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    String email;
//    Button step ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flextimefourthfifth);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        email = getIntent().getExtras().getString("email");
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FlexFouthFifth");
        LinearLayout btn1 = findViewById(R.id.btn1);
       Button step=findViewById(R.id.walk);

        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(flextimefourthfifth.this, stepcounter.class);

                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("1");
                increasecounter(email, "exercise");
            }
        });
        LinearLayout btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("7");
                increasecounter(email, "exercise");
            }
        });
        LinearLayout btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("2");
                increasecounter(email, "exercise");
            }
        });
        LinearLayout btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("3");
                increasecounter(email, "exercise");
            }
        });
        LinearLayout btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("4");
                increasecounter(email, "exercise");
            }
        });
        LinearLayout btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("5");
                increasecounter(email, "exercise");
            }
        });
        LinearLayout btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("6");
                increasecounter(email, "exercise");
            }
        });}
    public void getfun(String s) {
            databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String url = dataSnapshot.child("Vedio" + s).getValue().toString();
                Intent intent = new Intent(flextimefourthfifth.this, vedioPlay.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void increasecounter(String email, String trend) {
        LocalDate today = LocalDate.now();
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("WEEKTODO");

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("exercise").child("progress").getValue().toString();

                if (Integer.parseInt(progress) <= 90) {
                    int prg = Integer.parseInt(progress) + 10;
                    trending(trend);
                    reference1.child("exercise").child("progress").setValue(Integer.toString(prg));
                    week();
                    if(prg==100) {

                        reward() ;
                    }
                } else if(Integer.parseInt(progress)==100){

                    int prg = 100;

                    reference1.child("exercise").child("progress").setValue(Integer.toString(prg));
                    reward();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void trending(String trend)
    {       DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
            ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String p = snapshot.child("trending").child(trend).getValue().toString();
                int p2 = Integer.parseInt(p);
                ref2.child("trending").child(trend).setValue(Integer.toString(10+p2));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){
            }
        });}
    private void week()
    {        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("WEEKTODO");



        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("1").child("progress").getValue().toString();

                    int prg = Integer.parseInt(progress) + 10;

                    reference2.child("1").child("progress").setValue(Integer.toString(prg));


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        }
    void reward(){
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DatabaseReference reference2 =
                        FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email);
                String rew = snapshot.child("rewards").getValue().toString();
                int rev = Integer.parseInt(rew) + 50;
                reference2.child("rewards").setValue(rev + "");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }}