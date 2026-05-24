package com.example.projectcalculator.controller;

import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.service.ProjectService;
import com.example.projectcalculator.validation.LoginValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService service;

    @MockitoBean
    private LoginValidation validation;

    @Test
    void createTaskPage_ShouldReturnAddTaskPage() throws Exception{
        SubProject subProject = new SubProject();
        subProject.setId(1);
        subProject.setName("Test Test");

        when(service.findSubProject(1)).thenReturn(subProject);
        when(service.findEmployeesInProject(1)).thenReturn(List.of());

        mockMvc.perform(get("/task/1/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-task"));

    }

    @Test
    void createTask_ValidTaskRedirictToSubPage() throws Exception{
        Task newTask = new Task();
        newTask.setId(99);
        newTask.setSubProjectId(1);

        when(service.saveTask(any(Task.class))).thenReturn(newTask);
        mockMvc.perform(post("/task/create")
                .param("name","Test task")
                .param("hours", "5")
                .param("pricePerHour", "1200")
                .param("subProjectId", "1")
                .param("user_id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subproject/1"));
    }

    @Test
    void deleteTask_ValidIdRedirectsToSubproject() throws Exception{
        Task task = new Task();
        task.setId(1);
        task.setSubProjectId(1);
        when(service.findTaskById(1)).thenReturn(task);

        mockMvc.perform(post("/task/1/delete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateTask_redirectsToSubproject() throws Exception{
        Task task = new Task();
        task.setId(1);
        task.setSubProjectId(1);
        when(service.findTaskById(1)).thenReturn(task);

        mockMvc.perform(post("/task/1/update")
                    .param("name", "updated Task")
                    .param("hours", "8")
                    .param("pricePerHour", "1200"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void markDone_ValidIdredirectsToSubproject() throws Exception{
        Task task = new Task();
        task.setId(1);
        task.setSubProjectId(1);
        when(service.findTaskById(1)).thenReturn(task);

        mockMvc.perform(post("/task/1/update"))
                .andExpect(status().is3xxRedirection());
    }
}
