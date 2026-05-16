package com.example.projectcalculator.controller;

import com.example.projectcalculator.service.ProjectService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/prototype")
public class PrototypeController {

    private final ProjectService service;

    public PrototypeController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("projects", service.findAllProjects());
        return "homePage";
    }

    @GetMapping("/create")
    public String createProjectPage() {
        return "projectsPage";
    }

    @GetMapping("/project-name/subproject")
    public String subProjectPage() {
        return "subProjectsPage";
    }

    @GetMapping("/project-name")
    public String taskPage() {
        return "tasksPage";
    }

    @GetMapping("/project-name/subproject/addtask")
    public String addTaskPage() {
        return "addTasks";
    }
}