package com.example.projectcalculator.model;

public class Task {
    private String name;
    private int PricePerHour;
    private int hours;
    private int sub_project_id;

    public Task(String name, int pricePerHour, int hours) {
        this.name = name;
        this.PricePerHour = pricePerHour;
        this.hours = hours;
    }

    public Task() {
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

    public void setPricePerHour(int pricePerHour) {
        this.PricePerHour = pricePerHour;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;

    }

    public void setSub_project_id(int sub_project_id) {
        this.sub_project_id = sub_project_id;
    }

    public int getSub_project_id() {
        return sub_project_id;
    }
}
