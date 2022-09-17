package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class relaxingQuizes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing_quizes);
        ImageButton btn1 = findViewById(R.id.quz1);
        ImageButton btn2 = findViewById(R.id.quz2);
        ImageButton btn3 = findViewById(R.id.quz3);
        ImageButton btn4 = findViewById(R.id.quz4);
        ImageButton btn5 = findViewById(R.id.quz5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb("https://www.chipublib.org/blogs/post/quiz-how-well-do-you-know-the-harry-potter-movies/");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb("https://www.seventeen.com/celebrity/celeb-quizzes/a25048028/friends-quiz-trivia/");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb("https://www.beano.com/posts/the-ultimate-stranger-things-quiz");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb("https://www.proprofs.com/quiz-school/story.php?title=bollywood-quiz_1");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb("https://www.theguardian.com/tv-and-radio/quiz/2015/apr/11/game-of-thrones-quiz");
            }
        });

    }
    public void openWeb(String url)
    {
        Intent intent = new Intent(relaxingQuizes.this, webView.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}