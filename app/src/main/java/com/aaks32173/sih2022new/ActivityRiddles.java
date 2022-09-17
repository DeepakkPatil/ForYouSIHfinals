package com.aaks32173.sih2022new;

import static ai.api.util.ParametersConverter.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityRiddles extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddles);
        Button btn1  = findViewById(R.id.showans1);
        Button btn2  = findViewById(R.id.showans2);
        Button btn3  = findViewById(R.id.showans3);
        Button btn4  = findViewById(R.id.showans4);
        Button btn5  = findViewById(R.id.showans5);
        Button btn6  = findViewById(R.id.showans6);
        Button btn7  = findViewById(R.id.showans7);
        Button btn8  = findViewById(R.id.showans8);
        Button btn9  = findViewById(R.id.showans9);
        Button btn10  = findViewById(R.id.showans10);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        //sleepdetail = false;
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Riddle answers");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                            String answer = dataSnapshot.child("answer1").getValue().toString();
                        TextView tv = findViewById(R.id.ans1);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer10").getValue().toString();
                        TextView tv = findViewById(R.id.ans10);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer2").getValue().toString();
                        TextView tv = findViewById(R.id.ans2);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer3").getValue().toString();
                        TextView tv = findViewById(R.id.ans3);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer4").getValue().toString();
                        TextView tv = findViewById(R.id.ans4);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer5").getValue().toString();
                        TextView tv = findViewById(R.id.ans5);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer6").getValue().toString();
                        TextView tv = findViewById(R.id.ans6);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer7").getValue().toString();
                        TextView tv = findViewById(R.id.ans7);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer8").getValue().toString();
                        TextView tv = findViewById(R.id.ans8);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String answer = dataSnapshot.child("answer9").getValue().toString();
                        TextView tv = findViewById(R.id.ans9);
                        tv.setText(answer);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}