package com.example.myapplication;

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

    TextView totalIncomeTextView, totalExpenseTextView;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        totalIncomeTextView = findViewById(R.id.Incometotal);
        totalExpenseTextView = findViewById(R.id.Expensetotal);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Retrieve total income
        databaseReference.child(mAuth.getCurrentUser().getUid()).child("income")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        double totalIncome = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String getIncomeAmount = snapshot.child("amount").getValue(String.class);
                            if(getIncomeAmount==null){continue;}

                            totalIncome += Double.parseDouble(getIncomeAmount);
                        }
                        totalIncomeTextView.setText(String.valueOf((int) totalIncome)); // Convert to String
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


        // Retrieve total expenses
        databaseReference.child(mAuth.getCurrentUser().getUid()).child("expenses")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        double totalExpense = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String getExpenseAmount = snapshot.child("ex_amount").getValue(String.class);
                            if(getExpenseAmount==null){continue;}
                            totalExpense += Double.parseDouble(getExpenseAmount);

                        }

                        totalExpenseTextView.setText(String.valueOf((int)  totalExpense));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public void Backfrmprft(View view) {
        Intent intent = new Intent(getApplicationContext(), OoutcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
