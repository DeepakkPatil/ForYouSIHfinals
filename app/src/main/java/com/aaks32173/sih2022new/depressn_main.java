package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class depressn_main extends AppCompatActivity implements View.OnClickListener {

    TextView qns ;
    Button ansa,ansb,ansc,ansd,submit ;
        int pos=0 ;
    int score=0;
    int totalqns=questionans.question.length ;
    int currentqnindex=0 ;
    String selectedans="" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depressn_main);

        qns=findViewById(R.id.ques) ;
        ansa=findViewById(R.id.ans_a) ;
        ansb=findViewById(R.id.ans_b) ;
        ansc=findViewById(R.id.ans_c) ;
        ansd=findViewById(R.id.ans_d) ;
        submit=findViewById(R.id.submitbtn) ;

        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        submit.setOnClickListener(this);

        loadnewqn();
    }



    @Override
    public void onClick(View view) {


        ansa.setBackgroundColor(Color.WHITE);
        ansb.setBackgroundColor(Color.WHITE);
        ansc.setBackgroundColor(Color.WHITE);
        ansd.setBackgroundColor(Color.WHITE);
        Button clickbtn=(Button) view ;
        if(clickbtn.getId()==R.id.submitbtn){


           score=score + Integer.parseInt(questionans.ans_value[currentqnindex][pos][1]) ;

            currentqnindex++ ;
            loadnewqn();

        }
        else
        {
            selectedans=clickbtn.getText().toString() ;
            if(clickbtn.getId()==R.id.ans_a)
                pos=0 ;
            if(clickbtn.getId()==R.id.ans_b)
                pos=1 ;
            if(clickbtn.getId()==R.id.ans_c)
                pos=2 ;
            if(clickbtn.getId()==R.id.ans_d)
                pos=3 ;

            clickbtn.setBackgroundColor(Color.GREEN);
        }

    }
    void loadnewqn() {

        if(currentqnindex==questionans.question.length){
          finishquiz() ;
          return;
        }
        qns.setText(questionans.question[currentqnindex]);
        ansa.setText(questionans.choices[currentqnindex][0]);
        ansb.setText(questionans.choices[currentqnindex][1]);
        ansc.setText(questionans.choices[currentqnindex][2]);
        ansd.setText(questionans.choices[currentqnindex][3]);
    }
    void finishquiz(){

        String passStatus="" ;
        if(score>totalqns*0.60 )
        {
            Toast.makeText(this, "We recommend you to have a talk with our Experts", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(depressn_main.this, councellor.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Follow our activities", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(depressn_main.this, SixthEighthGroup.class);
            startActivity(intent);
        }

    }

}