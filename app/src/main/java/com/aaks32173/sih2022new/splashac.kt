package com.aaks32173.sih2022new

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class splashac : AppCompatActivity() {
   private lateinit var hell: TextView
   private lateinit var wel: TextView
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mAuth = FirebaseAuth.getInstance()
           val currentuser = mAuth!!.currentUser
        wel = findViewById(R.id.welcome_msg)
        hell = findViewById(R.id.hello)
        Handler().postDelayed({
            if (currentuser == null) {
                val spash = Intent(this, WelcomeActivity::class.java)
                startActivity(spash)
                finish()
            } else {
                val spash = Intent(this, LoginActivity::class.java)
                startActivity(spash)
                finish()
            }
        }, spash_time.toLong())
        val myanim2 = AnimationUtils.loadAnimation(this, R.anim.anim2)
        val myanim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
        wel.startAnimation(myanim2)
        hell.startAnimation(myanim1)
    }
    companion object {
        private const val spash_time = 6000
    }
}