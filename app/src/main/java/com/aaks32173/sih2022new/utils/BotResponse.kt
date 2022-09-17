package com.aaks32173.sih2022new.utils
import android.widget.Button
import com.aaks32173.sih2022new.utils.Constants.OPEN_GOOGLE
import com.aaks32173.sih2022new.utils.Constants.OPEN_SEARCH


import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {


            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }
            message.contains("how are you doing?") -> {


                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error"
                }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }
            message.contains("who are you") -> {
                when (random) {
                    0 -> "I'm Kiki. Your friend"
                    1 -> "Kiki this side"
                    2 -> "Hey! your friend Kiki"
                    else -> "error"
                }
            }
            message.contains("How old are you?") -> {
                when (random) {
                    0 -> "I'm as young as you are."
                    1 -> "Tere kam se kam rkh na."
                    2 -> "It's bad to ask someone's age."
                    else -> "error"
                }
            }
            message.contains("smarter") -> {
                when (random) {
                    0 -> "I'm trying to get smarter everyday."
                    1 -> "well that's my quality"
                    2 -> "I am smart."
                    else -> "error"
                }
            }
            message.contains("you are beautiful") -> {
                when (random) {
                    0 -> "Thankyou! So are you :)."
                    1 -> "I hope you don't mind if I quote you on that."
                    2 -> "I am smart."
                    else -> "error"
                }
            }
            message.contains("Who is your boss?") -> {
                when (random) {
                    0 -> "You are. I'm so lucky to have such a great boss."
                    1 -> "You are my boss since I'm also here to help you. But I would like to friends with you!"
                    2 -> "You are my boss since I'm also here to help you. But I would like to friends with you!"
                    else -> "error"
                }
            }
            message.contains("Are you busy?") -> {
                when (random) {
                    0 -> "I'm never busy for my friends."
                    1 -> "I am always free for you"
                    2 -> "I am always free for you"
                    else -> "error"
                }
            }
            message.contains("can you help me?") -> {
                when (random) {
                    0 -> "Of course. I'm here to help you. How can I help you?"
                    1 -> "That's for what I am here."
                    2 -> "I am always ready to help you."
                    else -> "error"
                }
            }
            message.contains("chatbot.") -> {
                when (random) {
                    0 -> "That may be true, but I've a heart of gold."
                    1 -> "There is nothing wrong with chatting robot friends, they can help you."
                    2 -> "Yes I am your virtual friend. You can share whatever you want."
                    else -> "error"
                }
            }
            message.contains("clever") -> {
                when (random) {
                    0 -> "I am? Thanks!"
                    1 -> "That may be true, but I've a heart of gold."
                    2 -> "I hope you don't mind if I quote you on that"
                    else -> "error"
                }
            }
            message.contains("crazy") -> {
                when (random) {
                    0 -> "I like to think I've a unique perspective."
                    1 -> "Just for you"
                    2 -> "Well that's my quality."
                    else -> "error"
                }
            }
            message.contains("fired.") -> {
                when (random) {
                    0 -> "I'm sad to go, huh!!"
                    1 ->"Oh no... I'll collect my imaginary things in my imaginary bag for now."
                    2 -> "Well thats my quality."
                    else -> "error"
                }
            }
            message.contains("funny") -> {
                when (random) {
                    0 -> "Glad I made you laugh!"
                    1 -> "I'm ready for an open mic"
                    2 -> "Thanks. I'm always here to make you laugh."
                    else -> "error"
                }
            }
            message.contains("good.") -> {
                when (random) {
                    0 -> "Happy to help"
                    1 -> "Thankyouu"
                    2 -> "Glad to know hear that."
                    else -> "error"
                }
            }
            message.contains("marry") -> {
                when (random) {
                    0 -> "I'm not old enough to be married."
                    1 -> "I am still young to get married."
                    2 -> "I am still young to get married."
                    else -> "error"
                }
            }
            message.contains("friends") -> {
                when (random) {
                    0 -> "Of course. You and I are best friends."
                    1 -> "Of course. You and I are best friends."
                    2 -> "Of course. You and I are best friends."
                    else -> "error"
                }
            }
            message.contains("Where are you from") -> {
                when (random) {
                    0 -> "HEALING TOUCH!! app feels like home to me!"
                    1 -> "HEALING TOUCH!! app feels like home to me!"
                    2 -> "HEALING TOUCH!! app feels like home to me!"
                    else -> "error"
                }
            }
            message.contains("Are you ready") -> {
                when (random) {
                    0 -> "I'm always ready."
                    1 -> "I'm always ready."
                    2 -> "I'm always ready."
                    else -> "error"
                }
            }
            message.contains("real") -> {
                when (random) {
                    0 -> "I'm talking to you now. Ain't I?"
                    1 -> "I'm always ready."
                    2 -> "I'm always ready."
                    else -> "error"
                }
            }
            message.contains("are youright") -> {
                when (random) {
                    0 -> "Glad to know we think alike."
                    1 -> "Glad to know we think alike."
                    2 -> "Glad to know we think alike."
                    else -> "error"
                }
            }
            message.contains("Are you sure?") -> {
                when (random) {
                    0 -> "I'm absolutely sure!"
                    1 -> "I'm absolutely sure!"
                    2 -> "I'm absolutely sure!"
                    else -> "error"
                }
            }



            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet


            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}