package com.aaks32173.sih2022new;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class character_quotes extends AppCompatActivity {

    private ViewPager2 viewPager2 ;
    private List<imagemodel> imagemodelList ;
    private imageadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_quotes);

    viewPager2=findViewById(R.id.viewpager2);
    imagemodelList=new ArrayList<>();
    imagemodelList.add(new imagemodel(R.drawable.back_diet));
    imagemodelList.add(new imagemodel(R.drawable.bluess));
    imagemodelList.add(new imagemodel(R.drawable.diet));
    imagemodelList.add(new imagemodel(R.drawable.poo));

    adapter=new imageadapter(imagemodelList,viewPager2);
    viewPager2.setAdapter(adapter);    }
}