package com.letrannguyenlam.repositories.models;

import java.sql.Date;

public class User {
    private int Id;
    private String username;
    private String password;
    private String name;
    private Date DateOfBirth;
    private double weight;
    private double height;
    private int workoutTime;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(int workoutTime) {
        this.workoutTime = workoutTime;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public User(int id, String username, String password, String name, Date dateOfBirth, double weight, double height, int workoutTime) {
        Id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        DateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.workoutTime = workoutTime;
    }

    public User(String username, String password, String name, Date dateOfBirth, double weight, double height, int workoutTime) {
        this.username = username;
        this.password = password;
        this.name = name;
        DateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.workoutTime = workoutTime;
    }

}
