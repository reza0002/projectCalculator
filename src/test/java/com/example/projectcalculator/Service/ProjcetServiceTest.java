package com.example.projectcalculator.Service;

import com.example.projectcalculator.exception.InvalidInputException;
import com.example.projectcalculator.exception.ProjectNotFound;
import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.repository.ProjectRepository;
import com.example.projectcalculator.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ProjcetServiceTest {
    private ProjectRepository repository;
    private ProjectService service;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(ProjectRepository.class);
        service = new ProjectService(repository);
    }

    @Test
    void userLogin_nullName(){
        assertThrows(InvalidInputException.class, () -> service.userLogin(null, "blabla"));
    }

    @Test
    void findProject_notFound(){
        when(repository.findProject(1)).thenReturn(null);
        assertThrows(ProjectNotFound.class, ()-> service.findProject(1));
    }

    @Test
    void findProject_Exist(){
        Project project = new Project();
        project.setId(10);
        when(repository.findProject(10)).thenReturn(project);

        Project test = service.findProject(10);

        assertEquals(10, test.getId());
    }

    @Test
    void saveTask_NegativeHours(){
        Task task = new Task();
        task.setName("test");
        task.setHours(-6);
        task.setPricePerHour(1200);

        assertThrows(InvalidInputException.class, ()-> service.saveTask(task));
    }




}
