package com.example.projectcalculator.authvalidation;

import com.example.projectcalculator.exception.UnauthorizedAccessException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class LoginValidation {
    public void isLoggedIn(HttpSession session) throws UnauthorizedAccessException {
        if (session.getAttribute("loggedIn") == null)
            throw new UnauthorizedAccessException("You don't have access to this site.");
    }
}
