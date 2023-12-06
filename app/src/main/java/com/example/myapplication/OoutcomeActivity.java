package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OoutcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ooutcome);
    }
    public void OutBack(View view) {
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }
   public void  Expensedetails(View view) {
        Intent intent = new Intent(getApplicationContext(),ExpensedetailsActivity.class);
        startActivity(intent);
        finish();
    }
    public void  Incomedetails(View view) {
        Intent intent = new Intent(getApplicationContext(), IncomedetailsActivity.class);
        startActivity(intent);
        finish();
    }
    public void  Profitdetails(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfitActivity.class);
        startActivity(intent);
        finish();
    }
}