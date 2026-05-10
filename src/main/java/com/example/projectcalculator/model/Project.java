package com.example.projectcalculator.model;

public class Project {
    private int id;
    private String name;
    private User projectLeader;
    public Project (int id, String name, User projectLeader){
        this.id = id;
        this.name = name;
        this.projectLeader = projectLeader;
    }
    public Project(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Project(String name, User projectLeader) {
        this.name = name;
        this.projectLeader = projectLeader;
    }

    public Project() {
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

}
