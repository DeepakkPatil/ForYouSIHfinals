package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class relaxingHobies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing_hobies);
        ImageButton hoby1 = findViewById(R.id.btn1);
        hoby1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies1);
            }
        });
        ImageButton hoby2 = findViewById(R.id.btn2);
        hoby2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies2);
            }
        });
        ImageButton hoby3 = findViewById(R.id.btn3);
        hoby3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies3);
            }
        });
        ImageButton hoby4 = findViewById(R.id.btn4);
        hoby4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies4);
            }
        });
        ImageButton hoby5 = findViewById(R.id.btn5);
        hoby5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies5);
            }
        });
        ImageButton hoby6 = findViewById(R.id.btn6);
        hoby6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies6);
            }
        });
        ImageButton hoby7 = findViewById(R.id.btn7);
        hoby7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies7);
            }
        });
        ImageButton hoby8 = findViewById(R.id.btn8);
        hoby8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies8);
            }
        });
        ImageButton hoby9 = findViewById(R.id.btn9);
        hoby9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies9);
            }
        });
        ImageButton hoby10 = findViewById(R.id.btn10);
        hoby10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, R.layout.popup_windowhobbies10);
            }
        });}
    public void onButtonShowPopupWindowClick(View view, int w) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(w, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}