package com.example.projectcalculator.controller;


import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.service.ProjectService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/create")
    public String createProjectPage(Model model) {
        model.addAttribute("project", new Project());
        return "projects-page";
    }

//    @PostMapping("/create")
//    public String createProject(@ModelAttribute Project project) {
//        projectService.createProject(project);
//        return "redirect:/projects/";
//    }

    @GetMapping("/{project-name}/subproject")
    public String subProjectPage() {
        return "subProjectsPage";
    }

    @GetMapping("/{project-name}/")
    public String taskPage() {
        return "tasksPage";
    }

    @GetMapping("/{project-name}/{sub-project-name}/addtask")
    public String addTaskPage(Model model) {
        model.addAttribute("task", new Task());
        return "addTasks";
    }

    @PostMapping("/task/add")
    public String addTask(@ModelAttribute Task task){
        projectService.addTask(task);
        return "redirect:/subProjectsPage";
    }

}