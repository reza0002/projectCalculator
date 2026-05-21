package com.example.projectcalculator.controller;

import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService service;

    public TaskController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/{subProjectId}/create")
    public String createTaskPage(@PathVariable int subProjectId,
                                 Model model) {
        var task = new Task();
        task.setSubProjectId(subProjectId);
        model.addAttribute("task", task);
        model.addAttribute("subProject", service.findSubProject(subProjectId));
        return "add-task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        service.addTask(task);
        return "redirect:/subproject/" + task.getSubProjectId();
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable int taskId) {
        var task = service.findTaskById(taskId);
        service.deleteTask(taskId);
        return "redirect:/subproject/" + task.getSubProjectId();
    }

    @PostMapping("/{subProjectId}/{taskId}/edit")
    public String updateTask(@PathVariable int subProjectId,
                             @PathVariable int taskId,
                             @ModelAttribute Task task) {
        task.setId(taskId);
        service.updateTask(task);
        return "redirect:/subproject/" + subProjectId;
    }

    @PostMapping("/{taskId}/update")
    public String markDone(@PathVariable int taskId) {
        var task = service.findTaskById(taskId);
        task.setDone(true);
        service.updateTask(task);
        return "redirect:/subproject/" + task.getSubProjectId();
    }
}