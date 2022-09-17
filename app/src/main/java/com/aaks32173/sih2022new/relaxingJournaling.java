package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

public class relaxingJournaling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing_journaling);
       // https://journey.cloud/
        LinearLayout pop1 = findViewById(R.id.pop1);



        String email = getIntent().getExtras().getString("email");

        pop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v,R.layout.popup_window12);
                increasecounter(email);
            }
        });
        ImageButton benifits = findViewById(R.id.benifits);
        benifits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v,R.layout.popup_windowjournal);
                increasecounter(email);
            }
        });
        ImageButton tips = findViewById(R.id.tips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v,R.layout.popup_windowjournal2);
                increasecounter(email);
            }
        });
        ImageButton img1 = findViewById(R.id.journal);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://journey.cloud/";

                increasecounter(email);
                openWeb(url,email);
            }
        });
    }
    public void onButtonShowPopupWindowClick(View view, int w) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(w, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
    public void openWeb(String url,String email)
    {
        Intent intent = new Intent(relaxingJournaling.this, webView.class);
        intent.putExtra("url",url );
        increasecounter(email);
        startActivity(intent);
    }





    private void increasecounter(String email) {
        LocalDate today=LocalDate.now();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("relaxinactivities").child("progress").getValue().toString();

                if(Integer.parseInt(progress)<=90) {
                    int prg = Integer.parseInt(progress) + 10;

                    reference1.child("relaxinactivities").child("progress").setValue(Integer.toString(prg));

                }else{

                    int prg = 100;

                    reference1.child("relaxinactivities").child("progress").setValue(Integer.toString(prg));

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}