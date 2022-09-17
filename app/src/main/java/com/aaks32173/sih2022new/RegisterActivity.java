package com.aaks32173.sih2022new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.io.FileWriter;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    EditText register_name,register_email,register_password,register_confirm_password,register_age,register_sssm_id,city;
    Button register_registerbtn;
    RadioButton radioButton;
    FirebaseAuth mAuth;
    RadioGroup radioGroup;
    String gender;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final TextView register_loginnow = findViewById(R.id.register_loginnow);
        register_loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetologin();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        register_name = findViewById(R.id.register_name);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_confirm_password = findViewById(R.id.register_confirm_password);
        register_age = findViewById(R.id.register_age);
        register_sssm_id = findViewById(R.id.register_sssm_id);
        city = findViewById(R.id.register_family_id);
        register_registerbtn = findViewById(R.id.register_registerbtn);
//        female = findViewById(R.id.female);
//        male = findViewById(R.id.male);
        radioGroup=findViewById(R.id.radioGroup);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserInfo");//.child(encode_email);//.child(encodeUserEmail(register_email.toString()));
        userInfo = new UserInfo();
//        if(female.isSelected()){
//            male.setSelected(false);
//        }
//        else if(male.isSelected())
//        {
//            female.setSelected(false);
//        }



        register_registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                RegisterNewUser();
            }
        });
    }

    public void rbclick(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        gender=radioButton.getText().toString();
        Toast.makeText(this, gender,
                Toast.LENGTH_SHORT).show();
    }
    private void RegisterNewUser() {
        String name, email, password, confirm_password, age, sssm_id, cityname;
        name = register_name.getText().toString();
        email = register_email.getText().toString();
        password = register_password.getText().toString();
        confirm_password = register_confirm_password.getText().toString();
        age = register_age.getText().toString();

        sssm_id = register_sssm_id.getText().toString();
        cityname = city.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password)
                || TextUtils.isEmpty(age) || TextUtils.isEmpty(sssm_id) || TextUtils.isEmpty(cityname) || gender.equals("")) {
            Toast.makeText(getApplicationContext(), "All fields are mandatory", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(confirm_password)) {
            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
            return;
        }
        if (Integer.parseInt(age) <= 0) {
            Toast.makeText(getApplicationContext(), "Enter Valid Age", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    addDatatoFirebase(name, email, password, age, sssm_id, cityname, gender);

                    if (Integer.parseInt(age) < 8) {
                        Intent intent = new Intent(RegisterActivity.this, DashHome_Nur_3.class);
                        startActivity(intent);
                    } else if(gender.equals("Female") &&Integer.parseInt(age)>11) {
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("age", age);
                        startActivity(intent);}
                        else{
                            Intent intent = new Intent(RegisterActivity.this, Interest.class);
                            intent.putExtra("age", age);
                            startActivity(intent);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }   private void addDatatoFirebase(String name, String email, String password, String age, String sssmid, String cityname, String gender) {
        userInfo.setName(name);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setage(age);
        userInfo.setSssm_id(sssmid);
        userInfo.setCityname(cityname);
        userInfo.setGender(gender);
        final boolean[] processDone = {true};
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(processDone[0]) {
                    databaseReference.child(encodeUserEmail(email.toString())).child("info").setValue(userInfo);
                    databaseReference.child(encodeUserEmail(email.toString())).child("userDiet").setValue("0");
                    databaseReference.child(encodeUserEmail(email.toString())).child("rewards").setValue("0");
                    databaseReference.child(encodeUserEmail(email.toString())).child("SleepDetails").child("SleepActivity").setValue(true);
                    databaseReference.child(encodeUserEmail(email.toString())).child("SleepDetails").child("SleepScore").setValue("0");
                    databaseReference.child(encodeUserEmail(email.toString())).child("SleepDetails").child("Sleepday").setValue("0");
                    databaseReference.child(encodeUserEmail(email.toString())).child("SleepDetails").child("detection").setValue("false");
                    Toast.makeText(RegisterActivity.this, "data added", Toast.LENGTH_SHORT).show();
                    processDone[0] =false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegisterActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void movetologin(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}