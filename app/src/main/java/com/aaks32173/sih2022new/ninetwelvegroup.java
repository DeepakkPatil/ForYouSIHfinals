package com.aaks32173.sih2022new;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aaks32173.sih2022new.ui.chatbot;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
public class ninetwelvegroup extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser Currentuser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference2;
    Button recommondtaion;
    ExtendedFloatingActionButton bot;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout :
            {
                Intent intent = new Intent(ninetwelvegroup.this, LoginActivity.class);
                startActivity(intent);
                return true ;
            }


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        MenuInflater menuInflater=getMenuInflater() ;
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninetwelvegroup);
        String [] interests ={"exercisee", "musicpodcast", "nutrition","relaxinactivities", "wetimee"};
        recommondtaion = findViewById(R.id.recommended);
        ImageButton menstural = findViewById(R.id.menstural);
        ImageButton relaxing = findViewById(R.id.relaxing);
        ImageButton flextime = findViewById(R.id.flextime);
        ImageButton gtbt = findViewById(R.id.gtbt);
        ImageButton podcast = findViewById(R.id.podcasts);
        ImageButton wetime = findViewById(R.id.wetime);
        ImageButton music = findViewById(R.id.music);
        ImageButton todo = findViewById(R.id.todo);
        ImageButton sleep = findViewById(R.id.sleept);
        ImageButton convo = findViewById(R.id.convo6to8);
        ImageButton diet = findViewById(R.id.diet);
        ImageButton nowcast = findViewById(R.id.nowcast6to8);
        ImageButton ptodo = findViewById(R.id.ptodo);
        TextView tv = findViewById(R.id.textView9);
        recommondtaion = findViewById(R.id.recommended);
        bot = findViewById(R.id.chatbot_6_12);
        recommondtaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =  new Intent(ninetwelvegroup.this,recommended.class);
                a.putExtra("email", encodeUserEmail(Currentuser.getEmail().toString()));
                startActivity(a);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        Currentuser = mAuth.getCurrentUser();
        LocalDate td=LocalDate.now();
        firebaseDatabase = FirebaseDatabase.getInstance();
        recommondtaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =  new Intent(ninetwelvegroup.this,recommended.class);
                a.putExtra("email", encodeUserEmail(Currentuser.getEmail().toString()));
                startActivity(a);
            }
        });
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("UserIntrest");
        String [] intrestspoint = new String[9];

//        String [] interests ={"exercise", "dancing", "indoorgames","music", "drawing", "travel", "reading", "sports", "yoga"};

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String exercise = snapshot.child(interests[0]).getValue().toString();
                intrestspoint[0]= (exercise);
                String dancing = snapshot.child(interests[1]).getValue().toString();
                intrestspoint[1]= (dancing);
                String indoorgames = snapshot.child(interests[2]).getValue().toString();
                intrestspoint[2]= (indoorgames);
                String music = snapshot.child(interests[3]).getValue().toString();
                intrestspoint[3]= (music);
                String drawing = snapshot.child(interests[4]).getValue().toString();
                intrestspoint[4]= (drawing);
