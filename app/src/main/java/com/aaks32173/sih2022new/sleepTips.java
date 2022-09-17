package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class sleepTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tips);

        LinearLayout open1 = (LinearLayout) findViewById (R.id.open1);
        LinearLayout open2 = (LinearLayout) findViewById (R.id.open2);
        LinearLayout open3 = (LinearLayout) findViewById (R.id.open3);
        LinearLayout open4 = (LinearLayout) findViewById (R.id.open4);
        LinearLayout open5 = (LinearLayout) findViewById (R.id.open5);
        LinearLayout open6 = (LinearLayout) findViewById (R.id.open6);
        LinearLayout open7 = (LinearLayout) findViewById (R.id.open7);
        LinearLayout open8 = (LinearLayout) findViewById (R.id.open8);
        LinearLayout open9 = (LinearLayout) findViewById (R.id.open9);
        LinearLayout open10 = (LinearLayout) findViewById (R.id.open10);
        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window2);
            }
        });
        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window3);
            }
        });
        open3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window4);
            }
        });
        open4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window5);
            }
        });
        open5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window6);
            }
        });
        open6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window7);
            }
        });
        open7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window8);
            }
        });
        open8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window9);
            }
        });
        open9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window10);
            }
        });
        open10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonShowPopupWindowClick(v, R.layout.popup_window11);
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
}