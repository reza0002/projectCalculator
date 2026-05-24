package com.example.projectcalculator.exception;

public class ProjectNotFound extends RuntimeException {
    public ProjectNotFound(int id) {
        super("Project with id " + id + " was not found");
    }
}



