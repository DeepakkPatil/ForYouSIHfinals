package com.aaks32173.sih2022new.chatbot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaks32173.sih2022new.*
import com.aaks32173.sih2022new.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chatbotsenior.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var  auth:FirebaseAuth
    private lateinit var database:DatabaseReference
    var tempmsg:String=""

    var count :Int=0

    private val adapterChatBot = AdapterChatBot()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbotsenior)
        val question = arrayOf(
            "How are you feeling Today?",
            "What is your favourite instrument?",
            "Who is your favourite singer?",
            "Who is your favourite band?",
             ""
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.2.107:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIService::class.java)

        rvChatList.layoutManager = LinearLayoutManager(this)
        rvChatList.adapter = adapterChatBot

        adapterChatBot.addChatToList(ChatModel("Hello User"!!, true))






            btnSend.setOnClickListener {
                if(etChat.text.isNullOrEmpty()){
                    Toast.makeText(this@MainActivity, "Please enter a text", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                adapterChatBot.addChatToList(ChatModel(etChat.text.toString()))
                tempmsg=etChat.text.toString()


                if(count<question.size) {


                    adapterChatBot.addChatToList(ChatModel(question[count]!!, true))
                    apiService.chatWithTheBit(etChat.text.toString()).enqueue(callBacks)
                    if(etChat.text.toString()=="sad"){

                        val a = Intent(this, depressn_main::class.java)
                        startActivity(a)
                    }
                    etChat.text.clear()
                    Toast.makeText(this@MainActivity, count.toString(), Toast.LENGTH_LONG)
                        .show()
                    count++


                }
                else{
                    apiService.chatWithTheBit(etChat.text.toString()).enqueue(callBack)
                    etChat.text.clear()
                }
            }












    }


    private val callBacks = object  : Callback<ChatResponse>{
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if(response.isSuccessful &&  response.body()!= null) {
//                adapterChatBot.addChatToList(ChatModel(response.body()!!.chatBotReply, true))
                val i = response.body()!!.sentiment
                Toast.makeText(this@MainActivity, i.toString(), Toast.LENGTH_LONG)
                    .show()


                if (i.toString() == "0") {

                    increasecounter(tempmsg)

                }

            }


        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            Toast.makeText(this@MainActivity, "Something went wrong2", Toast.LENGTH_LONG).show()
        }

    }



    private val callBack = object  : Callback<ChatResponse>{
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if(response.isSuccessful &&  response.body()!= null){
                adapterChatBot.addChatToList(ChatModel(response.body()!!.chatBotReply, true))
                val i = response.body()!!.sentiment
                Toast.makeText(this@MainActivity,i.toString(), Toast.LENGTH_LONG)
                    .show()


                if (i == "0") {
                    increasecounter(tempmsg)

                }
//                else if(i=="1") {
//                    count=count+1
//                    Toast.makeText(this@MainActivity, count.toString(), Toast.LENGTH_LONG)
//                        .show()
//
//                }


//                if(count==2){
//                    Toast.makeText(this@MainActivity, count.toString(), Toast.LENGTH_LONG).show()
//                    gotodepressionmain()
//
//                }
                if(response.body()!!.chatBotReply=="you can try relaxing activities "){
                    Toast.makeText(this@MainActivity, "intent to sleep schedule", Toast.LENGTH_LONG).show()
                    gotorelaxingactivityhigher()

                }
            }
            else{


                adapterChatBot.addChatToList(ChatModel("try again"!!, true))


            }



        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            Toast.makeText(this@MainActivity, "Something went wrong2", Toast.LENGTH_LONG).show()
        }

    }


    fun gotorelaxingactivityhigher() {
        val a = Intent(this, com.aaks32173.sih2022new.relaxingActivityHigher::class.java)
        startActivity(a)
    }


    private fun increasecounter(desc:String) {


//        val email = FirebaseAuth.getInstance().currentUser?.email
        val reference1 = FirebaseDatabase.getInstance().reference.child("UserInfo/amruti@gmail,com")
//                .child(encodeUserEmail(email.toString()).toString())
        reference1.addListenerForSingleValueEvent(object : ValueEventListener {
            var progress: String? = null
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                progress =
                    dataSnapshot.child("chat").value.toString()

                reference1.child("chat").setValue(progress+desc)

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }




}