package com.example.projectcalculator.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ProjectNotFound.class)
    public String handleProjectNotFound(Model model, ProjectNotFound ex) {
        model.addAttribute("status", 404);
        model.addAttribute("message", ex.getMessage());

        return "error";
    }


    @ExceptionHandler(SubProjectNotFound.class)
    public String handleSubProjectNotFound(Model model, SubProjectNotFound ex) {
        model.addAttribute("status", 404);
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(TaskNotFound.class)
    public String handleTaskNotFound(Model model, TaskNotFound ex) {
        model.addAttribute("status", 404);
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(InvalidLoginException.class)
    public String handleInvalidLoginException(Model model, InvalidLoginException ex) {
        model.addAttribute("status", 401);
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(InvalidInputException.class)
    public String handleInvalidInput(Model model, InvalidInputException ex) {
        model.addAttribute("status", 400);
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler (DataAccessException.class)
    public String handleDatabaseError(Model model,DataAccessException e) {
        e.printStackTrace();
        model.addAttribute("status", 500);
        model.addAttribute("message", "Database error occurred");

        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleGenericError(Model model, RuntimeException ex) {
        ex.printStackTrace();
        model.addAttribute("status", 500);
        model.addAttribute("message", ex.getMessage() != null ? ex.getMessage() : "Unexpected error");
        return "error";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleNotFound (Model model){
        model.addAttribute("status",404);
        model.addAttribute("message", "Resource not found");
        return "error";
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public String handleAccessError(Model model) {
        model.addAttribute("status", 401);
        model.addAttribute("message", "Unauthorized access");
        return "redirect:/login";
    }
}
