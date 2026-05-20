package com.example.projectcalculator.model;

public class Project {
    private int id;
    private String name;
    private User projectLeader;
    private String description;
    private boolean isDone;

    public Project (int id, String name, User projectLeader, String description){
        this.id = id;
        this.name = name;
        this.projectLeader = projectLeader;
        this.description = description;
    }
    public Project(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Project(String name, User projectLeader, String description) {
        this.name = name;
        this.projectLeader = projectLeader;
        this.description = description;
    }

    public Project() {
        this.projectLeader = new User();
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
    public User getProjectLeader() {
        return projectLeader;
    }
    public void setProjectLeader(User projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
