package com.letrannguyenlam.repositories.models;

import java.sql.Date;

public class DrinkRecord {
    public DrinkRecord() {
    }

    public DrinkRecord(int id, int userId, double amount, Date timeTaken) {
        Id = id;
        this.userId = userId;
        this.amount = amount;
        this.timeTaken = timeTaken;
    }

    public DrinkRecord(int userId, double amount, Date timeTaken) {
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

    private Date timeTaken;
    public Date getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Date timeTaken) {
        this.timeTaken = timeTaken;
    }
}
