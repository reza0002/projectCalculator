package com.example.projectcalculator.validation;

import com.example.projectcalculator.exception.UnauthorizedAccessException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoginValidationTest {

    private HttpSession session;
    private LoginValidation loginValidation;

    @BeforeEach
    void setUp() {
        session = mock(HttpSession.class);
        loginValidation = new LoginValidation();
    }

    @Test
    public void testLoginThrows() {
        when(session.getAttribute("loggedIn")).thenReturn(null);
        assertThrows(UnauthorizedAccessException.class, () -> loginValidation.isLoggedIn(session));
    }
}