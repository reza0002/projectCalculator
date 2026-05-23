package com.example.projectcalculator.controller;

import com.example.projectcalculator.controller.ProjectController;
import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @Test
    void listProjects() throws Exception {
        when(service.findAllProjects()).thenReturn(List.of());

        mockMvc.perform(get("/project"))
                .andExpect(status().isOk())
                .andExpect(view().name("home-page"))
                .andExpect(model().attributeExists("projects"));
    }

    @Test
    void loginPage() throws Exception {
        mockMvc.perform(get("/project/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login-page"));
    }

//    @Test
//    void login_correct() throws Exception {
//        var user = new User(1, "user", "password", List.of());
//
//        when(service.userLogin("user", "password")).thenReturn(user);
//        mockMvc.perform(post("/project/login")
//                        .param("username", "user")
//                        .param("password", "password"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/project/user"));
//    }

    @Test
    void createProjectPage() throws Exception {
        when(service.findAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/project/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-project"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attributeExists("employees"));
    }

    @Test
    void projectOverview() throws Exception {
        var project = new Project();
        project.setId(1);
        project.setName("Test Project");

        when(service.findProject(1)).thenReturn(project);
        when(service.findSubProjectHours(1)).thenReturn(List.of());
        when(service.findEmployeesInProject(1)).thenReturn(List.of());
        when(service.calculateTotalHours(1)).thenReturn(40);
        when(service.ProjectDeadline(1)).thenReturn(LocalDate.now().plusDays(5));

        mockMvc.perform(get("/project/{projectId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("project-overview"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attributeExists("subProjects"))
                .andExpect(model().attributeExists("totalHours"))
                .andExpect(model().attribute("totalHours", 40))
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(model().attribute("totalPrice", 1200))
                .andExpect(model().attributeExists("estimatedDeadline"))
                .andExpect(model().attribute("estimatedDeadline", LocalDate.now().plusDays(5)))
                .andExpect(model().attributeExists("assignedEmployees"));
    }

    @Test
    void createProject() throws Exception {
        var newProject = new Project();
        newProject.setId(2);
        newProject.setName("New Project");

        when(service.saveProject(any(Project.class)))
                .thenReturn(newProject);

        mockMvc.perform(post("/project/save")
                .param("name", "New Project")
                .param("description", "Test Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/2"));
    }

    @Test
    void updateProject() throws Exception {
        mockMvc.perform(post("/project/{projectId}/edit", 1)
                .param("name", "Updated Project")
                .param("description", "Updated Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void deleteProject() throws Exception {
        mockMvc.perform(post("/project/{projectId}/delete", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }
}
//
