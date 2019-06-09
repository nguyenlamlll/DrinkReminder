package com.letrannguyenlam.repositories.models;

import java.sql.Date;

public class User {
    private int Id;
    private String username;
    private String password;
    private String name;
    private Date DateOfBirth;
    private double weight;

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

    public User(int id, String username, String password, String name, Date dateOfBirth, double weight) {
        Id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        DateOfBirth = dateOfBirth;
        this.weight = weight;
    }

    public User(String username, String password, String name, Date dateOfBirth, double weight) {
        this.username = username;
        this.password = password;
        this.name = name;
        DateOfBirth = dateOfBirth;
        this.weight = weight;
    }
}
