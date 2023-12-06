package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ExpenseActivity extends AppCompatActivity {
    EditText Expensetype,amount;
    FirebaseAuth mAuth;
    Button btnsub1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Expensetype = findViewById(R.id.expense);
        amount = findViewById(R.id.amount);
        btnsub1 = findViewById(R.id.btnExpense);
        mAuth = FirebaseAuth.getInstance();
        btnsub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map2 = new HashMap<>();
                map2.put("ex_type",Expensetype.getText().toString());
                map2.put("ex_amount",amount.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid().toString()).child("Expense").push().setValue(map2)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ExpenseActivity.this, "Income status updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ExpenseActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

}