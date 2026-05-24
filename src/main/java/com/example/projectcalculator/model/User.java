package com.example.projectcalculator.model;

import com.example.projectcalculator.model.enums.Expertise;
import com.example.projectcalculator.model.enums.UserRole;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private UserRole userRole;
    private Expertise expertiseLevel;


    public User (int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User (String name){
        this.name = name;
    }

    public User () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Expertise getExpertiseLevel() {
        return expertiseLevel;
    }

    public void setExpertiseLevel(Expertise expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && userRole == user.userRole && expertiseLevel == user.expertiseLevel;
    }
}
