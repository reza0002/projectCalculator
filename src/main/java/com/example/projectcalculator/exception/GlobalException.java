package com.example.projectcalculator.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler (DataAccessException.class)
    public String handleDatabaseError(Model model,DataAccessException e) {
        model.addAttribute("status", 500);
        model.addAttribute("message", "Database error occured");

        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleGenericError(Model model, RuntimeException ex) {
        model.addAttribute("status", 500);
        model.addAttribute("message", ex.getMessage() != null ? ex.getMessage() : "Unexpected error");
        return "error";
    }
}
