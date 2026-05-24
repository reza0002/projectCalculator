package com.example.projectcalculator.controller;


import com.example.projectcalculator.validation.LoginValidation;
import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;
    private final LoginValidation validation;

    public ProjectController(ProjectService service, LoginValidation validation) {
        this.service = service;
        this.validation = validation;
    }

    @GetMapping
    public String listProjects(Model model, HttpSession session) {
        validation.isLoggedIn(session);

        model.addAttribute("projects", service.findAllProjects());
        return "home-page";
    }

    @GetMapping("/{projectId}")
    public String projectOverview(@PathVariable int projectId, Model model, HttpSession session) {
        validation.isLoggedIn(session);

        model.addAttribute("subProjects", service.findSubProjectHours(projectId));
        model.addAttribute("project", service.findProject(projectId));
        model.addAttribute("assignedEmployees", service.findEmployeesInProject(projectId));

        int totalHours = service.calculateTotalHours(projectId);
        LocalDate deadline = service.ProjectDeadline(projectId);
        model.addAttribute("totalHours", totalHours);
        model.addAttribute("totalPrice", totalHours * 1200);
        model.addAttribute("estimatedDeadline", deadline);

        return "project-overview";
    }

    @GetMapping("/create")
    public String createProjectPage(Model model, HttpSession session) {
        validation.isLoggedIn(session);

        model.addAttribute("project", new Project());
        model.addAttribute("employees", service.findAllUsers());
        return "create-project";
    }

    @PostMapping("/save")
    public String createProject(@ModelAttribute Project project, HttpSession session) {
        validation.isLoggedIn(session);

        var newProject = service.saveProject(project);
        return "redirect:/project/" + newProject.getId();
    }

    @PostMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable int projectId, HttpSession session) {
        validation.isLoggedIn(session);

        service.deleteProject(projectId);
        return "redirect:/project";
    }

    @PostMapping("/{projectId}/edit")
    public String updateProject(@PathVariable int projectId, @ModelAttribute Project project, HttpSession session) {
        validation.isLoggedIn(session);

        project.setId(projectId);
        service.updateProject(project);
        return "redirect:/project";
    }
}