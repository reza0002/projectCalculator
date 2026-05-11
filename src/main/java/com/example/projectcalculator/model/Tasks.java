package com.example.projectcalculator.model;

public class Tasks {
    private String name;
    private double PricePerHour;
    private double time;

    public Tasks(String name, double pricePerHour, double time) {
        this.name = name;
        PricePerHour = pricePerHour;
        this.time = time;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerHour() {
        return PricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        PricePerHour = pricePerHour;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;

    }
}
