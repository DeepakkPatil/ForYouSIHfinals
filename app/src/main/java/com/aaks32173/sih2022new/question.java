package com.aaks32173.sih2022new;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
public class question extends AppCompatActivity {
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        int [] agreedans = new int[8];
        LinearLayout l1 = findViewById(R.id.ly1);
        LinearLayout l2 = findViewById(R.id.ly2);
        LinearLayout l3 = findViewById(R.id.ly3);
        LinearLayout l4 = findViewById(R.id.ly4);
        LinearLayout l5 = findViewById(R.id.ly5);
        LinearLayout l6 = findViewById(R.id.ly6);
        LinearLayout l7 = findViewById(R.id.ly7);
        LinearLayout l8 = findViewById(R.id.ly8);
        Button y1 = findViewById(R.id.y1);
        Button y2 = findViewById(R.id.y2);
        Button y3 = findViewById(R.id.y3);
        Button y4 = findViewById(R.id.y4);
        Button y5 = findViewById(R.id.y5);
        Button y6 = findViewById(R.id.y6);
        Button y7 = findViewById(R.id.y7);
        Button y8 = findViewById(R.id.y8);
        Button n1 = findViewById(R.id.n1);
        Button n2 = findViewById(R.id.n2);
        Button n3 = findViewById(R.id.n3);
        Button n4 = findViewById(R.id.n4);
        Button n5 = findViewById(R.id.n5);
        Button n6 = findViewById(R.id.n6);
        Button n7 = findViewById(R.id.n7);
        Button n8 = findViewById(R.id.n8);
        y1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(GONE);
                l5.setVisibility(VISIBLE);

            }
        });
        y2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=(2);
                l2.setVisibility(GONE);
                l6.setVisibility(VISIBLE);

            }
        });
        y3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=(3);
                l3.setVisibility(GONE);
                l7.setVisibility(VISIBLE);

            }
        });
        y4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=(4);
                l4.setVisibility(GONE);
                l8.setVisibility(VISIBLE);

            }
        });
        y5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=(5);
                l5.setVisibility(GONE);

            }
        });
        y6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=6;
                l6.setVisibility(GONE);

            }
        });
        y7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=7;
                l7.setVisibility(GONE);


            }
        });
        y8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l8.setVisibility(GONE);
                Intent intent = new Intent(question.this, recommondationsSleep.class);
                intent.putExtra("array", agreedans);
                startActivity(intent);
            }
        });
        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=1;
                l1.setVisibility(GONE);
                l5.setVisibility(VISIBLE);
            }
        });
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l2.setVisibility(GONE);
                l6.setVisibility(VISIBLE);

            }
        });
        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l3.setVisibility(GONE);
                l7.setVisibility(VISIBLE);

            }
        });
        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l4.setVisibility(GONE);
                l8.setVisibility(VISIBLE);

            }
        });
        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l5.setVisibility(GONE);

            }
        });
        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l6.setVisibility(GONE);

            }
        });
        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l7.setVisibility(GONE);


            }
        });
        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreedans[i++]=8;
                l8.setVisibility(GONE);
                Intent intent = new Intent(question.this, recommondationsSleep.class);
                intent.putExtra("array", agreedans);
                startActivity(intent);
            }
        });
    }


}