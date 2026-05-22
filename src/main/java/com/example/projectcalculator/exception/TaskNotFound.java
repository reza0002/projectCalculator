package com.example.projectcalculator.exception;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(int id) {
        super("Task with id " + id + " was not found");
    }
}
