package com.aaks32173.sih2022new;

import static java.lang.Integer.parseInt;

import static ai.api.util.ParametersConverter.parseFloat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    EditText login_email,login_password;
    Button login;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference2;
    FirebaseUser Currentuser;
    FirebaseDatabase firebaseDatabase;
    public static boolean sleepdetail=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        Currentuser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        login=findViewById(R.id.login_loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });

        final TextView login_registernow = findViewById(R.id.login_registernow);
        login_registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoregister();
            }
        });

    }

    private void loginUserAccount() {
        String email, password;
        email =login_email.getText().toString();
        password = login_password.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(
                    @NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {

                    databaseReference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo");

                    databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String age = dataSnapshot.child(encodeUserEmail(email)).child("info").child("age").getValue().toString();
                            if(parseInt(age)<8 && parseInt(age)>2)
                            {
                                Intent intent = new Intent(LoginActivity.this, DashHome_Nur_3.class);
                                startActivity(intent);
                            }
                            else{
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
                                String strDate = mdformat.format(calendar.getTime());
                                String hour = strDate.substring(0,2);
                                int hourday = parseInt(hour);
                                final boolean[] processDone = {true};
                                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if(processDone[0]) {
                                            String booleanvar = dataSnapshot.child(encodeUserEmail(email)).child("SleepDetails").child("SleepActivity").getValue().toString();
                                            if(hourday>=7 && hourday<=12 && booleanvar.equals("true"))
                                            {
                                                Intent intent = new Intent(LoginActivity.this, getSleepDetails.class);
                                                intent.putExtra("age", age);
                                                startActivity(intent);
                                            }
                                            else {
                                                Calendar calendar2 = Calendar.getInstance();
                                                SimpleDateFormat mdformat2 = new SimpleDateFormat("HH:mm");
                                                String strDate2 = mdformat2.format(calendar2.getTime());
                                                String hour2 = strDate2.substring(0,1);
                                                int hourday2 = parseInt(hour2);
                                                if(hourday2>9)
                                                {
                                                    databaseReference2.child(encodeUserEmail(email)).child("SleepDetails").child("SleepActivity").setValue("true");
                                                }
                                                if(parseInt(age)>8 && parseInt(age)<=11) {
                                                    Intent intent = new Intent(LoginActivity.this, fouthFifthGroup.class);
                                                    startActivity(intent);
                                                }
                                                else if (parseInt(age) <=14 && parseInt(age) > 11) {
                                                    Intent intent = new Intent(LoginActivity.this, SixthEighthGroup.class);
                                                    startActivity(intent);
                                                }
                                                else if (parseInt(age) >14) {
                                                    Intent intent = new Intent(LoginActivity.this, ninetwelvegroup.class);
                                                    startActivity(intent);
                                                }
                                            }
                                            processDone[0] =false;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                });
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }

                    });
                    Toast.makeText(getApplicationContext(), "Login successful!!",Toast.LENGTH_LONG).show();



                }
                else {
                    // sign-in failed
                    Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
    private void movetoregister(){
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }
}