package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IncomedetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomedetails);
    }
    public void Backincomedetails (View view) {
        Intent intent = new Intent(getApplicationContext(),OoutcomeActivity.class);
        startActivity(intent);
        finish();

    }
}