package com.example.projectcalculator.model;

import java.util.Objects;

public class SubProject {
    private int id;
    private String name;
    private String description;
    private int pricePerHour;
    private int hours;
    private int projectId;
    private boolean isDone;

    public SubProject() {
        this.pricePerHour = 1200;
        this.hours = 0;
        this.isDone = false;
    }

    public SubProject(String name, String description, int pricePerHour, int hours, int projectId, boolean isDone) {
        this.name = name;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.hours = hours;
        this.projectId = projectId;
        this.isDone = isDone;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubProject that = (SubProject) o;
        return id == that.id && pricePerHour == that.pricePerHour && hours == that.hours && projectId == that.projectId && isDone == that.isDone && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, pricePerHour, hours, projectId, isDone);
    }
}


