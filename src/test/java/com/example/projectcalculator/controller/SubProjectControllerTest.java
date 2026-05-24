package com.example.projectcalculator.controller;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.service.ProjectService;
import com.example.projectcalculator.validation.LoginValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubProjectController.class)
public class SubProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @MockitoBean
    private LoginValidation validation;

    @Test
    void subProjectPage() throws Exception {
        var subProject = new SubProject();
        subProject.setId(1);
        subProject.setName("test subProject");

        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        List<Task> tasks = List.of(task1, task2, task3);

        User testUser1 = new User("Søren Berlev");
        User testUser2 = new User("Christian Dahl");
        User testUser3 = new User("Kim Larsen");
        User testUser4 = new User("Franz Beckerlee");
        List<User> allUsers = List.of(testUser1, testUser2, testUser3, testUser4);

        when(service.findSubProject(subProject.getId())).thenReturn(subProject);
        when(service.findTasksBySubproject(subProject.getId())).thenReturn(tasks);
        when(service.findAllUsers()).thenReturn(allUsers);

        mockMvc.perform(get("/subproject/{subProjectId}", subProject.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("sub-project-tasks"))
                .andExpect(model().attributeExists("subProject"))
                .andExpect(model().attribute("subProject", subProject))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attribute("tasks", tasks))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", allUsers));
    }

    @Test
    void createSubProjectPage() throws Exception {
        var project = new Project();
        project.setId(1);
        project.setName("project");

        var subProject = new SubProject();
        subProject.setId(1);
        subProject.setName("subProject");

        when(service.findProject(project.getId())).thenReturn(project);
        when(service.findSubProject(subProject.getId())).thenReturn(subProject);

        mockMvc.perform(get("/subproject/{projectId}/create", subProject.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("create-sub-project"))
                .andExpect(model().attributeExists("subProject"))
                .andExpect(model().attribute("subProject", subProject))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attribute("project",project));
    }

    @Test
    void saveSubProject() throws Exception {
        mockMvc.perform(post("/subproject/save")
                .param("name", "new subProject")
                .param("projectId", "1"))
                .andExpect(status().is3xxRedirection());

        verify(service).saveSubProject(any(SubProject.class));
    }

    @Test
    void deleteSubProject() throws Exception {
        mockMvc.perform(post("/subproject/{projectId}/{subProjectId}/delete", 1, 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1"));

        verify(service).deleteSubProject(1);
    }
}
