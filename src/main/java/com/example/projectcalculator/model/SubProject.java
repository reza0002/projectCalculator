package com.example.projectcalculator.model;

public class SubProject {
    private int id;
    private String name;
    private String description;
    private int hours;
    private int pricePerHour;
    private int projectId;

    public SubProject() {
    }

    public SubProject(String name, String description, int hours, int pricePerHour, int projectId) {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.pricePerHour = pricePerHour;
        this.projectId = projectId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}