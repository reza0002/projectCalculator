package com.example.projectcalculator.controller;


import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", service.findAllProjects());
        return "home-page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }

    @GetMapping("/{projectId}")
    public String projectOverview(@PathVariable int projectId, Model model) {
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
    public String createProjectPage(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("employees", service.findAllUsers());
        return "create-project";
    }

    @PostMapping("/save")
    public String createProject(@ModelAttribute Project project) {
        var newProject = service.saveProject(project);
        return "redirect:/project/" + newProject.getId();
    }

    @PostMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable int projectId) {
        service.deleteProject(projectId);
        return "redirect:/project";
    }

    @PostMapping("/{projectId}/edit")
    public String updateProject(@PathVariable int projectId, @ModelAttribute Project project) {
        project.setId(projectId);
        service.updateProject(project);
        return "redirect:/project";
    }
}