package com.example.projectcalculator.controller;

import com.example.projectcalculator.validation.LoginValidation;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService service;
    private final LoginValidation validation;

    public TaskController(ProjectService service, LoginValidation validation) {
        this.service = service;
        this.validation = validation;
    }

    @GetMapping("/{subProjectId}/create")
    public String createTaskPage(@PathVariable int subProjectId, Model model, HttpSession session) {
        validation.isLoggedIn(session);

        var task = new Task();
        task.setSubProjectId(subProjectId);
        final SubProject subProject = service.findSubProject(subProjectId);
        final List<User> employees = service.findEmployeesInProject(subProject.getProjectId());
        final var projectsDeadline = service.ProjectDeadline(subProject.getProjectId());

        model.addAttribute("task", task);
        model.addAttribute("subProject", subProject);
        model.addAttribute("teamMembers", employees);
        model.addAttribute("projectDeadline", projectsDeadline);
        return "add-task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task, HttpSession session) {
        validation.isLoggedIn(session);

        Task savedTask = service.saveTask(task);
        return "redirect:/subproject/" + savedTask.getSubProjectId();
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable int taskId, HttpSession session) {
        validation.isLoggedIn(session);

        var task = service.findTaskById(taskId);
        service.deleteTask(taskId);
        return "redirect:/subproject/" + task.getSubProjectId();
    }

    @PostMapping("/{subProjectId}/{taskId}/edit")
    public String updateTask(@PathVariable int subProjectId,
                             @PathVariable int taskId,
                             @ModelAttribute Task task,
                             HttpSession session) {
        validation.isLoggedIn(session);

        task.setId(taskId);
        service.updateTask(task);
        return "redirect:/subproject/" + subProjectId;
    }

    @PostMapping("/{taskId}/update")
    public String markDone(@PathVariable int taskId, HttpSession session) {
        validation.isLoggedIn(session);

        var task = service.findTaskById(taskId);
        task.setDone(true);
        service.updateTask(task);
        return "redirect:/subproject/" + task.getSubProjectId();
    }
}