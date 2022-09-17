package com.aaks32173.sih2022new;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    Button saveBtn;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    public static int i = 2;
    String age;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    private Calendar c;
    private int year;
    private int month;
    private int date2;
    //Button to pick date
    private Button datepicker;
    private TextView selectdate;
    EditText cycleentry;
    EditText daysentry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
        databaseReference = firebaseDatabase.getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("PeriodDetails");
        saveBtn = findViewById(R.id.save);
        age= getIntent().getExtras().getString("age");
        daysentry = findViewById(R.id.daysinput);
        cycleentry = findViewById(R.id.cycleinput);
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date2 = c.get(Calendar.DAY_OF_MONTH);
        //Button to pick date
        datepicker = findViewById(R.id.datepick);
        selectdate = findViewById(R.id.selectdate);
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectdatefun();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.

                fun();
            }
        });
    }

    private void fun() {
        String date = selectdate.getText().toString();
        String days = daysentry.getText().toString();
        String cyclelen = cycleentry.getText().toString();
        String day2 = days;
        //int i=Integer.parseInt(day2);
        if (date.equals("Select date") || TextUtils.isEmpty(days) || TextUtils.isEmpty(cyclelen)) {
            // if the text fields are empty
            // then show the below message.
            Toast.makeText(MainActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(day2) > 7 || Integer.parseInt(day2) < 2) {
            Toast.makeText(MainActivity.this, "Please select the bleeding days between 2-7", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "periods details are saved successfully", Toast.LENGTH_SHORT).show();
            databaseReference.child("lastDate").setValue(date);
            databaseReference.child("lastDates").child("lastDates1").setValue(date);
            databaseReference.child("cycleLength").setValue(cyclelen);
            databaseReference.child("bleedingDays").setValue(days);
            nextActivity();
        }
    }
        private void selectdatefun () {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    selectdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            };
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, year, month, date2);
            datePickerDialog.show();
        }

        private void nextActivity () {
            Intent intent = new Intent(this, Interest.class);
            intent.putExtra("age", age);
            startActivity(intent);

        }
        private String encodeUserEmail (String email){
            return email.replace(".", ",");
        }
    }


