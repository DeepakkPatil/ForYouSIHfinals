package com.aaks32173.sih2022new;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class chekk extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mauth;
    FirebaseUser Currentuser;
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter adapter ;
    private List<listitem>listitems ;
    private Button btn ;
    private String si ;
    private EditText editText ;
    private EditText edt_calf ;
    private static String URL_DATA ;

    private static String URL_DATA1=" https://api.nutritionix.com/v1_1/search/" ;
    private static String URL_DATA2= "?results=0:20&fields=item_name,brand_name,item_id,nf_calories&appId=26e9d7d4&appKey=470cf8906c9bcf28bbffec8d6bede0ee" ;



    DatabaseReference dbref;
    DatabaseReference dbref2;
    DatabaseReference dbref22 ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String email;
    TextView showCalories;
    double calories=0.0;
    double ttlcalories=0.0;
    public double height=0.0;
    public double weight=0.0;
    public double age_id=0.0;
    ImageView imageView;
    ProgressBar progress;
    public MyAdapter.onItemClickListener listener;
    EditText show;
    Button submit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chekk);
        progress = findViewById(R.id.progress_bar1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        //sleepdetail = false;
        Currentuser = mauth.getCurrentUser();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerv) ;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText=findViewById(R.id.search) ;
        btn=findViewById(R.id.searchbtn) ;
        listitems=new ArrayList<>() ;
        edt_calf = findViewById(R.id.cal_f) ;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("BMI");

        mAuth = FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        email = currentUser.getEmail();
        showCalories = findViewById(R.id.showCalories);
        show=findViewById(R.id.diet_show);
        submit=findViewById(R.id.diet_submit);
        imageView = findViewById(R.id.imageView);

        fetchdata();









        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listitems.clear();
                si=editText.getText().toString();
                loadRecyclerviewData() ;

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            //            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Toast.makeText(chekk.this, "1", Toast.LENGTH_SHORT).show();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dbref = FirebaseDatabase.getInstance().getReference("UserInfo").child(encodeUserEmail(Currentuser.getEmail())).child("BMI");
                        String cl = dataSnapshot.child("calinitial").getValue().toString();
                        int i = Integer.parseInt(show.getText().toString()) + Integer.parseInt(cl);
                        dbref.child("calinitial").setValue(i + "");
                        String finalc=edt_calf.getText().toString();
                        float calfdd= Float.parseFloat(finalc);
                        int c=(int)Math.round(calfdd) ;
                        progress.setMax(c);
                        progress.setProgress(Integer.parseInt(cl));
                        Toast.makeText(chekk.this, c+"", Toast.LENGTH_SHORT).show();

                        fetchdata();
                        increasecounter(encodeUserEmail(email),i,c);
                        week(encodeUserEmail(email),i,c);



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });



            }
//                if(p<0.0d) {
//                Toast.makeText(chekk.this, "Calorie Intake Achieved", Toast.LENGTH_SHORT).show();
//                showCalories.setText("0");
//                show.setText("");
//                editText.setText("");
//                listitems.clear();
//                loadRecyclerviewData() ;
//            }else
//            {
//                databaseReference.child("calinitial").setValue(p);
//                showCalories.setText(p.toString());
//                show.setText("");
//                editText.setText("");
//                listitems.clear();
//                loadRecyclerviewData() ;
//            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(chekk.this, "hre", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(chekk.this,Diett.class);
                Toast.makeText(chekk.this, "Feature Under Progress", Toast.LENGTH_SHORT).show();

//                startActivity(intent);
            }
        });


    }
    private void loadRecyclerviewData()
    {
        URL_DATA=URL_DATA1+si+URL_DATA2;
//        Toast.makeText(this,URL_DATA,Toast.LENGTH_SHORT).show();
        ProgressDialog progressDialog=new ProgressDialog(this) ;
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(s) ;
                    JSONArray array=jsonObject.getJSONArray("hits")    ;

                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i) ;

                        String fild=o.getString("fields");
//                         Toast.makeText(getApplicationContext(),fild,Toast.LENGTH_SHORT).show();

                        JSONObject fldfinal=new JSONObject(fild) ;
                        listitem item=new listitem(fldfinal.getString("item_name"),fldfinal.getString("nf_calories")) ;



                        listitems.add(item) ;
                    }



                    SetOnClickListener();
                    adapter= new MyAdapter(listitems,listener) ;
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }) ;

        RequestQueue requestQueue= Volley.newRequestQueue(this) ;
        requestQueue.add(stringRequest);
    }

    private void SetOnClickListener() {
        listener = new MyAdapter.onItemClickListener(){
            @Override
            public void onClickItem(View v, int positon) {
//
                float f=Float.parseFloat(listitems.get(positon).getCal()) ;
                int i=(int)Math.round(f) ;
                show.setText(i+"");
            }
        };
    }


    private String encodeUserEmail(String email) {
        return email.replace(".", ",");
    }




//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void increasecounter(String email, int diet, int ttldiet) {
//        LocalDate today=LocalDate.now();
//
//        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
//        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
//            String progress;
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                progress = dataSnapshot.child("nutrition").child("progress").getValue().toString();
//
//                int prg=(ttldiet/diet)*100;
//
//                reference1.child("nutrition").child("progress").setValue(Integer.toString(prg));
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//
//    }


    private void fetchdata(){
        dbref2= FirebaseDatabase.getInstance().getReference("UserInfo").child(encodeUserEmail(email)).child("BMI");
        dbref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //calories= Double.parseDouble(snapshot.child("age").getValue().toString());
                String calinit = snapshot.child("calinitial").getValue().toString();
                String calfinal = snapshot.child("calfinal").getValue().toString();
                edt_calf.setText(calfinal);
                double calin = Double.parseDouble(calinit);
                double calf = Double.parseDouble(calfinal);
                double ans = calf-calin;
                int a =(int)Math.round(ans);
                if(a<0) {
                    a = 0;
                }
                else{
                    ;
                }

                showCalories.setText(a+"");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void increasecounter(String email,int ttldiet,int diet ) {
        LocalDate today = LocalDate.now();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());
        DatabaseReference refre2=FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("TODO").child(today.toString());


        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progress = dataSnapshot.child("nutrition").child("progress").getValue().toString();

                int prg =  ((ttldiet*100)/diet);

                if(prg>100) {
                    prg = 100;

                }
                reference1.child("nutrition").child("progress").setValue(Integer.toString(prg));
                reward();

            }




            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
    void reward() {

        DatabaseReference reference1 =
                FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(email));

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DatabaseReference reference2 =
                        FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(email));
                String rew = snapshot.child("rewards").getValue().toString();

                Toast.makeText(chekk.this, "initial rewards "+rew, Toast.LENGTH_SHORT).show();

                int rev = Integer.parseInt(rew) + 50;
                reference2.child("rewards").setValue(rev + "");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        return ;
    }


    private void week(String email,int ttldiet,int diet ) {

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(email).child("WEEKTODO");


        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            String progress;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progress = dataSnapshot.child("3").child("progress").getValue().toString();

                int prg =  ((ttldiet*100)/diet)+Integer.parseInt(progress);


                reference1.child("3").child("progress").setValue(Integer.toString(prg));


            }




            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
}