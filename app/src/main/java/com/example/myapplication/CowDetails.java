package com.example.myapplication;

public class CowDetails {

    String cowID;
    String type;
    String weight;
    String milk;
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key;

    public CowDetails(String cowID, String type, String weight, String milk, String key) {
        this.cowID = cowID;
        this.type = type;
        this.weight = weight;
        this.milk = milk;
        this.key = key;
    }

    public CowDetails(String cowID, String type, String weight, String milk) {
        this.cowID = cowID;
        this.type = type;
        this.weight = weight;
        this.milk = milk;
    }

    public String getCowID() {
        return cowID;
    }

    public void setCowID(String cowID) {
        this.cowID = cowID;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }
}
