package com.example.projectcalculator.model;

public class SubProject {
    private String name;
    private double estPricePerHour;
    private double estTime;

    public SubProject(String name, double estpricePerHour, double estTime) {
        this.name = name;
        estPricePerHour = estpricePerHour;
        this.estTime = estTime;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getestPricePerHour() {
        return estPricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        estPricePerHour = pricePerHour;
    }

    public double getEstTime() {
        return estTime;
    }

    public void setEstTime(double estTime) {
        this.estTime = estTime;
    }
}


