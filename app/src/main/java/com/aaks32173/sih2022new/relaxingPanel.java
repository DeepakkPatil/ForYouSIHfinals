package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class relaxingPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing_panel);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoKids();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPrimary();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHigher();
            }
        });
    }

    private void gotoHigher() {
        Intent intent = new Intent(relaxingPanel.this, relaxingActivityHigher.class);
        startActivity(intent);
    }

    private void gotoPrimary() {
        Intent intent = new Intent(relaxingPanel.this, relaxingActivityPrimary.class);
        startActivity(intent);

    }

    private void gotoKids() {
        Intent intent = new Intent(relaxingPanel.this, relaxingActivityKids.class);
        startActivity(intent);
    }
}