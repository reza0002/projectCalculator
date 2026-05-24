package com.example.projectcalculator.controller;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
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

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(SubProjectController.class)
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

        when(service.findSubProject(1)).thenReturn(subProject);
        when(service.findTasksBySubproject(1)).thenReturn(List.of());
        when(service.findAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/subproject/{subProjectId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("sub-project-tasks"))
                .andExpect(model().attributeExists("subProject"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void createSubProjectPage() throws Exception {
        var project = new Project();
        project.setId(1);
        project.setName("Test Project");

        when(service.findProject(1)).thenReturn(project);

        mockMvc.perform(get("/subproject/{projectId}/create", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("create-sub-project"))
                .andExpect(model().attributeExists("subProject"))
                .andExpect(model().attributeExists("project"));
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
