package com.aaks32173.sih2022new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<listitem>listitems ;
    private Context context ;
   onItemClickListener listener;

    public interface onItemClickListener{
        void onClickItem(View v,int positon);

    }

    public MyAdapter(List<listitem> listitems,onItemClickListener listener ) {
        this.listitems = listitems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view= LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.chec,parent,false);
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View v =layoutInflater.inflate(R.layout.chec, null);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      listitem listitemm = listitems.get(position) ;
      holder.textViewname.setText(listitemm.getName());

        holder.textViewcal.setText(listitemm.getCal());



    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewname;
        public TextView textViewcal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewname=(TextView)itemView.findViewById(R.id.foodname) ;
            textViewcal=(TextView)itemView.findViewById(R.id.calories) ;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClickItem(view, getAdapterPosition());
        }

    }
    }

