package com.example.projectcalculator.controller;

import com.example.projectcalculator.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @Test
    void showLogin_shouldRedirectToProject_whenUserIsLoggedIn() throws Exception {
        mockMvc.perform(get("/login")
                        .sessionAttr("loggedIn", true))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void showLogin_shouldReturnLoginPage_whenUserIsNotLoggedIn() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login-page"));
    }

    @Test
    void login_shouldRedirectToProject_whenCredentialsAreValid() throws Exception {
        when(service.userLogin("abc", "123")).thenReturn(true);

        mockMvc.perform(post("/login")
                        .param("username", "abc")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));

        verify(service).userLogin("abc", "123");
    }

    @Test
    void login_shouldRedirectToLogin_whenCredentialsAreInvalid() throws Exception {
        when(service.userLogin("wrong", "wrong")).thenReturn(false);

        mockMvc.perform(post("/login")
                        .param("username", "wrong")
                        .param("password", "wrong"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(service).userLogin("wrong", "wrong");
    }

    @Test
    void  logout_shouldRedirectToLogin_whenUserLogsOut() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loggedIn", true);

        mockMvc.perform(get("/login/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(request().sessionAttributeDoesNotExist("loggedIn"));

        assertTrue(session.isInvalid());
    }
}