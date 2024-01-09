package com.example.myapplication;

public class IncomeDetails {

    String Type,Date,Amount;

    public IncomeDetails(String Type, String Date, String Amount) {
        this.Type = Type;
        this.Date = Date;
        this.Amount = Amount;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = Date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        this.Amount = Amount;
    }
}