//                    String travel = snapshot.child(interests[5]).getValue().toString();
//                    intrestspoint[5]= (travel);
//                    String reading = snapshot.child(interests[6]).getValue().toString();
//                    intrestspoint[6]= (reading);
//                    String sports = snapshot.child(interests[7]).getValue().toString();
//                    intrestspoint[7]= (sports);
//                    String yoga = snapshot.child(interests[8]).getValue().toString();
//                    intrestspoint[8]= (yoga);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        String encodedemmail=encodeUserEmail(email.toString());



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("TODO");
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("info");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String exercise = snapshot.child("gender").getValue().toString();
                if(exercise.equals("Male"))
                {
                    menstural.setVisibility(View.GONE);
                    tv.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean todotoday = dataSnapshot.child(td.toString()).exists();

                if(!todotoday){
                    String[] wetime={"exercise",
                            "music&podcast",
                            "nutrition",
                            "relaxinactivities",
                            "wetime"};

                    databaseReference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("TODO");

                    for (int i = 1; i <= 5; i++) {
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("intrest").setValue(intrestspoint[i-1]);
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("activity").setValue(wetime[i-1]);
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("date").setValue(td.toString());
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("progress").setValue("0");
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail()));

        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean todotoday = dataSnapshot.child("WEEKTODO").exists();

                if(!todotoday){
                    String[] wetime={"exercise",
                            "music&podcast",
                            "nutrition",
                            "relaxinactivities",
                            "wetime"};
                    DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("WEEKTODO");


                    for (int i = 1; i <= 5; i++) {



                        reference4.child(""+i).child("activity").setValue(wetime[i-1]);
                        reference4.child(""+i).child("progress").setValue("0");

                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        DatabaseReference reference12 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("WeTime");
        reference12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean todotoday = dataSnapshot.child(td.toString()).exists();
                if(!todotoday){
                    String[][] wetime={
                            {"1","Go for a walk with your parents"},
                            {"2","Have dinner with family"},
                            {"3","Ask your family members to tell you a story before bed."},
                            {"4","Plant a seed with the help with your elder and water it daily"},
                            {"5","Ask your mom to let you help in kitchen. Do as she instructs."},
                            {"6","Tell your mom how good she looks."},
                            {"7","Take blessings from your elders."},
                            {"8","Ask your parents to help you with your TODO list "},
                            {"9","Thank god for givng you such a wonderful family."},
                            {"10","Have a small dance party with songs played on your phone with your family."},
                            {"11","Maintain a piggy bank.Save money and add to it."},
                            {"12","Dress up and join your elders to temples, mosques,churches or gurudwaras"},
                            {"13","Set tables  for meals."},
                            {"14","Go to fruit or vegetable market with your elders."},
                            {"15","Arrange your closet with your elders."},
                            {"16","Check your photo albums with your family."}
                    };
                    DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("BMI");
                    reference3.child("calinitial").setValue("0");
                    databaseReference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("WeTime");
                    for (int i = 1; i <= 16; i++) {
                        databaseReference2.child(td.toString()).child(""+i).child("status").setValue("False");
                        databaseReference2.child(td.toString()).child(""+i).child("description").setValue(wetime[i-1][1]);
                        databaseReference2.child(td.toString()).child(""+i).child("date").setValue(td.toString());
                        databaseReference2.child(td.toString()).child(""+i).child("id").setValue(wetime[i-1][0]);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               gotobot();
            }
        });
        ptodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotopTodo(encodedemmail);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMusic(Currentuser.getEmail().toString());
            }
        });
        wetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWetime(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTodo(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSleep();
            }
        });
        nowcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ninetwelvegroup.this,Showpost.class);
                startActivity(intent);
            }
        });

        convo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTotherapist();
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotodiet();
            }
        });
        podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPodcats(Currentuser.getEmail().toString());
            }
        });
        flextime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFlex();
            }
        });
        relaxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRelaxing();
            }
        });
        menstural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenstural();
            }
        });
        gtbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetogtbt();
            }
        });}

    private void gotobot() {
        Intent intent = new Intent(ninetwelvegroup.this, com.aaks32173.sih2022new.chatbot.MainActivity.class);
        startActivity(intent);
    }

    private void gotoSleep() {
        Intent intengt = new Intent(ninetwelvegroup.this , sleepTracker.class);
        startActivity(intengt);
    }

    private void gotoTotherapist() {
        Intent intent = new Intent(ninetwelvegroup.this, councellor.class);
        startActivity(intent);
    }


    private void gotodiet() {
        Intent intent = new Intent(ninetwelvegroup.this, chekk.class);
        startActivity(intent);
    }

    private void gotoWetime(String email) {
        Intent intent = new Intent(ninetwelvegroup.this, WetimeActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    private void gotoPodcats(String email) {
        Intent intent =  new Intent(ninetwelvegroup.this, MusicPlayer.class);
        intent.putExtra("path","PodcastSixthEight");
        intent.putExtra("email", email);
        intent.putExtra("grp", "podcast");
        startActivity(intent);
    }
    private void gotoMusic(String email) {
        Intent intent = new Intent(ninetwelvegroup.this, MusicPlayer.class);
        intent.putExtra("path","MusicFourthFifth");
        intent.putExtra("email", email);
        intent.putExtra("grp", "music");
        startActivity(intent);
    }
    private void movetogtbt() {
        Intent intent =  new Intent(ninetwelvegroup.this, gtbtPanelFourthFifth.class);
        startActivity(intent);
    }
    private void gotoFlex() {
        Intent intent = new Intent(ninetwelvegroup.this, flex_time.class);
        startActivity(intent);
    }
    private void gotoRelaxing() {
        Intent intent = new Intent(ninetwelvegroup.this, relaxingActivityHigher.class);
        startActivity(intent);
    }
    private void gotoMenstural() {
        Intent intent = new Intent(ninetwelvegroup.this, MenstrualHome.class);
        startActivity(intent);
    }
    private void gotoTodo(String email) {
        Intent intent = new Intent(ninetwelvegroup.this, todo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    private void gotopTodo(String email) {
        Intent intent = new Intent(ninetwelvegroup.this, addpersoneltodo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }
}