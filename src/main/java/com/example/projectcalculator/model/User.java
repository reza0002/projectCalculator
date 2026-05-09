package com.example.projectcalculator.model;

public class User {
    private String name;
    private String email;
    private int password;


    public User (String name, String email, int password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User (String name, int password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
