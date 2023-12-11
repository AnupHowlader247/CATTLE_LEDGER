package com.example.myapplication;

public class ExpenseDetails {

    String type,date,amount;

    public ExpenseDetails(String type, String date, String amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
