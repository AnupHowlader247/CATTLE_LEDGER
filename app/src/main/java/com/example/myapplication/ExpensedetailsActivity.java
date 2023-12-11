package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ExpensedetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<ExpenseDetails> expList;

    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expensedetails);

        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.exp_recylcer_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expList = new ArrayList<>();
        myAdapter = new MyAdapter(ExpensedetailsActivity.this,expList);
        recyclerView.setAdapter(myAdapter);

        //Retrieving data from database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                expList.clear();
                for (DataSnapshot dataSnapshot : snapshot.child(mAuth.getCurrentUser().getUid().toString()).child("Expense").getChildren()){

                    String getExpDate = dataSnapshot.child("Cdate").getValue(String.class);
                    String getExpType = dataSnapshot.child("ex_type").getValue(String.class);
                    String getExpAmount = dataSnapshot.child("ex_amount").getValue(String.class);

                    Log.d("aynaa", "onDataChange: "+getExpDate);

                    ExpenseDetails details = new ExpenseDetails(getExpType,getExpDate,getExpAmount);
                    expList.add(details);

                }
                Collections.reverse(expList);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void Btnexpdetlsback (View view) {
        Intent intent = new Intent(getApplicationContext(),OoutcomeActivity.class);
        startActivity(intent);
        finish();

    }
}