package com.aaks32173.sih2022new

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aaks32173.sih2022new.databinding.ActivityParentBinding
import com.aaks32173.sih2022new.databinding.ActivityWetimeBinding
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class parent  : AppCompatActivity() {

    private lateinit var binding: ActivityParentBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<parentclass>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityParentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<parentclass>()
        getUserData()



    }

    private fun getUserData() {
        val email = intent.getStringExtra("email").toString()
//        Toast.makeText(applicationContext, email, Toast.LENGTH_SHORT).show()

        dbref = FirebaseDatabase.getInstance().getReference("UserInfo/"+email+"/Parent")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(parentclass::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerview.adapter = parentadapter(userArrayList)



                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}