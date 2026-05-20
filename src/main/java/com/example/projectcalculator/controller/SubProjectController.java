package com.example.projectcalculator.controller;

import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subproject")
public class SubProjectController {

    private final ProjectService service;

    public SubProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/{subProjectId}")
    public String subProjectPage(@PathVariable int subProjectId,
                                 Model model) {
        model.addAttribute("subProject", service.findSubProject(subProjectId));
        model.addAttribute("tasks", service.findTasksBySubproject(subProjectId));
        return "sub-project-tasks";
    }

    @GetMapping("/{projectId}/create")
    public String createSubProjectPage(@PathVariable int projectId, Model model) {
        var subProject = new SubProject();
        subProject.setProject_id(projectId);
        model.addAttribute("subProject", subProject);
        model.addAttribute("project", service.findProject(projectId));
        return "create-sub-project";
    }

    @PostMapping("/save")
    public String saveSubProject(@ModelAttribute SubProject subProject) {
        service.saveSubProject(subProject);
        return "redirect:/subproject/" + subProject.getId();
    }

    @PostMapping("/{projectId}/{subProjectId}/delete")
    public String deleteSubProject(@PathVariable int projectId, @PathVariable int subProjectId) {
        service.deleteSubProject(subProjectId);
        return "redirect:/projects/" + projectId + "/subprojects";
    }
}
