package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.net.URL;

public class councellor extends AppCompatActivity {

    TextView counc_c_1,counc_w_1,counc_c_2,counc_c_3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_councellor);

        counc_c_1 = findViewById(R.id.contact1) ;
        counc_c_2 = findViewById(R.id.contact2) ;
        counc_c_3 = findViewById(R.id.contact3) ;
        counc_w_1=findViewById(R.id.whatsapp1);
        counc_w_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movetoWats();
            }
        }) ;

        counc_c_1.setMovementMethod(LinkMovementMethod.getInstance());
        counc_c_2.setMovementMethod(LinkMovementMethod.getInstance());
        counc_c_3.setMovementMethod(LinkMovementMethod.getInstance());


    }
    private void movetoWats() {
    String url="https://wa.me/+91" +
            "" +
            "9999666555" ;
        Intent i= new Intent(Intent.ACTION_VIEW) ;
        i.setData(Uri.parse(url)) ;
        startActivity((i));
    }
}