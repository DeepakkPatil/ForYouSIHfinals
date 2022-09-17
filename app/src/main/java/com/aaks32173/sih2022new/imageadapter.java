package com.aaks32173.sih2022new;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class imageadapter extends RecyclerView.Adapter<imageadapter.imageviewholder> {

    private List<imagemodel> imageList ;
    private ViewPager2 viewPager2 ;

    public imageadapter(List<imagemodel> imageList, ViewPager2 viewPager2) {
        this.imageList = imageList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_quotes_container,parent,false) ;
        return new imageviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageviewholder holder, int position) {
    holder.imageView.setImageResource(imageList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class imageviewholder extends RecyclerView.ViewHolder{

        RoundedImageView imageView;

        public imageviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView) ;
        }
    }
}
