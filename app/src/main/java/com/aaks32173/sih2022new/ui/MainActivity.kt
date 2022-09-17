package com.aaks32173.sih2022new.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaks32173.sih2022new.*
import com.aaks32173.sih2022new.data.Message
import com.aaks32173.sih2022new.utils.Constants.OPEN_GOOGLE
import com.aaks32173.sih2022new.utils.Constants.OPEN_SEARCH
import com.aaks32173.sih2022new.utils.Constants.RECEIVE_ID
import com.aaks32173.sih2022new.utils.Constants.SEND_ID
import com.aaks32173.sih2022new.utils.Time
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mainchatbot.*
import kotlinx.android.synthetic.main.welcome.*
import kotlinx.coroutines.*
import pl.droidsonroids.gif.GifImageView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    var qs = "1"
    var chatbottype = "chatbot"
//    private var mainindex=""
//   private var question=""
//   private var answer1=""
//    var answer1index=""
//    var answer2=""
//    var answer2index=""
//    var answer3=""
//    var answer3index=""


    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Kiki", "Kiki", "Kiki", "Kiki")
    lateinit var gif: GifImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainchatbot)
        ll_layout_bar.setVisibility(View.INVISIBLE)


        val name = "Hello Kiki"
        var etmsg = findViewById<EditText>(R.id.et_message)
        gif = findViewById<GifImageView>(R.id.ch)
        etmsg.setText(name)
        recyclerView()

        clickEvents()


        val random = (0..3).random()

        when (random) {
            0 -> customBotMessage("Hello! I am ${botList[random]} your friend. How are you doing today?")
            1 -> customBotMessage("Hi there ! It's nice to meet you. I'm  ${botList[random]} .I love meeting new people.")
            2 -> customBotMessage("Hello! I am ${botList[random]} ")
        }

    }

    private fun clickEvents() {

        //Send a message

        btn_send.setOnClickListener {

            gif.setVisibility(View.VISIBLE)
            sendMessage()
            et_message.setVisibility(View.INVISIBLE)
            btn_send.setVisibility(View.INVISIBLE)
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
//                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
//            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)


            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        database = FirebaseDatabase.getInstance().getReference(chatbottype.toString())
        database.child(qs).get().addOnSuccessListener {
//            Toast.makeText(applicationContext, chatbottype, Toast.LENGTH_SHORT).show()
            ll_layout_bar.setVisibility(View.VISIBLE)

            if (it.exists()) {
                val mainindex = it.child("mainindex").value.toString()
                val question = it.child("question").value.toString()
                val answer1 = it.child("answer1").value.toString()

                val answer1index = it.child("answer1index").value.toString()
                val answer2 = it.child("answer2").value.toString()

                val answer2index = it.child("answer2index").value.toString()

                val answer3 = it.child("answer3").value.toString()

                val answer3index = it.child("answer3index").value.toString()


                btn_send1.text = answer1

                btn_send2.text = answer2

                btn_send3.text = answer3







                btn_send1.setOnClickListener {

                    et_message.setText(answer1)

                    if (chatbottype == "chatbotprimary") {
                        addmessage(question, answer1)

                    }
                    if (answer1.toString() == "Happy" && chatbottype == "chatbot") {

                        gif.setImageResource(R.drawable.great_tim)
                    }
                    if (answer1.toString() == "yes" && mainindex.toString() == "14" && chatbottype == "chatbotprimary") {

                        chatbottype = "chatbot"
                    }
                    if (answer1.toString() == "yes" && mainindex.toString() == "27" && chatbottype == "chatbot") {
                        chatbottype = "chatbotprimary"
                    }
                    if (answer1.toString() == "yes" && mainindex.toString() == "13" && chatbottype == "chatbotprimary") {
                        val intent = Intent(this, councellor::class.java)
                        startActivity(intent)
                    }
                    if (answer1.toString() == "Yes" && mainindex.toString() == "22" && chatbottype == "chatbot") {
                        val intent = Intent(this, fouthFifthGroup::class.java)
                        startActivity(intent)
                    }
                    if (answer1.toString() == "Relaxing Activities " && mainindex.toString() == "3" ||mainindex.toString() == "8" && chatbottype == "chatbot") {
                        val intent = Intent(this, relaxingActivityKids::class.java)
                        startActivity(intent)
                    }


                    qs = answer1index
                    sendMessage()


                }
                btn_send2.setOnClickListener {

                    et_message.setText(answer2)

                    if (chatbottype == "chatbotprimary") {
                        addmessage(question, answer2)

                    }
                    if (answer2.toString() == "Listening to Music" && mainindex.toString() == "3"||mainindex.toString() == "8" && chatbottype == "chatbot") {

                        val intent = Intent(this, MusicPlayer::class.java)
                        intent.putExtra("path", "MusicFourthFifth")
                        startActivity(intent)
                    }

                    if (answer2.toString() == "No" && mainindex.toString() == "22" && chatbottype == "chatbot") {
                        Toast.makeText(applicationContext, "Moving  To Todo", Toast.LENGTH_SHORT)
                            .show()

                        val intent = Intent(this, todo::class.java)
                        startActivity(intent)
                    }

                    if (answer2.toString() == "Sad" && chatbottype == "chatbot") {

                        chatbottype = "chatbotprimary"

                        gif.setImageResource(R.drawable.hereforyougif)


                    }

                    qs = answer2index
                    sendMessage()
                }
                btn_send3.setOnClickListener {
                    qs = answer3index
                    if (chatbottype == "chatbotprimary") {
                        addmessage(question, answer3)

                    }
                    if (answer3.toString() == "Exercise" && mainindex.toString() == "3"||mainindex.toString() == "8" && chatbottype == "chatbot") {

                        val intent = Intent(this, flextimefourthfifth::class.java)

                        val email = FirebaseAuth.getInstance().currentUser?.email
                        intent.putExtra("email", encodeUserEmail(email.toString()))
                        startActivity(intent)
                    }
                    if (answer3.toString() == "I'll do it Later" && mainindex.toString() == "14" && chatbottype == "chatbot") {
                        Toast.makeText(
                            applicationContext,
                            "Moving  To DashBoard",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this, fouthFifthGroup::class.java)

                        startActivity(intent)
                    }
                    et_message.setText(answer3)

                    sendMessage()
                }
                GlobalScope.launch {
                    //Fake response delay
                    delay(1000)

                    withContext(Dispatchers.Main) {
                        //Gets the response

                        val response = question


                        //Adds it to our local list
                        messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                        //Inserts our message into the adapter
                        adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                        //Scrolls us to the position of the latest message
                        rv_messages.scrollToPosition(adapter.itemCount - 1)

                        //Starts Google
                        when (response) {
                            OPEN_GOOGLE -> {
                                val site = Intent(Intent.ACTION_VIEW)
                                site.data = Uri.parse("https://www.google.com/")
                                startActivity(site)
                            }
                            OPEN_SEARCH -> {
                                val site = Intent(Intent.ACTION_VIEW)
                                val searchTerm: String? = message.substringAfterLast("search")
                                site.data =
                                    Uri.parse("https://www.google.com/search?&q=$searchTerm")
                                startActivity(site)
                            }

                        }
                    }
                }
            }

        }


    }


    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }


    private fun addmessage(message: String, ans: String) {


        val email = FirebaseAuth.getInstance().currentUser?.email

        database = FirebaseDatabase.getInstance()
            .getReference("UserInfo/"+encodeUserEmail(email.toString())+"/Parent")
        val attendance = parentclass(message, ans)

        database.child(message+ans).setValue(attendance).addOnSuccessListener {
            Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()


        }



    }
    public fun encodeUserEmail(email: String): String? {
        return email.replace(".", ",")
    }

}