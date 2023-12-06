package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);
    }
    public void Backfrmprft(View view) {
        Intent intent = new Intent(getApplicationContext(),OoutcomeActivity.class);
        startActivity(intent);
        finish();

    }
}