package com.example.projectcalculator.controller;


import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("projects", projectService.findAllProjects());
        return "home-page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login-page";
    }

    @GetMapping("/create")
    public String createProjectPage(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("employees", projectService.findAllUsers());
        return "create-project";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute Project project) {
        var newProject = projectService.createProject(project);
        return "redirect:/projects/" + newProject.getId() + "/subproject";
    }

    @PostMapping("/delete")
    public String deleteProject(@ModelAttribute Project project) {
        projectService.deleteProject(project);
        return "redirect:/projects";
    }

    @PostMapping("/{project-id}/edit")
    public String updateProject(@PathVariable("project-id") int projectId, @ModelAttribute Project project) {
        project.setId(projectId);
        projectService.updateProject(project);
        return "redirect:/{project-name}/";
    }

    @GetMapping("/{project-id}/subproject")
    public String findSubProject(@PathVariable("project-id") int projectId, Model model) {
        model.addAttribute("subProjects", projectService.findSubProjectsForProject(projectId));
        model.addAttribute("project", projectService.findProject(projectId));
        return "project-overview";
    }

    @GetMapping("/{project-id}/subproject/create")
    public String createSubProjectPage(@PathVariable("project-id") int projectId, Model model) {
        model.addAttribute("subProject", new SubProject());
        return "create-sub-project";
    }

    @PostMapping("/{project-id}/subproject/create")
    public String createSubProject(
            @PathVariable("project-id") int projectId,
            @ModelAttribute SubProject subProject) {

        projectService.createSubProject(subProject);

        return "redirect:/projects/" + projectId + "/subproject";
    }

    @PostMapping("/subproject/delete/{id}")
    public String deleteSubProject(@PathVariable int id) {
        projectService.deleteSubProject(id);
        return "redirect:/projects/";
    }

    @GetMapping("/{project-id}/{sub-project-id}/task")
    public String taskPage(@PathVariable("project-id") int projectId,
                           @PathVariable("sub-project-id") int subProjectId, Model model) {
        model.addAttribute("tasks", projectService.findTasksBySubproject(subProjectId));

        return "sub-project-tasks";
    }

    @GetMapping("/{project-id}/addSubProject")
    public String addSubProject(@PathVariable("project-id") int projectId, Model model) {
        var subProject = new SubProject();

        subProject.setProject_id(projectId);
        model.addAttribute("subProject", subProject);
        return "create-sub-project";
    }

    @PostMapping("/{project-id}/saveSubProject")
    public String saveSubProject(@ModelAttribute SubProject subProject) {
        projectService.saveSubProject(subProject);

        return "redirect:";
    }

    @GetMapping("/{project-id}/{sub-project-id}/addtask")
    public String addTaskPage(@PathVariable("project-id") int projectId,
                              @PathVariable("sub-project-id") int subProjectId,
                              Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/task/add")
    public String addTask(@ModelAttribute Task task) {
        projectService.addTask(task);
        return "redirect:/{project-name}/";
    }

    @PostMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable int id) {
        projectService.deleteTask(id);
        return "redirect:/{project-name}/";
    }

    @PostMapping("/task/save")
    public String saveTask(@ModelAttribute Task task) {
        projectService.saveTask(task);
        return "redirect:/{project-name}/";
    }

    @PostMapping("/task/{id}/edit")
    public String updateTask(@PathVariable int id, @ModelAttribute Task task) {
        task.setId(id);
        projectService.updateTask(task);
        return "redirect:/{project-name}/";
    }


}