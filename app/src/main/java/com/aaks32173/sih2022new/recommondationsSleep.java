package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class recommondationsSleep extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommondations_sleep);
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("SleepDetails");

        LinearLayout l1 = findViewById(R.id.ans1);
        LinearLayout l2 = findViewById(R.id.ans2);
        LinearLayout l3 = findViewById(R.id.ans3);
        LinearLayout l4 = findViewById(R.id.ans4);
        LinearLayout l5 = findViewById(R.id.ans5);
        LinearLayout l6 = findViewById(R.id.ans6);
        LinearLayout l7 = findViewById(R.id.ans7);
        LinearLayout l8 = findViewById(R.id.ans8);
        Button notsatisfy = findViewById(R.id.notsatisfied);
        int[] arrayB = getIntent().getExtras().getIntArray("array");
        int [] array={1,2,3,4,5,6,7,8};
        for(int i=0; i<8; i++)
        {
            int check =0;
            for(int j=0; j<arrayB.length;j++)
            {
                if(array[i]==arrayB[j])
                {
                    check=1;
                }
            }if(check==0) {
            switch (array[i]) {
                case 1:
                    l1.setVisibility(View.GONE);
                    break;
                case 2:
                    l2.setVisibility(View.GONE);
                    break;
                case 3:
                    l3.setVisibility(View.GONE);
                    break;
                case 4:
                    l4.setVisibility(View.GONE);
                    break;
                case 5:
                    l5.setVisibility(View.GONE);
                    break;
                case 6:
                    l6.setVisibility(View.GONE);
                    break;
                case 7:
                    l7.setVisibility(View.GONE);
                    break;
                case 8:
                    l8.setVisibility(View.GONE);
            }
        }
        }

        notsatisfy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSleep();
            }
        });
    }

    private void gotoDash() {
    }

    private void gotoSleep() {
        Intent intent = new Intent(recommondationsSleep.this, sleepTracker.class);
        databaseReference.child("detection").setValue("false");
        startActivity(intent);
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}