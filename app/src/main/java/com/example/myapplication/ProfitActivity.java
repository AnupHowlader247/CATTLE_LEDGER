package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfitActivity extends AppCompatActivity {

    TextView totalIncomeTextView,totalExpenseTextview;

    DatabaseReference databaseReference;
    ValueEventListener eventListener,eventListener1;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);
        totalIncomeTextView=findViewById(R.id.Incometotal);
        totalExpenseTextview=findViewById(R.id.Expensetotal);


        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Retrieve total income
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int totalIncome = 0;
                for (DataSnapshot dataSnapshot : snapshot.child(mAuth.getCurrentUser().getUid().toString()).child("income").getChildren()) {
                    String getIncAmount = dataSnapshot.child("amount").getValue(String.class);

                    if (getIncAmount == null) {
                        continue;
                    }
                    totalIncome += Integer.parseInt(getIncAmount);
                }
                totalIncomeTextView.setText(String.valueOf(totalIncome +" Tk"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled if needed
            }
        });
        // Retrieve total expenses
        eventListener1 = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalExpense = 0;
                for (DataSnapshot dataSnapshot : snapshot.child(mAuth.getCurrentUser().getUid()).child("Expense").getChildren()) {
                    String getExpamount = dataSnapshot.child("ex_amount").getValue(String.class);

                    if (getExpamount == null) {
                        continue;
                    }
                    totalExpense += Integer.parseInt(getExpamount);
                }
                totalExpenseTextview.setText(String.valueOf(totalExpense + " Tk"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });









    }

    public void Backfrmprft(View view) {
        Intent intent = new Intent(getApplicationContext(), OoutcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
