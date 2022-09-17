package com.aaks32173.sih2022new

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.AccessController.getContext
import java.time.LocalDate

class todoadapterp(private val userList : ArrayList<ptodo> ,val email :String) : RecyclerView.Adapter<todoadapterp.MyViewHolder>() {


    private lateinit var database : DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        userList.sortBy { it.percent?.toInt() }
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list2,
            parent,false)



        return MyViewHolder(itemView)



    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]



        holder.name.text = currentitem.title

        holder.desc.text = currentitem.descripton


        holder.btndelete.setOnClickListener() {


            database = FirebaseDatabase.getInstance().getReference("UserInfo/"+email+"/PersonalTodo")


            database.child(currentitem.title.toString()+currentitem.descripton.toString()).removeValue().addOnSuccessListener {


            }

        }


    }

    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //        val cname : TextView = itemView.findViewById(R.id.cname)
        val name : TextView = itemView.findViewById(R.id.title)

        val desc : TextView = itemView.findViewById(R.id.desc)


        val btndelete : Button = itemView.findViewById(R.id.btndelete)






    }


}