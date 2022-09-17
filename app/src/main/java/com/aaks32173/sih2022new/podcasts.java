package com.aaks32173.sih2022new;
import static java.lang.Integer.parseInt;
import static ai.api.util.ParametersConverter.parseFloat;
import static ai.api.util.ParametersConverter.parseInteger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class podcasts extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    StorageReference storagereference;
    String email ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcasts);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        //sleepdetail = false;
        Currentuser = mauth.getCurrentUser();

         email = getIntent().getExtras().getString("email");

        String group = getIntent().getExtras().getString("group");
        if(group.equals("FourthFifth"))
        {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("PodcastsFouthFifth");
            for(int i=1; i<=7 ;i++){
                int i1=i;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child("Podcast"+i1).child("name").getValue().toString();
                        String tvname = "tv"+i1;
                        int id = getResources().getIdentifier(tvname, "id", getPackageName());
                        if (id != 0) {
                            TextView textView = (TextView) findViewById(id);
                            textView.setText(name);
                        }
//                        String imgname = "img"+i1;
//                        int id2 = getResources().getIdentifier(imgname, "id", getPackageName());
//                        if (id2 != 0) {
//                            ImageView imv = (ImageView) findViewById(id2);
//                            storagereference = FirebaseStorage.getInstance().getReference("3rd-6th/podacst/img"+1+".jpg");
//                           fetchimg(imv);
//                        }


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });
            }
        }

        LinearLayout btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("1");
                increasecounter(email, "music&podcast");
            }
        });
        LinearLayout btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("7");
                increasecounter(email, "music&podcast");
            }
        });
        LinearLayout btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("2");
                increasecounter(email, "music&podcast");
            }
        });
        LinearLayout btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("3");
                increasecounter(email, "music&podcast");
            }
        });
        LinearLayout btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("4");
                increasecounter(email, "music&podcast");
            }
        });
        LinearLayout btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("5");
                increasecounter(email, "music&podcast");
            }
        });
        LinearLayout btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfun("6");
                increasecounter(email, "music&podcast");
            }
        });
    }
    public  void getfun(String s)
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String url = dataSnapshot.child("Podcast"+s).child("url").getValue().toString();
                Intent intent = new Intent(podcasts.this, vedioPlay.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }
    void fetchimg(ImageView page)
    {
        //ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, R.layout.magazinepages, pages);
        try {
            File localfile = File.createTempFile("tempfile", ".jpg");
            storagereference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    page.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private void increasecounter(String email) {
//        LocalDate today=LocalDate.now();
//
//        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
//        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
//            String progress;
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                progress = dataSnapshot.child("music&podcast").child("progress").getValue().toString();
//
//                if(Integer.parseInt(progress)<=90) {
//                    int prg = Integer.parseInt(progress) + 10;
//
//                    reference1.child("music&podcast").child("progress").setValue(Integer.toString(prg));
//
//                }else{
//
//                    int prg = 100;
//
//                    reference1.child("music&podcast").child("progress").setValue(Integer.toString(prg));
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//
//    }

    private void increasecounter(String email, String trend) {
        LocalDate today = LocalDate.now();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("music&podcast").child("progress").getValue().toString();

                if (Integer.parseInt(progress) <= 90) {
                    int prg = Integer.parseInt(progress) + 10;
                    trending(trend);
                    reference1.child("music&podcast").child("progress").setValue(Integer.toString(prg));
                    week();
                    if(prg==100) {

                        reward() ;
                    }
                } else if(Integer.parseInt(progress)==100){

                    int prg = 100;

                    reference1.child("music&podcast").child("progress").setValue(Integer.toString(prg));
                    reward();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    private void trending(String trend)
    {        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("trending");


        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String p = snapshot.child(trend).getValue().toString();
                int p2 = Integer.parseInt(p);
                ref2.child(trend).setValue(Integer.toString(10+p2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void week()
    {        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("WEEKTODO");



        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progress = dataSnapshot.child("2").child("progress").getValue().toString();

                int prg = Integer.parseInt(progress) + 10;

                reference2.child("2").child("progress").setValue(Integer.toString(prg));


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    void reward() {
        DatabaseReference reference1 =
                FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email);

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DatabaseReference reference2 =
                        FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email);
                String rew = snapshot.child("rewards").getValue().toString();

                int rev = Integer.parseInt(rew) + 50;
                reference2.child("rewards").setValue(rev + "");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}