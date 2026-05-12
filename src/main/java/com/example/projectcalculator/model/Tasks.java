package com.example.projectcalculator.model;

public class Tasks {
    private String name;
    private double PricePerHour;
    private int hours;

    public Tasks(String name, double pricePerHour, int hours) {
        this.name = name;
        PricePerHour = pricePerHour;
        this.hours = hours;
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

    public double getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;

    }
}
