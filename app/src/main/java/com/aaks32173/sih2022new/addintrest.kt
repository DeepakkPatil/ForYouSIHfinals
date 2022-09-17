package com.aaks32173.sih2022new

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class addintrest : AppCompatActivity() {
    private lateinit var age: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var firebaseUserID: String = ""
    private lateinit var subButton: Button
    //    lateinit var title : EditText
//    private lateinit var desc : EditText

    private lateinit var exer: EditText

    private lateinit var wet: EditText

    private lateinit var relact: EditText

    private lateinit var nut: EditText

    private lateinit var mus: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addintrest)

        auth = FirebaseAuth.getInstance()
        val email = FirebaseAuth.getInstance().currentUser?.email

        val encodedemmail = encodeUserEmail(email.toString())
        subButton = findViewById(R.id.submitbutton)
//        title= findViewById(R.id.title)
//        desc= findViewById(R.id.desc)
        exer = findViewById(R.id.exercise)
        wet = findViewById(R.id.wetime)
        relact = findViewById(R.id.relaxingactivity)
        nut = findViewById(R.id.nutrition)
        mus = findViewById(R.id.musicpodcast)
        age = intent.getStringExtra("age").toString()
        Toast.makeText(applicationContext, age, Toast.LENGTH_SHORT).show()
        subButton.setOnClickListener {
            registerUser(encodedemmail.toString())
        }
    }

    private fun registerUser(email: String) {
//        val date= Calendar.getInstance()
//
//        val year=date.get(Calendar.YEAR).toString()
//         val datee= date.get(Calendar.DATE).toString()
//
//        val Status:String="False"
//        val title :String= title.text.toString()
//        val description: String=desc.text.toString()

        val exerc: String = exer.text.toString()

        val wett: String = wet.text.toString()

        val rel: String = relact.text.toString()

        val nutt: String = nut.text.toString()

        val music: String = mus.text.toString()
        if (exerc.isBlank() || wett.isBlank() || rel.isBlank() || nutt.isBlank() || music.isBlank()) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }
//        Toast.makeText(this,title,Toast.LENGTH_SHORT).show()

//val d= datee+year.toString()
        database = FirebaseDatabase.getInstance().getReference("UserInfo/" + email)
        val intrest = intrestclass(exerc, music, rel, nutt, wett)

        database.child("UserIntrest").setValue(intrest).addOnSuccessListener {
            Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
            val a = Intent(this, Details::class.java)

            a.putExtra("age", age)

            startActivity(a)
        }
    }

    private fun encodeUserEmail(email: String): String? {
        return email.replace(".", ",")
    }
}
