//package com.example.projectcalculator.controller;
//
//import com.example.projectcalculator.model.SubProject;
//import com.example.projectcalculator.service.ProjectService;
//import org.springframework.web.bind.annotation.*;
//
//@org.springframework.stereotype.Controller
//@RequestMapping("/prototype")
//public class PrototypeController {
//
//    private final ProjectService service;
//
//    public PrototypeController(ProjectService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/")
//    public String loginPage() {
//        return "loginPage";
//    }
//
//    @GetMapping("/home")
//    public String homepage(Model model) {
//        model.addAttribute("projects", service.findAllProjects());
//        return "homePage";
//    }
//
//    @GetMapping("/create")
//    public String createProjectPage() {
//        return "projectsPage";
//    }
//
//    @GetMapping("/project-name/subproject")
//    public String subProjectPage() {
//        return "subProjectsPage";
//    }
//
//    @PostMapping("/project/{projectId}/subproject/create")
//    public String createSubProject(
//            @PathVariable int projectId,
//            @ModelAttribute SubProject subProject) {
//
//        subProject.setProjectId(projectId);
//
//        service.createSubProject(subProject);
//
//        return "redirect:/prototype/project/" + projectId + "/subproject";
//    }
//
//    @GetMapping("/project-name")
//    public String taskPage() {
//        return "tasksPage";
//    }
//
//    @GetMapping("/project-name/subproject/addtask")
//    public String addTaskPage() {
//        return "addTasks";
//    }
//}