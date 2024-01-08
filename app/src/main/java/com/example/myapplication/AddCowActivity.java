package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddCowActivity extends AppCompatActivity {

    EditText cowId,weight, milk;

    String Value;
    FirebaseAuth mAuth;
    Button btnAdd,btnBack;
    Spinner spinner;
    String [] CowsType = {"Cow","Bull"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cow);

        cowId = findViewById(R.id.cowID);
        spinner = findViewById(R.id.Spinner);
        weight = findViewById(R.id.cowWeight);
        milk = findViewById(R.id.cowMilk);
        btnBack = findViewById(R.id.backAdd);


        mAuth = FirebaseAuth.getInstance();

        btnAdd = findViewById(R.id.btnDetails);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(AddCowActivity.this,R.layout.dropdown_icon,CowsType);
        adapter.setDropDownViewResource(R.layout.dropdown_icon);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Value = parent.getItemAtPosition(position).toString();
                Log.d("hagu", "onItemSelected: " + Value);

                // Check if the selected value is "Bull" and hide the "milk" EditText accordingly
                if (Value.equalsIgnoreCase("Bull")) {
                    milk.setVisibility(View.GONE);
                } else {
                    milk.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<>();
                map.put("id",cowId.getText().toString());
               map.put("category",Value.toString());
                map.put("weight",weight.getText().toString());
                map.put("milk",milk.getText().toString());


                FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid().toString()).child("cows").push().setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddCowActivity.this, "Info added successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddCowActivity.this, "Info insert Error!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


    }
    public void Addcowback(View view) {
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();

    }

}