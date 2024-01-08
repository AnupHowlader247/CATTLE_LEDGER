package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class CowdetailsActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;

    CowAdapter CowAdapter;
    ArrayList<CowDetails> cowList;

    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    FirebaseAuth mAuth;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cowdetails);
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.cow_recylcer_id);

        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cowList = new ArrayList<>();
        CowAdapter = new CowAdapter(CowdetailsActivity.this,cowList);
        recyclerView.setAdapter(CowAdapter);

        //Retrieving data from database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            // Inside onDataChange method
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cowList.clear();
                for (DataSnapshot dataSnapshot : snapshot.child(mAuth.getCurrentUser().getUid().toString()).child("cows").getChildren()) {

                    String cowKey = dataSnapshot.getKey();
                    String getID = dataSnapshot.child("id").getValue(String.class);
                    String getCategory = dataSnapshot.child("category").getValue(String.class);
                    String getWeight = dataSnapshot.child("weight").getValue(String.class);
                    String getMilk = dataSnapshot.child("milk").getValue(String.class);

                    Log.d("aynaa", "onDataChange: " + getID);

                    CowDetails details = new CowDetails(getID, getCategory, getWeight, getMilk, cowKey);
                    cowList.add(details);

                }
                Collections.reverse(cowList);
                CowAdapter.notifyDataSetChanged();



            }

// ...



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }
    public void searchList(String text){
        ArrayList<CowDetails> searchList = new ArrayList<>();
        for(CowDetails cowDetails: cowList){
            if(cowDetails.getCowID().toLowerCase().contains(text.toLowerCase())){
                searchList.add((cowDetails));
            }
            else{
                Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show();
            }
        }
        CowAdapter.searchDataList(searchList);

    }

    public void DtlsBack(View view) {
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();

    }

}