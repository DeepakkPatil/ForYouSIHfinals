package com.aaks32173.sih2022new

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class addpersoneltodo : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var firebaseUserID: String = ""
    private lateinit var subButton : Button

    private lateinit var stodo : Button
    lateinit var title : EditText
    private lateinit var desc : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpersoneltodo)

        auth = FirebaseAuth.getInstance()

        stodo = findViewById(R.id.showtodo)

        subButton = findViewById(R.id.submitbutton)
//        title= findViewById(R.id.title)
        desc= findViewById(R.id.desc)
        title= findViewById(R.id.title)

        subButton.setOnClickListener {
            registerUser()
        }


        stodo.setOnClickListener {

            val email = FirebaseAuth.getInstance().currentUser?.email
            val a = Intent(this, personeltodo::class.java)
                .putExtra("email",encodeUserEmail(email.toString()))
            startActivity(a)
        }
    }

    private fun registerUser() {

        val description: String=desc.text.toString()

        val tit: String=title.text.toString()
      if (description.isBlank() ||tit.isBlank()) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }
//        Toast.makeText(this,title,Toast.LENGTH_SHORT).show()

        val email = FirebaseAuth.getInstance().currentUser?.email

        database = FirebaseDatabase.getInstance().getReference("UserInfo/"+encodeUserEmail(email.toString())+"/PersonalTodo")
        val attendance = ptodo(tit,description.toString())

        database.child(tit+description).setValue(attendance).addOnSuccessListener {
            Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()

           desc.setText("")
            title.setText("")

        }


    }

    private fun encodeUserEmail(email: String): String? {
        return email.replace(".", ",")
    }


//    private fun increasecounter() {
//        Toast.makeText(applicationContext, "Submitted1 successfully", Toast.LENGTH_SHORT).show()
//
//        val email = FirebaseAuth.getInstance().currentUser?.email
//        val reference1 =
//            FirebaseDatabase.getInstance().reference.child("UserInfo/dee@gmail,com")
////                .child(encodeUserEmail(email.toString()).toString())
//        reference1.addListenerForSingleValueEvent(object : ValueEventListener {
//            var progress: String? = null
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                progress =
//                    dataSnapshot.child("chat").value.toString()
//                Toast.makeText(applicationContext, "Submitted2 successfully", Toast.LENGTH_SHORT).show()
//
//                val description: String=desc.text.toString()
//
//                val tit: String=title.text.toString()
//                reference1.child("chat").setValue(progress+description+tit)
//
//            Toast.makeText(applicationContext, "Submitted 3successfully", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }


}