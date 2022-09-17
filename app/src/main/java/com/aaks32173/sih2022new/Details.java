package com.aaks32173.sih2022new;
import static ai.api.util.ParametersConverter.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Details extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    EditText heightt, weightt;
    Button bmi,next;
    String check;
    TextView showbmi, bmiinfo;
    DatabaseReference dbref;
    String age;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth mAuth1;
    private FirebaseUser mCurrentUser1;
    RadioGroup radioGroup;
    RadioButton rb1,rb2;
    String diet;
    String final_email ="";
    int age1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        //sleepdetail = false;
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("info");
       age =getIntent().getExtras().getString("age");

        heightt = findViewById(R.id.height);
        weightt = findViewById(R.id.weight);
        bmi = findViewById(R.id.bmibtn);
        showbmi = findViewById(R.id.bmishow);
        bmiinfo = findViewById(R.id.bmiinfo);
        next = findViewById(R.id.next_bmi);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();



        mAuth1 = FirebaseAuth.getInstance();
        mCurrentUser1 = mAuth1.getCurrentUser();

        radioGroup=findViewById(R.id.radioGroup);



        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Float h= Float.parseFloat(String.valueOf(heightt.getText()));
                Float w= Float.parseFloat(String.valueOf(weightt.getText()));
                h=h/100;
                Float b=w/(h*h);
                bmiinfo.setVisibility(view.getVisibility());

                if (b < 18.5) {
                    check = "Under Weight";
                } else if (b >= 18.5 && b < 24.9) {
                    check = "Healthy";
                } else if (b >= 24.9 && b < 30) {
                    check = "Overweight";
                } else if (b >= 30) {
                    check = "Suffering from Obesity";
                }
                Float finalH = h;
                Float finalH1 = h;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String age = dataSnapshot.child("age").getValue().toString();
                        int age2 = Integer.parseInt(age);
                        double calories  = 387 + 7.31 * Double.parseDouble(age) +1 * (10.9 * w + 660.7 * finalH1);
                        calories=(int)Math.round(calories) ;
                        String s = calories+"";
                        dbref = FirebaseDatabase.getInstance().getReference("UserInfo").child(final_email).child("BMI");
                        dbref.child("calinitial").setValue("0");
                        dbref.child("calfinal").setValue(s);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });

//
//                calories = ((height_inch*4.7)+(weight_pounds*4.35)-(age_id*4.7))*1.35;
                bmiinfo.setText(check);
                showbmi.setVisibility(view.getVisibility());
                showbmi.setText(b.toString());
                next.setVisibility(view.getVisibility());
                String email = mAuth.getCurrentUser().getEmail();
                final_email = encodeUserEmail(email);
                dbref = FirebaseDatabase.getInstance().getReference("UserInfo").child(final_email).child("BMI");
                dbref.child("height").setValue(h.toString());
                dbref.child("weight").setValue(w.toString());
                dbref.child("bmi").setValue(b.toString());
                dbref.child("condition").setValue(check);

            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i= new Intent(Details.this,Diett.class);
//                finish();
//                startActivity(i);
//            }
//        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= mCurrentUser1.getEmail();
//                Toast.makeText(view.getContext(), "2", Toast.LENGTH_SHORT).show();
                dbref = FirebaseDatabase.getInstance().getReference("UserInfo").child(final_email);
                dbref.child("userDiet").setValue(diet);
                if (Integer.parseInt(age) > 8 && Integer.parseInt(age) <= 11) {
            Intent intent =new  Intent(Details.this, fouthFifthGroup.class);
            startActivity(intent);
        } else if (Integer.parseInt(age) <= 14 && Integer.parseInt(age) > 11) {
            Intent intent = new Intent(Details.this, SixthEighthGroup.class);
            startActivity(intent);
        }

                else {
                    Toast.makeText(Details.this, "details", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Details.this, ninetwelvegroup.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void getUserData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(final_email).child("info");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String age = dataSnapshot.child("age").getValue().toString();
                dbref = FirebaseDatabase.getInstance().getReference("UserInfo").child(final_email);
                dbref.child("age").setValue(age);

                age1=Integer.parseInt(age);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void rbclick(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        rb1 = findViewById(radioId);
        diet =rb1.getText().toString();
//        Toast.makeText(this, diet,
//                Toast.LENGTH_SHORT).show();
    }

    private String encodeUserEmail(String email) {
        return email.replace(".", ",");
    }

}