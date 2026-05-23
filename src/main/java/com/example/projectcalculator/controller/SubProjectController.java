package com.example.projectcalculator.controller;

import com.example.projectcalculator.validation.LoginValidation;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subproject")
public class SubProjectController {

    private final ProjectService service;
    private final LoginValidation validation;

    public SubProjectController(ProjectService service, LoginValidation validation) {
        this.service = service;
        this.validation = validation;
    }

    @GetMapping("/{subProjectId}")
    public String subProjectPage(@PathVariable int subProjectId,
                                 Model model,
                                 HttpSession session) {
        validation.isLoggedIn(session);

        model.addAttribute("subProject", service.findSubProject(subProjectId));
        model.addAttribute("tasks", service.findTasksBySubproject(subProjectId));
        model.addAttribute("users", service.findAllUsers());
        return "sub-project-tasks";
    }

    @GetMapping("/{projectId}/create")
    public String createSubProjectPage(@PathVariable int projectId, Model model, HttpSession session) {
        validation.isLoggedIn(session);

        var subProject = new SubProject();
        subProject.setProjectId(projectId);
        model.addAttribute("subProject", subProject);
        model.addAttribute("project", service.findProject(projectId));
        return "create-sub-project";
    }

    @PostMapping("/save")
    public String saveSubProject(@ModelAttribute SubProject subProject, HttpSession session) {
        validation.isLoggedIn(session);

        service.saveSubProject(subProject);
        return "redirect:/subproject/" + subProject.getId();
    }

    @PostMapping("/{projectId}/{subProjectId}/delete")
    public String deleteSubProject(@PathVariable int projectId, @PathVariable int subProjectId, HttpSession session) {
        validation.isLoggedIn(session);

        service.deleteSubProject(subProjectId);
        return "redirect:/project/" + projectId;
    }
}
