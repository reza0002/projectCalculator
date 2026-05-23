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
        assertEquals("Test Project", project.getName());
        assertEquals("Test Description", project.getDescription());
    }

    @Test
    void findSubProjectsForProject() {
        List<SubProject> subProjects = repository.findSubProjectsForProject(1);

        assertFalse(subProjects.isEmpty());
        assertEquals("Backend API", subProjects.get(0).getName());
    }

    @Test
    void findTasksBySubproject() {
        List<Task> tasks = repository.findTasksBySubproject(1);

        assertFalse(tasks.isEmpty());
        assertEquals("Database Setup", tasks.get(0).getName());
        assertEquals(3, tasks.get(0).getHours());
    }
}