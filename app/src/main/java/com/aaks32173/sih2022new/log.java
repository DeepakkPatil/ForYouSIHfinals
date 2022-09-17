package com.aaks32173.sih2022new;

import static com.aaks32173.sih2022new.MainActivity.i;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class log extends AppCompatActivity {
    private ListView mListView;
    FirebaseAuth mauth;
    DatabaseReference databaseReference2;
    FirebaseUser Currentuser;
    private ListView newDates;
    private ArrayList<String> oldPeriods = new ArrayList<String>();
    private ArrayList<String> newDatess = new ArrayList<String>();
     public static int x= 1;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        mListView = (ListView) findViewById(R.id.oldPeriods);
        newDates = (ListView) findViewById(R.id.predictedPeriods);
        mauth = FirebaseAuth.getInstance();
        Currentuser = mauth.getCurrentUser();
   // while(x<=4)
        getOldDate();
        getOldDate();
        getOldDate();
        getOldDate();
        getOldDate();

       // showdata(x2);
            getNewData();

    }

    private void getOldDate()
    {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.text_color_layout, oldPeriods);
       // ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, oldPeriods);

        // below line is used for getting reference
        // of our Firebase Database.
        reference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("PeriodDetails").child("lastDates");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    String lastDatee = dataSnapshot.child("lastDates" + x).getValue().toString();
                    x++;

                     oldPeriods.add("Date: " + lastDatee);
                //oldPeriods.add("Bleeding days "+ days);
                    adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(log.this, "periods details are not saved successfully", Toast.LENGTH_SHORT);
        mListView.setAdapter(adapter);

    }
    //Prediction Function
    private void getNewData() {
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.text_color_layout, newDatess);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PeriodDetails");
        reference.addValueEventListener(new ValueEventListener() {
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
                    for(int i=0; i<5; i++) {
                        myDate = formatter2.parse(req);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(myDate);
                        calendar.add(Calendar.DAY_OF_YEAR, cycleLen);
                        Date newDate = calendar.getTime();
                        String newDatestr = formatter2.format(newDate);

                        newDatess.add("Date: " + newDatestr);
                        //oldPeriods.add("Bleeding days "+ days);
                        adapter2.notifyDataSetChanged();
                        //predictdatee.setText(date + " next period");
                        newDates.setAdapter(adapter2);
                        req = newDatestr;
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
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }

}
