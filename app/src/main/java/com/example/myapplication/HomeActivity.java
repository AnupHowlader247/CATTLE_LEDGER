package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addCow(View view) {
        Intent intent = new Intent(getApplicationContext(),AddCowActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateExpense(View view) {
        Intent intent = new Intent(getApplicationContext(),ExpenseActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateIncome(View view) {
        Intent intent = new Intent(getApplicationContext(),IncomeActivity.class);
        startActivity(intent);
        finish();
    }
     public void Outcome(View view) {
        Intent intent = new Intent(getApplicationContext(),OoutcomeActivity.class);
        startActivity(intent);
        finish();

}
    public void Cowdetails(View view) {
        Intent intent = new Intent(getApplicationContext(),CowdetailsActivity.class);
        startActivity(intent);
        finish();

    }
}