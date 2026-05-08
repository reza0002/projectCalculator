package com.example.projectcalculator.controller;

import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping("/")
    public String homepage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/create")
    public String createProjectPage() {
        return "projectsPage";
    }

    @GetMapping("/{project-name}/subproject")
    public String subProjectPage() {
        return "subProjectsPage";
    }

    @GetMapping("/{project-name}/")
    public String taskPage() {
        return "tasksPage";
    }

    @GetMapping("/{project-name}/{sub-project-name}/addtask")
    public String addTaskPage() {
        return "addTasks";
    }
}