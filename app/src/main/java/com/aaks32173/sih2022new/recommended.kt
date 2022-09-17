package com.aaks32173.sih2022new

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aaks32173.sih2022new.databinding.ActivityRecommendedBinding
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class recommended  : AppCompatActivity() {

    private lateinit var binding: ActivityRecommendedBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<reco>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)
        val email = intent.getStringExtra("email").toString()
        userArrayList = arrayListOf<reco>()
        getUserData(email.toString())
    }

    private fun getUserData(email:String ) {
        dbref = FirebaseDatabase.getInstance().getReference("UserInfo/"+email+"/recommondation")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){




                        val user = userSnapshot.getValue(reco::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerview.adapter = recadapter(userArrayList)



                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}