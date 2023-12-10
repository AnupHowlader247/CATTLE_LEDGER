package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class IncomeActivity extends AppCompatActivity {
    EditText Inocmetype,Amount;
    TextView Date;
    FirebaseAuth mAuth;
    Button btnsub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Inocmetype = findViewById(R.id.typeofIncome);
        Amount = findViewById(R.id.amountOfincome);
        btnsub = findViewById(R.id.btnIncome);
        mAuth = FirebaseAuth.getInstance();
        Date = findViewById(R.id.Idate);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        Date.setText(currentDate);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map1 = new HashMap<>();
                map1.put("type",Inocmetype.getText().toString());
                map1.put("amount",Amount.getText().toString());
                map1.put("ICdate",Date.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid().toString()).child("income").push().setValue(map1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(IncomeActivity.this, "Income status updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(IncomeActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }


}