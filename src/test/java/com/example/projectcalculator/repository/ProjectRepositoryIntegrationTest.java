package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.model.User;
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

    @Test
    void saveProject() {
        var project = new Project();
        project.setName("Save Test Project");
        project.setDescription("Test Description");

        User leader = new User();
        leader.setId(1);
        project.setProjectLeader(leader);

        Project saved = repository.saveProject(project);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Save Test Project", saved.getName());
    }

    @Test
    void saveSubProject() {
        var subProject = new SubProject();
        subProject.setName("Save Test SubProject");
        subProject.setDescription("Test");
        subProject.setHours(10);
        subProject.setPricePerHour(1200);
        subProject.setProjectId(1);

        SubProject saved = repository.saveSubProject(subProject);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Save Test SubProject", saved.getName());
    }

    @Test
    void saveTasks() {
        var task = new Task();
        task.setName("Save Test Task");
        task.setHours(5);
        task.setPricePerHour(1200);
        task.setSubProjectId(1);
        task.setAssigneeId(1);

        Task saved = repository.saveTasks(task);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Save Test Task", saved.getName());
    }

    // ===== DELETE TESTS =====
    @Test
    void deleteProject() {
        var project = new Project();
        project.setName("Delete Test Project");
        project.setDescription("Test");
        User leader = new User();
        leader.setId(1);
        project.setProjectLeader(leader);
        Project saved = repository.saveProject(project);

        int projectId = saved.getId();
        repository.deleteProject(projectId);

        assertThrows(Exception.class, () -> repository.findProject(projectId));
    }

    @Test
    void deleteSubProject() {
        var subProject = new SubProject();
        subProject.setName("Delete Test SubProject");
        subProject.setDescription("Test");
        subProject.setHours(10);
        subProject.setPricePerHour(1200);
        subProject.setProjectId(1);
        SubProject saved = repository.saveSubProject(subProject);

        int subProjectId = saved.getId();
        repository.deleteSubProject(subProjectId);

        assertThrows(Exception.class, () -> repository.findSubProject(subProjectId));
    }

    @Test
    void deleteTask() {
        var task = new Task();
        task.setName("Delete Test Task");
        task.setHours(5);
        task.setPricePerHour(1200);
        task.setSubProjectId(1);
        task.setAssigneeId(1);
        Task saved = repository.saveTasks(task);

        int taskId = saved.getId();
        repository.deleteTask(taskId);

        assertThrows(Exception.class, () -> repository.findTaskById(taskId));
    }
}

