package com.aaks32173.sih2022new

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aaks32173.sih2022new.databinding.ActivityTodoBinding
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class todo  : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var dbref1 : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var rewrd : TextView
    private lateinit var trending : TextView
    private lateinit var week : Button
    private lateinit var userArrayList : ArrayList<usertodo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trending = findViewById(R.id.trending)

        val email = FirebaseAuth.getInstance().currentUser?.email
        val encodedemmail=encodeUserEmail(email.toString())

        rewrd=findViewById(R.id.reward)
        week=findViewById(R.id.week)
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<usertodo>()


        week.setOnClickListener {

            val a = Intent(this, weektodo::class.java)

                .putExtra("email",encodeUserEmail(email.toString()))
            startActivity(a)
        }
        setreward(encodedemmail.toString())
        trend()
        getUserData()

    }

    private fun getUserData() {

        val email = intent.getStringExtra("email").toString()

        val today= LocalDate.now()
        dbref = FirebaseDatabase.getInstance().getReference("UserInfo/"+email+"/TODO/"+today)

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(usertodo::class.java)
                        userArrayList.add(user!!)


                    }


                    userRecyclerview.adapter = todoadapter(userArrayList)



                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
    private fun setreward(email :String) {

        val reference1 =FirebaseDatabase.getInstance().reference.child("UserInfo").child(email)

        reference1.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val rew= dataSnapshot.child("rewards").getValue().toString();
                rewrd.setText("Rewards : "+rew+" \uD83C\uDF1F")
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }
    private fun encodeUserEmail(email: String): String? {
        return email.replace(".", ",")
    }
    private fun trend(){

        val reference2 =FirebaseDatabase.getInstance().reference.child("trending")

        reference2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val exercise= dataSnapshot.child("exercise").getValue().toString().toInt();
                val music= dataSnapshot.child("music&podcast").getValue().toString().toInt();
                val relaxingactivities= dataSnapshot.child("relaxingactivities").getValue().toString().toInt();
                val wetime= dataSnapshot.child("wetime").getValue().toString().toInt();
                val maximum = maxOf(exercise, relaxingactivities, wetime,music)
                if(maximum==exercise)
                    trending.text = "Exercise"
                else if(maximum==music)
                    trending.text = "Music"
                else if(maximum == relaxingactivities)
                    trending.text = "RelaxingActivity"
                else
                    trending.text = "WeTime"
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }

}