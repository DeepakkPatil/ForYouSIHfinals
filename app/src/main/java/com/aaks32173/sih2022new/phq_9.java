package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class phq_9 extends AppCompatActivity implements View.OnClickListener {

    Button an;
    TextView linkTextView,tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phq9);

            linkTextView = findViewById(R.id.tvv) ;
            tv=findViewById(R.id.tvv9) ;
            an=findViewById(R.id.quizbtn) ;
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        tv.setMovementMethod(LinkMovementMethod.getInstance());

        an.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button clickbtn=(Button) view ;
        if(clickbtn.getId()==R.id.quizbtn){

            Intent intent = new Intent(this, depressn_main.class);
            //Intent intent = new Intent(AskLocationActivity.this, LatLongActivity.class);
            startActivity(intent);
        }

    }
}