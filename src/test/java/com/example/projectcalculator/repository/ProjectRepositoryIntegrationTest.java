package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProjectRepositoryIntegrationTest {

    @Autowired
    private ProjectRepository repository;

    @Test
    void findProject() {
        Project project = repository.findProject(1);
        assertNotNull(project);
    }

    @Test
    void findSubProjectsForProject() {
        List<SubProject> subProjects = repository.findSubProjectsForProject(1);
        assertFalse(subProjects.isEmpty());
    }

    @Test
    void findTasksBySubproject() {
        List<Task> tasks = repository.findTasksBySubproject(1);
        assertFalse(tasks.isEmpty());
    }

    @Test
    void updateProject() {

        Project project = repository.findProject(1);

        project.setName("Updated Project");

        repository.updateProject(project);

        Project updatedProject = repository.findProject(1);

        assertEquals("Updated Project", updatedProject.getName());
    }

    @Test
    void updateSubProject() {

        SubProject subProject = repository.findSubProjectsForProject(1).get(0);

        subProject.setName("Updated SubProject");

        repository.updateSubProject(subProject);

        SubProject updatedSubProject =
                repository.findSubProjectsForProject(1).get(0);

        assertEquals("Updated SubProject",
                updatedSubProject.getName());
    }

    @Test
    void updateTask() {

        Task task = repository.findTasksBySubproject(1).get(0);

        task.setName("Updated Task");

        repository.updateTask(task);

        Task updatedTask =
                repository.findTasksBySubproject(1).get(0);

        assertEquals("Updated Task",
                updatedTask.getName());
    }
}