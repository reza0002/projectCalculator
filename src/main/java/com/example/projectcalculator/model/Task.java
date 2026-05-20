package com.example.projectcalculator.model;

public class Task {
    private String name;
    private int PricePerHour;
    private int hours;
    private int sub_project_id;
    private int id;
    private boolean isDone;

    public Task(String name, int pricePerHour, int hours, boolean isDone) {
        this.name = name;
        this.PricePerHour = pricePerHour;
        this.hours = hours;
        this.isDone = isDone;
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePerHour() {
        return PricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        PricePerHour = pricePerHour;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSub_project_id() {
        return sub_project_id;
    }

    public void setSub_project_id(int sub_project_id) {
        this.sub_project_id = sub_project_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
