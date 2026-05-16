package com.example.projectcalculator.model;

public class SubProject {
    private int id;
    private String name;
    private String description;
    private int price_per_hour;
    private int hours;

    public SubProject(String name, String description, int price_per_hour, int hours) {
        this.name = name;
        this.description = description;
        this.price_per_hour = price_per_hour;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(int price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


