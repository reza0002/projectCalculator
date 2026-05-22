package com.example.projectcalculator.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("Invalid username of password");
    }
}
