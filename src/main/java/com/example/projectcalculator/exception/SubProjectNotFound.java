package com.example.projectcalculator.exception;

public class SubProjectNotFound extends RuntimeException {
    public SubProjectNotFound(int id) {
        super("Subproject with id " + id + " was not found");
    }
}
