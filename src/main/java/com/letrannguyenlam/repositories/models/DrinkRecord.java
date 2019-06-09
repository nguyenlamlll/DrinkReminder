package com.letrannguyenlam.repositories.models;

import java.sql.Date;
import java.sql.Timestamp;

public class DrinkRecord {
    public DrinkRecord() {
    }

    public DrinkRecord(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public DrinkRecord(int id, int userId, double amount, Timestamp timeTaken) {
        Id = id;
        this.userId = userId;
        this.amount = amount;
        this.timeTaken = timeTaken;
    }

    public DrinkRecord(int userId, double amount, Timestamp timeTaken) {
        this.userId = userId;
        this.amount = amount;
        this.timeTaken = timeTaken;
    }

    private int Id;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    private int userId;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private double amount;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private Timestamp timeTaken;
    public Timestamp getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Timestamp timeTaken) {
        this.timeTaken = timeTaken;
    }
}
