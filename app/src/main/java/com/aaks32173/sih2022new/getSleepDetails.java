package com.aaks32173.sih2022new;

import static java.lang.Integer.parseInt;
import static ai.api.util.ParametersConverter.parseFloat;
//import static com.aaks32173.sih2022new.LoginActivity.sleepdetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class getSleepDetails extends AppCompatActivity {

    private float sleepScoreHours = 0;
    private float SleepScore =0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_sleep_details);
       firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("SleepDetails");
        int age = Integer.parseInt(getIntent().getExtras().getString("age"));
       Button submit = findViewById(R.id.submit);
        Button good = findViewById(R.id.good);
        Button bad = findViewById(R.id.bad);
        Button excellent = findViewById(R.id.excellent);
        EditText sleptHours= findViewById(R.id.hoursSlept);


        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getSleepDetails.this, "Your good sleep is recorded..", Toast.LENGTH_SHORT).show();
            sleepScoreHours = 35;
            }
        });
        excellent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getSleepDetails.this, "Your excellent sleep is recorded..", Toast.LENGTH_SHORT).show();
                sleepScoreHours = 35;
            }
        });
        bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getSleepDetails.this, "Your bad sleep is recorded..", Toast.LENGTH_SHORT).show();
                sleepScoreHours = 15;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sleptHours.getText().toString().isEmpty())
                {
                    Toast.makeText(getSleepDetails.this, "Please enter hours you slept..", Toast.LENGTH_SHORT).show();
                    return;
                }
                final boolean[] processDone = {true};
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(processDone[0]) {
                            String oldSleepScore = dataSnapshot.child("SleepScore").getValue().toString();
                            String day = dataSnapshot.child("Sleepday").getValue().toString();
                            float sleepold = parseFloat(oldSleepScore);
                            float dayold = parseFloat(day) + 1;
                            if (dayold == 8) {
                                if(sleepold<70)
                                {
                                    databaseReference.child("detection").setValue("true");
                                }
                                sleepold=0;
                                dayold = 1;
                            }
                            SleepScore = (SleepScore + sleepold) / dayold;
                            float sleepHourper = getHourPercent(sleptHours.getText().toString());
                            SleepScore = sleepHourper + sleepScoreHours;
                            databaseReference.child("SleepScore").setValue(SleepScore + "");
                            databaseReference.child("Sleepday").setValue(dayold + "");
                            databaseReference.child("SleepActivity").setValue("false");
                            processDone[0] =false;

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });
                sleeptrack(age);
            }
        });

    }
    private float getHourPercent(String hours)
    {
        float hour = parseFloat(hours);
        float ans =0;
        if(hour >= 6 && hour <=7)
        {
            ans = 50;
        }
        else if(hour>7)
            ans = 55;
        else if(hour<6 && hour>5)
            ans = 40;
        else
            ans = 30;
        return ans;
    }
    private  void sleeptrack(int age)
    {

        if(age>8 && age<11) {
            Intent intent = new Intent(getSleepDetails.this, fouthFifthGroup.class);
            startActivity(intent);
        }
        else if (age < 14 && (age) >= 11) {
            Intent intent = new Intent(getSleepDetails.this, SixthEighthGroup.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getSleepDetails.this, ninetwelvegroup.class);
            startActivity(intent);
        }
    }

    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}
