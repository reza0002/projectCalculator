package com.example.projectcalculator.controller;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.service.ProjectService;
import com.example.projectcalculator.validation.LoginValidation;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @MockitoBean
    private LoginValidation loginValidation;

    @Test
    void getAllProjects_ShouldReturnHomePage_WhenProjectsExist() throws Exception {
        User projectLeader = new User("Kim Larsen");
        Project project = new Project("Controller test", projectLeader, "Beskrivelse");
        List<Project> projects = List.of(project);
        when(service.findAllProjects()).thenReturn(projects);

        mockMvc.perform(get("/project"))
                .andExpect(status().isOk())
                .andExpect(view().name("home-page"))
                .andExpect(model().attributeExists("projects"))
                .andExpect(model().attribute("projects", projects));
    }

    @Test
    void getProject_ShouldReturnProjectOverviewWithAllAttributes_WhenProjectExists() throws Exception {
        User projectLeader = new User("Kim Larsen");
        User testUser1 = new User("Laura Madsen");
        User testUser2 = new User("Søren Andersen");
        User testUser3 = new User("Christian Dahl");
        List<User> employeesInProject = List.of(testUser1, testUser2, testUser3);

        SubProject testSubProject1 = new SubProject("Test sub-projekt", "Test beskrivelse", 1200, 10, 1, false);
        SubProject testSubProject2 = new SubProject("Test sub-projekt", "Test beskrivelse", 1200, 10, 2, false);
        SubProject testSubProject3 = new SubProject("Test sub-projekt", "Test beskrivelse", 1200, 10, 3, false);
        List<SubProject> subProjects = List.of(testSubProject1, testSubProject2, testSubProject3);

        Project project = new Project(1, "Test projekt", projectLeader, "Test beskrivelse");

        int totalHours = 30;
        LocalDate deadline = LocalDate.now().plusDays(5);
        int totalPrice = totalHours * 1200;

        when(service.findProject(1)).thenReturn(project);
        when(service.findSubProjectHours(1)).thenReturn(subProjects);
        when(service.findEmployeesInProject(1)).thenReturn(employeesInProject);
        when(service.calculateTotalHours(1)).thenReturn(totalHours);
        when(service.ProjectDeadline(1)).thenReturn(deadline);

        mockMvc.perform(get("/project/{projectId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("project-overview"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attributeExists("subProjects"))
                .andExpect(model().attribute("subProjects", subProjects))
                .andExpect(model().attributeExists("totalHours"))
                .andExpect(model().attribute("totalHours", totalHours))
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(model().attribute("totalPrice", totalPrice))
                .andExpect(model().attributeExists("estimatedDeadline"))
                .andExpect(model().attribute("estimatedDeadline", deadline))
                .andExpect(model().attributeExists("assignedEmployees"))
                .andExpect(model().attribute("assignedEmployees", employeesInProject));
    }

    @Test
    void getCreateProjectForm_ShouldReturnCreateProjectPage_WithAvailableEmployees() throws Exception {
        Project project = new Project();

        User testUser1 = new User("Søren Berlev");
        User testUser2 = new User("Christian Dahl");
        User testUser3 = new User("Kim Larsen");
        User testUser4 = new User("Franz Beckerlee");
        List<User> allUsers = List.of(testUser1, testUser2, testUser3, testUser4);

        when(service.findAllUsers()).thenReturn(allUsers);

        mockMvc.perform(get("/project/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-project"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attributeExists("employees"))
                .andExpect(model().attribute("employees", allUsers));
    }

    @Test
    void saveProject_ShouldRedirectToProjectOverview_WhenProjectIsSaved() throws Exception {
        User projectLeader = new User("Kim Larsen");
        Project savedProject = new Project("Controller test", projectLeader, "Beskrivelse");
        savedProject.setId(1);

        when(service.saveProject(any(Project.class))).thenReturn(savedProject);

        mockMvc.perform(post("/project/save")
                        .param("name", "Controller test")
                        .param("projectLeader", String.valueOf(projectLeader))
                        .param("description", "Beskrivelse"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1"));

        ArgumentCaptor<Project> captor = ArgumentCaptor.forClass(Project.class);
        verify(service).saveProject(captor.capture());
        Project captured = captor.getValue();
        assertEquals("Controller test", captured.getName());
        assertEquals("Beskrivelse", captured.getDescription());
    }

    @Test
    void deleteProject_ShouldRedirectToProjectList_WhenProjectIsDeleted() throws Exception {
        mockMvc.perform(post("/project/{projectId}/delete", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void editProject_ShouldRedirectToProjectList_WhenProjectIsEdited() throws Exception {
        mockMvc.perform(post("/project/{projectId}/edit", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }
}