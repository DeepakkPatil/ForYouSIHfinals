package com.aaks32173.sih2022new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Diett extends AppCompatActivity {

//    Button next = R.
        RecyclerView recyclerView;
        DatabaseReference dbref;
        DatabaseReference dbref2;
        FirebaseAuth mAuth;
        FirebaseUser currentUser;
        public double check=0.0;
        ArrayList<diet_class> list;
        diet_class diet_class;
        DietAdapter dietAdapter;
        String diet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diett);

        recyclerView = findViewById(R.id.diet_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        String email = currentUser.getEmail();
        String final_email = encodeUserEmail(email);

        list = new ArrayList<diet_class>();
        dietAdapter = new DietAdapter(this, list);
        recyclerView.setAdapter(dietAdapter);

        FirebaseDatabase.getInstance().getReference("User").child(final_email).child("BMI").child("bmi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {

                check=Double.parseDouble((String) snap.getValue());
//                double bmi = Double.parseDouble(check);
//                String c=Double.toString(bmi);
//               Toast.makeText(Diet.this, snap.getValue().toString(),Toast.LENGTH_LONG).show();
                FirebaseDatabase.getInstance().getReference("User").child(final_email).child("userDiet").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        diet = snapshot.getValue().toString();

                        if((diet.equals("Vegeterian")) && (check <=(double)18.0) ){
//                        Toast.makeText(Diet.this,diet,Toast.LENGTH_LONG).show();
                            dbref=FirebaseDatabase.getInstance().getReference("VegDiet");}
                        else if((diet.equals("Vegeterian")) && (check >=(double)18.0) && (check <= (double)22.0)){
//                        Toast.makeText(Diet.this,diet,Toast.LENGTH_LONG).show();
                        dbref=FirebaseDatabase.getInstance().getReference("moderateCalorieVeg");}

                        else if((diet.equals("Vegeterian")) && (check >= (double)22.0)){
//                        Toast.makeText(Diet.this,diet,Toast.LENGTH_LONG).show();
                            dbref=FirebaseDatabase.getInstance().getReference("lowCalorieVeg");}//.child(final_email).child("userDiet");
                            dbref.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                    diet_class = dataSnapshot.getValue(diet_class.class);
//                                    Toast.makeText(Diet.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();

                                    list.add(diet_class);
                                }
                                dietAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private String encodeUserEmail(String email) {
        return email.replace(".", ",");
    }
}