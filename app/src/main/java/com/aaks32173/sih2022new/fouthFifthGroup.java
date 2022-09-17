package com.aaks32173.sih2022new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class fouthFifthGroup extends AppCompatActivity {
    FirebaseAuth mAuth;
    long previousTime;
    FirebaseUser Currentuser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout :
            {
                Intent intent = new Intent(fouthFifthGroup.this, LoginActivity.class);
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

    RecyclerView recycler_view;
    LinearLayoutManager layoutManager;
    AutoScrollAdapter autoScrollAdapter;

    DatabaseReference databaseReference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fouth_fifth_group);
        ImageButton button5=(ImageButton)findViewById(R.id.imageButton5);
        String [] interests ={"exercisee", "musicpodcast", "nutrition","relaxinactivities", "wetimee"};


        recycler_view = findViewById(R.id.recycler_view_3to5);
        setRV();

        ImageButton todobtn = findViewById(R.id.todobtn);
        ImageButton music = findViewById(R.id.music);
        ImageButton podcasts = findViewById(R.id.podcasts);
        ImageButton gtbt = findViewById(R.id.gtbt);
        ImageButton relaxing = findViewById(R.id.relaxing);
        ImageButton menstural = findViewById(R.id.menstural);

        ImageButton chatbot = findViewById(R.id.chatbot);
        ImageButton diet =findViewById(R.id.imageButton3);
        ImageButton post =findViewById(R.id.post);

        TextView tv = findViewById(R.id.textView90);
        ImageButton wetime =findViewById(R.id.wetime);
        firebaseDatabase = FirebaseDatabase.getInstance();
        ImageButton exercise=(ImageButton)findViewById(R.id.exercise_3to5);
        ExtendedFloatingActionButton letshaveconversation = findViewById(R.id.chatbot_3to5);

                TextView badge = findViewById(R.id.chatbotbadge_3to5);
                Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.shake);
        letshaveconversation.startAnimation(myanim2);
        badge.startAnimation(myanim2);
        letshaveconversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =  new Intent(fouthFifthGroup.this,com.aaks32173.sih2022new.ui.MainActivity.class);
                startActivity(a);
            }
        });

                firebaseDatabase = FirebaseDatabase.getInstance();

        LocalDate td= LocalDate.now();
        mAuth = FirebaseAuth.getInstance();
        Currentuser = mAuth.getCurrentUser();

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

        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail()));
        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("BMI");

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("info");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String exercise = snapshot.child("gender").getValue().toString();
                if(exercise.equals("Male"))
                {
                    Toast.makeText(fouthFifthGroup.this, exercise, Toast.LENGTH_SHORT).show();
                    menstural.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
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
                    DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("WEEKTODO");

                    databaseReference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("TODO");

                    for (int i = 1; i <= 5; i++) {
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("intrest").setValue(intrestspoint[i-1]);
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("activity").setValue(wetime[i-1]);
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("date").setValue(td.toString());
                        databaseReference2.child(td.toString()).child(wetime[i-1]).child("progress").setValue("0");



                        reference4.child(wetime[i-1]).setValue("0");
                        reference4.child(wetime[i-1]).setValue("0");
                        reference4.child(wetime[i-1]).setValue("0");
                        reference4.child(wetime[i-1]).setValue("0");

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
        gtbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGtbt();
            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendiet(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
        wetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwetime(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
//        wetime.setOnClickListener(new View.OnClickListener( {
//                Intent a =  new Intent(fouthFifthGroup.this,com.aaks32173.sih2022new.ui.MainActivity.class);
//
////        Intent intent = new Intent(fouthFifthGroup.this, WetimeActivity.class).putExtra("email",encodedemmail);
//            startActivity(intent);
//        )};
        todobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentodo(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoflextime(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });

//        health.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(fouthFifthGroup.this, yoga.class);
//                intent.putExtra("category", "health");
//                startActivity(intent);
//            }
//        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMusic(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
        podcasts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPodcasts(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
        relaxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoRelaxing(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
        menstural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoMenstural(encodeUserEmail(Currentuser.getEmail().toString()));
            }
        });
//        chatbot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                movetoBot();
//            }
//        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fouthFifthGroup.this, "Feature Under Progress", Toast.LENGTH_SHORT).show();
//                openShowpost();
            }
        });

    }
    private void openShowpost() {
        Intent intent = new Intent(fouthFifthGroup.this, Showpost.class);
        startActivity(intent);
    }

    private void opentodo(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, todo.class);
     intent.putExtra("email", email);
        startActivity(intent);
    }

    private void openwetime(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, WetimeActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    private void openGtbt() {
        Intent intent = new Intent(fouthFifthGroup.this, gtbtPanelFourthFifth.class);
        startActivity(intent);
    }
    private void openMusic( String email) {
            Intent intent = new Intent(fouthFifthGroup.this, MusicPlayer.class);
            intent.putExtra("path", "MusicFourthFifth");

        intent.putExtra("email", email);
            startActivity(intent);
    }
    private void openPodcasts(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, podcasts.class);
        intent.putExtra("group", "FourthFifth");

        intent.putExtra("email", email);
        startActivity(intent);
    }
    private void opendiet(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, chekk.class);
        startActivity(intent);
    }
    private void movetoflextime(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, flextimefourthfifth.class);

        intent.putExtra("email", email);
        startActivity(intent);
    }
    private void movetoRelaxing(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, relaxingActivityPrimary.class);

        intent.putExtra("email", email);
        startActivity(intent);
    }
    private void movetoMenstural(String email) {
        Intent intent = new Intent(fouthFifthGroup.this, MensturalFourthFifthGroup.class);

        startActivity(intent);
    }
    private void movetoBot() {
        Intent intent = new Intent(fouthFifthGroup.this, com.aaks32173.sih2022new.ui.MainActivity.class);
        startActivity(intent);
    }

    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }

    private void setRV() {

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recycler_view.setLayoutManager(layoutManager);
        autoScrollAdapter = new AutoScrollAdapter(this);
        recycler_view.setAdapter(autoScrollAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recycler_view);

        Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(layoutManager.findLastCompletelyVisibleItemPosition() <(autoScrollAdapter.getItemCount()-1)){
                    layoutManager.smoothScrollToPosition(recycler_view, new RecyclerView.State(),layoutManager.findLastCompletelyVisibleItemPosition()+1);
                }
                else if(layoutManager.findLastCompletelyVisibleItemPosition()<(autoScrollAdapter.getItemCount()-1)){
                    layoutManager.smoothScrollToPosition(recycler_view, new RecyclerView.State(),0);
                }
                {

                }
            }
        },0,3000);

    }



}