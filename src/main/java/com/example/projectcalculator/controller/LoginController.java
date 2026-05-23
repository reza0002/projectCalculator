package com.example.projectcalculator.controller;

import com.example.projectcalculator.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final ProjectService service;
    private final HttpSession session;


    public LoginController(ProjectService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }

    @GetMapping()
    public String showLogin(HttpSession session) {
        if (session.getAttribute("loggedIn") != null) {
            return "redirect:/project";
        }
        return "login-page";
    }

    @PostMapping()
    public String login(@RequestParam String username, @RequestParam String password) {
        if (service.userLogin(username, password)) {
            session.setAttribute("loggedIn", true);
            session.setMaxInactiveInterval(1200);
            return "redirect:/project";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("loggedIn");
        session.invalidate();
        return "redirect:/login";
    }
}
