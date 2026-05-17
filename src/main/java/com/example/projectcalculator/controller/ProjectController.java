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

    @PostMapping("/create")
    public String createProject(@ModelAttribute Project project) {
        var newProject = projectService.createProject(project);
        return "redirect:/" + newProject.getName();
    }

    @PostMapping("/delete")
    public String deleteProject(@ModelAttribute Project project) {
        projectService.deleteProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{project-name}/subproject")
    public String subProjectPage() {
        return "subProjectsPage";
    }

    @GetMapping("/subproject/{id}")
    public String findSubProject(@PathVariable int id, Model model) {
        model.addAttribute("subProject", projectService.findSubProject(id));
        return "subProjectsPage";
    }

    @GetMapping("/{project-name}/subproject/create")
    public String createSubProjectPage(Model model) {
        model.addAttribute("subProject", new SubProject());
        return "createSubProjectPage";
    }

    @PostMapping("/{project-name}/subproject/create")
    public String createSubProject(
            @PathVariable("project-name") String projectName,
            @ModelAttribute SubProject subProject) {

        projectService.createSubProject(subProject);

        return "redirect:/projects/" + projectName + "/subproject";
    }

    @PostMapping("/subproject/delete/{id}")
    public String deleteSubProject(@PathVariable int id) {
        projectService.deleteSubProject(id);
        return "redirect:/projects/";
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