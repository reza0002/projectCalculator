package com.example.projectcalculator.Service;

import com.example.projectcalculator.exception.InvalidInputException;
import com.example.projectcalculator.exception.ProjectNotFound;
import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.repository.ProjectRepository;
import com.example.projectcalculator.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
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
    void findSubProject_Exist(){
        SubProject subProject = new SubProject();
        subProject.setId(10);
        when(repository.findSubProject(10)).thenReturn(subProject);

        SubProject test = service.findSubProject(10);

        assertEquals(10, test.getId());
    }

    @Test
    void findTaskById_returnsTask(){
        Task task = new Task();
        task.setId(99);
        when(repository.findTaskById(99)).thenReturn(task);

        Task test = service.findTaskById(99);
        assertEquals(99, test.getId());
    }


    @Test
    void saveTask_NegativeHours(){
        Task task = new Task();
        task.setName("test");
        task.setHours(-6);
        task.setPricePerHour(1200);

        assertThrows(InvalidInputException.class, ()-> service.saveTask(task));
    }

    @Test
    void saveTask_Saves(){
        Task task = new Task();
        task.setName("test");
        task.setHours(8);
        task.setPricePerHour(1200);
        when(repository.saveTasks(task)).thenReturn(task);

        Task testTask = service.saveTask(task);

        assertEquals("test", testTask.getName());
    }

    @Test
    void saveProject_noProjectLeader(){
        Project project = new Project();
        project.setName("TestProject");
        project.setDescription("Bla bla bla");
        project.setProjectLeader(null);

        assertThrows(InvalidInputException.class, () -> service.saveProject(project));
    }

    @Test
    void updateTask_emptyName(){
        Task task = new Task();
        task.setName("");
        task.setHours(8);
        task.setPricePerHour(1200);

        assertThrows(InvalidInputException.class, ()-> service.updateTask(task));
    }

    @Test
    void deleteProject_NotFound(){
        when(repository.findProject(1)).thenReturn(null);
        assertThrows(ProjectNotFound.class, ()-> service.deleteProject(1));
    }

    @Test
    void calculateTotalHours_returnsCorrect(){
        SubProject sub1 = new SubProject(); sub1.setId(1);
        SubProject sub2 = new SubProject(); sub2.setId(2);

        Task task1 = new Task();
        task1.setHours(5);
        Task task2 = new Task();
        task2.setHours(2);
        Task task3 = new Task();
        task3.setHours(10);

        when(repository.findSubProjectsForProject(99)).thenReturn(List.of(sub1,sub2));
        when(repository.findTasksBySubproject(1)).thenReturn(List.of(task1,task2));
        when(repository.findTasksBySubproject(2)).thenReturn(List.of(task3));

        assertEquals(17, service.calculateTotalHours(99));
    }

    @Test
    void calculateHoursSubproject_CorrectTest(){
        Task task1 = new Task();
        task1.setHours(5);

        Task task2 = new Task();
        task2.setHours(10);

        List<Task> testTask = new ArrayList<>();
        testTask.add(task1);
        testTask.add(task2);

        when(repository.findTasksBySubproject(99)).thenReturn(testTask);

        assertEquals(15, service.calculateHoursSubproject(99));
    }









}
