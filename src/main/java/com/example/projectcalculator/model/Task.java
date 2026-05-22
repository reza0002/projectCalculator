package com.example.projectcalculator.model;

public class Task {
    private String name;
    private int pricePerHour;
    private int hours;
    private int subProjectId;
    private int id;
    private boolean isDone;
    private int assigneeId;

    public Task(int id, String name, int pricePerHour, int subProjectId, int hours, boolean isDone) {
        this.id = id;
        this.name = name;
        this.pricePerHour = pricePerHour;
        this.subProjectId = subProjectId;
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
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
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

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }
}
