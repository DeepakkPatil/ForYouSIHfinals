package com.aaks32173.sih2022new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AutoScrollAdapter extends RecyclerView.Adapter<AutoScrollAdapter.ViewHolder> {

    View view;
    Context context;
    static String[] quotes;

    public AutoScrollAdapter(Context context){
        this.context = context;
        quotes = new String[]{"There are so many beautiful things to be Happy\uD83D\uDE04", "Life is a gift ,never forget to enjoy every moment you are in", "You are blessed","You have so may ears to share your thoughts to.. ", "Love the way you see yourself","There are so many beautiful things to be Happy\uD83D\uDE04", "Life is a gift ,never forget to enjoy every moment you are in", "You are blessed","You have so may ears to share your thoughts to.. "};

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                view = LayoutInflater.from(context).inflate(R.layout.scroll_layout,parent,false);
                  return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.position_tv.setText(quotes[position]);


    }

    @Override
    public int getItemCount() {
        return quotes.length ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView position_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            position_tv = itemView.findViewById(R.id.position_tv);
        }
    }
}