package com.example.projectcalculator.model;

import java.util.List;
import java.util.Objects;

public class Project {
    private int id;
    private String name;
    private User projectLeader;
    private String description;
    private boolean isDone;
    private List<User> employees;
    private List<Integer> employeeIds;

    public Project(int id, String name, User projectLeader, String description) {
        this.id = id;
        this.name = name;
        this.projectLeader = projectLeader;
        this.description = description;
    }

    public Project(int id, String name, String description, boolean isDone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDone = isDone;
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

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Integer> employeeIds) {
        this.employeeIds = employeeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && isDone == project.isDone && Objects.equals(name, project.name) && Objects.equals(projectLeader, project.projectLeader) && Objects.equals(description, project.description) && Objects.equals(employees, project.employees) && Objects.equals(employeeIds, project.employeeIds);
    }
}
