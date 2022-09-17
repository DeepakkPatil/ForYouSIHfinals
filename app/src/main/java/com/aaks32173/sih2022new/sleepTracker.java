package com.aaks32173.sih2022new;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
public class sleepTracker extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    DatabaseReference databaseReference2;
    FirebaseUser Currentuser;
    TextView sleepscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("SleepDetails");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String detect = snapshot.child("detection").getValue().toString();
                if(detect.equals("true"))
                {
                    Intent intent = new Intent(sleepTracker.this, question.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
      Button music = findViewById(R.id.musicforsleep);
        sleepscore = findViewById(R.id.sleepscore);
        Button sleep=findViewById(R.id.sleephygine);
        sleepNotify();
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMusic();
            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotosleep();
            }
        });
       // music.setBackgroundColor(getResources().getColor(R.drawable.colors);
    getUserData();
    }
    private void sleepNotify() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String strDate = mdformat.format(calendar.getTime());
        if(strDate.equals("15:07")){
            addNotification();
        }
    }
    public void gotoMusic()
    {
       Intent intent = new Intent(sleepTracker.this, MusicPlayer.class);
       intent.putExtra("path", "SleepMusic");
        intent.putExtra("grp", "SleepMusic");
       startActivity(intent);
    }
    public void gotosleep()
    {
        Intent intent = new Intent(sleepTracker.this, sleepHygienePanle.class);

        startActivity(intent);
    }
    private void addNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("sleep notify", "sleep notify", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        String message = "You should sleep now as your bed time is started.";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(sleepTracker.this, "sleep notify");
        builder.setContentTitle("Bed time started");
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.background2);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(sleepTracker.this);
        managerCompat.notify(1, builder.build());
    }
    private void getUserData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("SleepDetails");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String SleepScore = dataSnapshot.child("SleepScore").getValue().toString();
                sleepscore.setText(SleepScore+"/100");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}