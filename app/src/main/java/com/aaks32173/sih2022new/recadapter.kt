package com.aaks32173.sih2022new

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.time.LocalDate

class recadapter(private val userList : ArrayList<reco> ) : RecyclerView.Adapter<recadapter.MyViewHolder>() {


    private lateinit var database : DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        userList.sortBy { it.percent?.toInt() }
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_listrec,
            parent,false)



        return MyViewHolder(itemView)



    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]


        holder.name.text = currentitem.rec




    }



    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.tvfirstName)








    }




}