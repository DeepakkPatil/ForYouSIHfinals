package com.aaks32173.sih2022new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class todoadapter(private val userList : ArrayList<usertodo> ) : RecyclerView.Adapter<todoadapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        userList.sortBy {
            it.intrest?.toInt()
            it.progress?.toInt()
        }
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list,
            parent,false)


        return MyViewHolder(itemView)



    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

//        holder.cname.text = currentitem.cname
//if (currentitem.name=)

        holder.name.text = currentitem.activity

//        holder.pos_t.text=(position+1).toString()
//        holder.tv.text = currentitem.progress

        val t = Timer()
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                holder.pb!!.progress = currentitem.progress!!.toInt()


            }
        }
        t.schedule(tt, 0, 10)

    }

    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //        val cname : TextView = itemView.findViewById(R.id.cname)
        val name : TextView = itemView.findViewById(R.id.tvfirstName)

        val pb : ProgressBar = itemView.findViewById(R.id.pb) as ProgressBar


//        val tv : TextView = itemView.findViewById(R.id.tv)

//        val pos_t : TextView = itemView.findViewById(R.id.pos_t)






    }


}