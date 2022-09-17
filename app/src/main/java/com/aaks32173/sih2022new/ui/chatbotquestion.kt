package com.aaks32173.sih2022new.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aaks32173.sih2022new.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class chatbotquestion : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var firebaseUserID: String = ""
    private lateinit var subButton : Button
    lateinit var mainindex : EditText
    private lateinit var question : EditText

    lateinit var answer1 : EditText
    private lateinit var answer1index: EditText

    lateinit var answer2 : EditText
    private lateinit var answer2index: EditText

    lateinit var answer3 : EditText
    private lateinit var answer3index: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbotquestion)


        subButton = findViewById(R.id.submitbutton)
        mainindex= findViewById(R.id.mainindex)
        question= findViewById(R.id.question)

        answer1= findViewById(R.id.answer1)
        answer1index= findViewById(R.id.answer1index)
        answer2= findViewById(R.id.answer2)
        answer2index= findViewById(R.id.answer2index)
        answer3= findViewById(R.id.answer3)
        answer3index= findViewById(R.id.answer3index)



        subButton.setOnClickListener {
            registerUser()
            mainindex.setText("")
            question.setText("")

            answer1.setText("")

            answer1index.setText("")
            answer2.setText("")

            answer2index.setText("")
            answer3.setText("")

            answer3index.setText("")
        }
    }

    private fun registerUser() {

        val mainindex :String= mainindex.text.toString()
        val question: String=question.text.toString()

        val answer1 :String= answer1.text.toString()
        val answer1index: String=answer1index.text.toString()

        val answer2 :String= answer2.text.toString()
        val answer2index: String=answer2index.text.toString()

        val answer3 :String= answer3.text.toString()
        val answer3index: String=answer3index.text.toString()

        if (mainindex.isBlank() || question.isBlank() || answer1.isBlank() || answer1index.isBlank() || answer2.isBlank() || answer2index.isBlank() || answer3.isBlank() || answer3index.isBlank()) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }
        database = FirebaseDatabase.getInstance().getReference("chatbot")
        val attendance = chatbot(mainindex,question,answer1,answer1index,answer2,answer2index,answer3,answer3index)

        database.child(mainindex).setValue(attendance).addOnSuccessListener {
            Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()


        }


    }}