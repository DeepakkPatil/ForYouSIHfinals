package com.aaks32173.sih2022new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.time.LocalDate

class parentadapter(private val userList : ArrayList<parentclass> ) : RecyclerView.Adapter<parentadapter.MyViewHolder>() {


    private lateinit var database : DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        userList.sortBy { it.percent?.toInt() }

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list_re,
            parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.name.text = "Q: "+currentitem.question

        holder.ans.text ="A: "+ currentitem.ans

    }
    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.tvfirstName)

        val ans : TextView = itemView.findViewById(R.id.ans)
    }


}