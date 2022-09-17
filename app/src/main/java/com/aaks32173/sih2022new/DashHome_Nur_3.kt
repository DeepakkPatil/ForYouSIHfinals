package com.aaks32173.sih2022new

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDate
import java.util.*


class DashHome_Nur_3 : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    var databaseReference2: DatabaseReference? = null
    var recycler_view: RecyclerView? = null
    var autoScrollAdapter: AutoScrollAdapter? = null
    var layoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_home_nur3)




        val poemsandrhymes = findViewById<ImageButton>(R.id.poemsryhmes)
        val email = FirebaseAuth.getInstance().currentUser?.email
        recycler_view = findViewById(R.id.recycler_view)
        setRV()



        val encodedemmail=encodeUserEmail(email.toString())

        val td = LocalDate.now()

//        val reference12 = FirebaseDatabase.getInstance().reference.child("UserInfo").child(encodedemmail.toString()).child("WeTime")
//
//        reference12.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val todotoday = dataSnapshot.child(td.toString()).exists()
//                if (!todotoday) {
//                    val wetime = arrayOf(
//                        arrayOf("1", "Go for a walk with your parents"),
//                        arrayOf("2", "Have dinner with family"),
//                        arrayOf("3", "Ask your family members to tell you a story before bed."),
//                        arrayOf(
//                            "4",
//                            "Plant a seed with the help with your elder and water it daily"
//                        ),
//                        arrayOf(
//                            "5",
//                            "Ask your mom to let you help in kitchen. Do as she instructs."
//                        ),
//                        arrayOf("6", "Tell your mom how good she looks."),
//                        arrayOf("7", "Take blessings from your elders."),
//                        arrayOf("8", "Ask your parents to help you with your TODO list "),
//                        arrayOf("9", "Thank god for givng you such a wonderful family."),
//                        arrayOf(
//                            "10",
//                            "Have a small dance party with songs played on your phone with your family."
//                        ),
//                        arrayOf("11", "Maintain a piggy bank.Save money and add to it."),
//                        arrayOf(
//                            "12",
//                            "Dress up and join your elders to temples, mosques,churches or gurudwaras"
//                        ),
//                        arrayOf("13", "Set tables  for meals."),
//                        arrayOf("14", "Go to fruit or vegetable market with your elders."),
//                        arrayOf("15", "Arrange your closet with your elders."),
//                        arrayOf("16", "Check your photo albums with your family.")
//                    )
//                    databaseReference2 = FirebaseDatabase.getInstance().reference.child("UserInfo").child(encodedemmail!!).child("WeTime")
//                    for (i in 1..16) {
//                        databaseReference2!!.child(td.toString()).child("" + i).child("status")
//                            .setValue("False")
//                        databaseReference2!!.child(td.toString()).child("" + i).child("description")
//                            .setValue(
//                                wetime[i - 1][1]
//                            )
//                        databaseReference2!!.child(td.toString()).child("" + i).child("date")
//                            .setValue(td.toString())
//                        databaseReference2!!.child(td.toString()).child("" + i).child("id")
//                            .setValue(wetime[i - 1][0])
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })




        poemsandrhymes.setOnClickListener {

            val a = Intent(this, yoga::class.java)
                .putExtra("category","rhymes")
            startActivity(a)
        }

        val parent = findViewById<ImageButton>(R.id.parent)

        parent.setOnClickListener {

            val a = Intent(this, com.aaks32173.sih2022new.parent::class.java)
                .putExtra("email",encodedemmail.toString())
            startActivity(a)
        }

        val stories = findViewById<ImageButton>(R.id.stories)

        stories.setOnClickListener {

            val a = Intent(this, yoga::class.java)
                .putExtra("category","stories")
            startActivity(a)
        }
        val gtbt = findViewById<ImageButton>(R.id.gtbt)

        gtbt.setOnClickListener {

            val a = Intent(this, goodBadtouch::class.java)
            startActivity(a)
        }

        val fightyourfear = findViewById<ImageButton>(R.id.fightyourfear)

        fightyourfear.setOnClickListener {

            val a = Intent(this, yoga::class.java)
                .putExtra("category","fightyourfear")
            startActivity(a)
        }


        val gksection = findViewById<ImageButton>(R.id.gksection)

        gksection.setOnClickListener {

            val a = Intent(this, yoga::class.java)
                .putExtra("category","gksection")
            startActivity(a)
        }





        val letshaveconversation = findViewById<ExtendedFloatingActionButton>(R.id.chatbot)

        val badge = findViewById<TextView>(R.id.chatbotbadge)
        val myanim2 = AnimationUtils.loadAnimation(this, R.anim.shake)
        letshaveconversation.startAnimation(myanim2)
        badge.startAnimation(myanim2)
        letshaveconversation.setOnClickListener {

            val a =  Intent(this,com.aaks32173.sih2022new.ui.MainActivity::class.java)

            startActivity(a)


        }

        val health = findViewById<ImageButton>(R.id.health)

        health.setOnClickListener {

            val a = Intent(this, yoga::class.java)
                .putExtra("category","health1")
            startActivity(a)


        }

        val nowcast = findViewById<ImageButton>(R.id.nowcast)

        nowcast.setOnClickListener {

            val a = Intent(this, Showpost::class.java)
            startActivity(a)
        }

        val relaxingactivity =findViewById<ImageButton>(R.id.relaxingactivity)

        relaxingactivity.setOnClickListener{

            val a = Intent(this, relaxingActivityKids::class.java)
            startActivity(a)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        return super.onCreateOptionsMenu(menu)
    }

    private fun encodeUserEmail(email: String): String? {
        return email.replace(".", ",")
    }
    private fun setRV() {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view!!.layoutManager = layoutManager
        autoScrollAdapter = AutoScrollAdapter(this)
        recycler_view!!.adapter = autoScrollAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycler_view)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (layoutManager!!.findLastCompletelyVisibleItemPosition() < autoScrollAdapter!!.itemCount - 1) {
                    layoutManager!!.smoothScrollToPosition(
                        recycler_view,
                        RecyclerView.State(),
                        layoutManager!!.findLastCompletelyVisibleItemPosition() + 1
                    )
                } else if (layoutManager!!.findLastCompletelyVisibleItemPosition() < autoScrollAdapter!!.itemCount - 1) {
                    layoutManager!!.smoothScrollToPosition(recycler_view, RecyclerView.State(), 0)
                }
                run {}
            }
        }, 0, 6000)
    }

}