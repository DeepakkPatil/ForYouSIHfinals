package com.aaks32173.sih2022new

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aaks32173.sih2022new.databinding.ActivityWetimeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class WetimeActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityWetimeBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<wetime>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityWetimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<wetime>()
        getUserData()



    }

    private fun getUserData() {

        lateinit var auth: FirebaseAuth
        val today =LocalDate.now()
        val email = intent.getStringExtra("email").toString()
        val emaill = FirebaseAuth.getInstance().currentUser?.email
        val encodedemmail=encodeUserEmail(email.toString())

        dbref = FirebaseDatabase.getInstance().getReference("UserInfo/"+encodedemmail+"/WeTime/"+today.toString())

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(wetime::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerview.adapter = wetimeadapter(userArrayList,email.toString())



                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
    private fun encodeUserEmail(email: String): String? {
        return email.replace(".", ",")
    }
}