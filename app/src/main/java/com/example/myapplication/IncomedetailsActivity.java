package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class IncomedetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    IncomeAdapter incomeAdapter;
    ArrayList<IncomeDetails>incList;

    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomedetails);
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.inc_recylcer_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        incList = new ArrayList<>();
        incomeAdapter = new IncomeAdapter(IncomedetailsActivity.this,incList);
        recyclerView.setAdapter(incomeAdapter);

        //Retrieving data from database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                incList.clear();
                for (DataSnapshot dataSnapshot : snapshot.child(mAuth.getCurrentUser().getUid().toString()).child("income").getChildren()){

                    String getIncDate = dataSnapshot.child("ICdate").getValue(String.class);
                    String getIncType = dataSnapshot.child("type").getValue(String.class);
                    String getIncAmount = dataSnapshot.child("amount").getValue(String.class);

                    Log.d("aynaa", "onDataChange: "+getIncDate);

                    IncomeDetails details = new IncomeDetails(getIncType,getIncDate,getIncAmount);
                    incList.add(details);

                }
                Collections.reverse(incList);
                incomeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Backincomedetails (View view) {
        Intent intent = new Intent(getApplicationContext(),OoutcomeActivity.class);
        startActivity(intent);
        finish();

    }
}