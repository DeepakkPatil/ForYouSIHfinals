package com.aaks32173.sih2022new;

import static com.aaks32173.sih2022new.MainActivity.i;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MenstrualHome extends AppCompatActivity {
    TextView predictdatee;
    TextView dayofperiod;
    TextView periodtxt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    DatabaseReference databaseReference2;
    FirebaseUser Currentuser;
    private NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual_home);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("PeriodDetails");
        Button startPeriodbtn = findViewById(R.id.startperiod);
        predictdatee = findViewById(R.id.predicteddate);
        dayofperiod = findViewById(R.id.dayofperiod);
        periodtxt = findViewById(R.id.textperiod);
        Button log = findViewById(R.id.log);
        Button exercise = findViewById(R.id.exercise);
        Button magazine = findViewById(R.id.magazine);
        Button diet = findViewById(R.id.diet);
        Button doanddont = findViewById(R.id.doanddont);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity3();
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity2();
            }
        });
        magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity4();
            }
        });
        doanddont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity5();
            }
        });
        startPeriodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPeriod();
            }
        });

        getUserData();
        // Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
    }

    private void getUserData() {
        // DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("PeriodDetails");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String lastDatee = dataSnapshot.child("lastDate").getValue().toString();
                String cycleDays = dataSnapshot.child("cycleLength").getValue().toString();
                String bleedingdayss = dataSnapshot.child("bleedingDays").getValue().toString();
                int cycleLen = Integer.parseInt(cycleDays);
                String req = lastDatee;
                SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
                Date myDate = null;
                try {
                    myDate = formatter2.parse(req);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(myDate);
                    calendar.add(Calendar.DAY_OF_YEAR, cycleLen);
                    Date newDate = calendar.getTime();
                    String date = formatter2.format(newDate);
                    predictdatee.setText(date + " next period");
                    Date current = new Date();
                    long different = current.getTime() - myDate.getTime();
                    different = different / (24 * 60 * 60 * 1000) + 1;
                    int bleedingdays = Integer.parseInt(bleedingdayss);
                    if (different == 1) {
                        addNotification();
                    }
                    if (different >= 1 && different <= bleedingdays) {
                        String difference = "" + different;
                        dayofperiod.setText(difference);
                        periodtxt.setText(" day of period");
                    }
                    else {
                        myDate = newDate;
                        long different2 = myDate.getTime() - current.getTime();
                        different2 = different2 / (24 * 60 * 60 * 1000) + 1;
                        if (different2 <= 0 && different2 >= -15) {
                            String difference2 = "" + -(different2 - 1);
                            dayofperiod.setText(difference2);
                            periodtxt.setText(" day delay period");
                            predictdatee.setText("");
                            return;
                        } else if (different2 <= 0) {
                            String difference2 = "" + -(different2 - 1);
                            dayofperiod.setText(difference2);
                            periodtxt.setText(" day delay period. Please consult with doctor");
                            predictdatee.setText("");
                            return;
                        }
                        String difference2 = "" + different2;
                        dayofperiod.setText(difference2);
                        periodtxt.setText(" days to go");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void startPeriod() {
        Date current = new Date();
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter2.format(current);
        databaseReference.child("lastDate").setValue(date);
        String s = "lastDates" + i;
        setdetails(s, date);
        getUserData();
    }
    private void addNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("period notify", "period notify", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        String message = "Please follow some diets and do some exercises for healthy period.";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MenstrualHome.this, "period notify");
        builder.setContentTitle("Your period is arrived");
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.background2);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MenstrualHome.this);
        managerCompat.notify(1, builder.build());
    }

    private void nextActivity() {
        Intent intent = new Intent(this, log.class);
        startActivity(intent);

    }

    private void nextActivity2() {
        Intent intent = new Intent(this, exercise.class);
        startActivity(intent);

    }

    private void nextActivity3() {
        Intent intent = new Intent(this, diet.class);
        startActivity(intent);

    }

    private void nextActivity4() {
        Intent intent = new Intent(this, magazine.class);
        startActivity(intent);

    }

    private void nextActivity5() {
        Intent intent = new Intent(this, doanddont.class);
        startActivity(intent);
    }
    public void setdetails(String s, String date) {
        databaseReference2 = firebaseDatabase.getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("PeriodDetails").child("lastDates");
        databaseReference2.child(s).setValue(date);
        if (i == 5)
            i = 0;
        i++;
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}