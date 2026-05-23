package com.example.projectcalculator.service;

import com.example.projectcalculator.exception.InvalidInputException;
import com.example.projectcalculator.exception.ProjectNotFound;
import com.example.projectcalculator.exception.SubProjectNotFound;
import com.example.projectcalculator.exception.TaskNotFound;
import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public boolean userLogin(String username, String password) {

        if(username == null || password == null)
            throw new InvalidInputException(
                    "Username or password cannot be empty");
        return repository.login(username, password);
    }


    public User findUser(String username) {
        User user = repository.findUser(username);

        if(user == null){
            throw new InvalidInputException(
                    "User not found");
        }
        return user;
    }

    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    public List<Project> findAllProjects() {
        return repository.findAllProjects();
    }

    public Project findProject(int projectId) {
        Project project = repository.findProject(projectId);

        if(project == null) {
            throw new ProjectNotFound(projectId);
        }
        return project;
    }

    // overload hvis man vil brug navn i stedet for id
    public Project findProject(String projectName) {
        Project project = repository.findProject(projectName);

        if(project == null) {
            throw new ProjectNotFound(-1);
        }
        return project;
    }

    public SubProject findSubProject(int subProjectId) {
        SubProject subProject =
                repository.findSubProject(subProjectId);

        if(subProject == null) {
            throw new SubProjectNotFound(subProjectId);
        }

        return subProject;
    }

    public List<SubProject> findSubProjectsForProject(int projectId) {

        findProject(projectId);

        return repository.findSubProjectsForProject(projectId);
    }

    public void addTask(Task task) {

        if(task.getName() == null ||
                task.getName().isBlank()) {
            throw new InvalidInputException(
                    "Task name cannot be empty");
        }

        if(task.getHours() < 0) {
            throw new InvalidInputException(
                    "Hours cannot be negative");
        }

        if(task.getPricePerHour() < 0) {
            throw new InvalidInputException(
                    "Price per hour cannot be negative");
        }

        repository.saveTasks(task);
    }

    public Task saveTask(Task task) {
        if(task.getName() == null || task.getName().isBlank()) {
            throw new InvalidInputException("Task name cannot be empty");
        }

        if(task.getHours() < 0) {
            throw new InvalidInputException("Hours cannot be negative");
        }

        if(task.getPricePerHour() < 0) {
            throw new InvalidInputException("Price per hour cannot be negative");
        }

        return repository.saveTasks(task);
    }

    public void deleteTask(int id) {

        findTaskById(id);

        repository.deleteTask(id);
    }

    public Task findTaskById(int id) {
        Task task = repository.findTaskById(id);

        if(task == null) {
            throw new TaskNotFound(id);
        }

        return task;
    }

    public List<Task> findTasksBySubproject(int subProjectId) {

        findSubProject(subProjectId);

        return repository.findTasksBySubproject(subProjectId);
    }

    public Project saveProject(Project project) {

        if (project.getName() == null || project.getName().isBlank()) {
            throw new InvalidInputException(
                    "Project name cannot be empty");
        }

        if (project.getDescription() == null || project.getDescription().isBlank()) {
            throw new InvalidInputException(
                    "Project description cannot be empty");
        }

        if (project.getProjectLeader() == null ||
                project.getProjectLeader().getId() <= 0) {
            throw new InvalidInputException(
                    "Project must have a valid project leader");
        }

        final int leaderId = project.getProjectLeader().getId();

        if (project.getEmployeeIds() != null) {
            project.setEmployees(repository.findEmployeesById(project.getEmployeeIds()));
            project.getEmployees().removeIf(user -> user.getId() == leaderId);
        }
        return repository.saveProject(project);
    }

    public SubProject saveSubProject(SubProject subProject) {

        if (subProject.getName() == null || subProject.getName().isBlank()) {
            throw new InvalidInputException("Subproject name cannot be empty");
        }

        if (subProject.getPricePerHour() < 0) {
            throw new InvalidInputException("Price per hour cannot be negative");
        }

        if (subProject.getHours() < 0) {
            throw new InvalidInputException("Hours cannot be negative");
        }

        return repository.saveSubProject(subProject);
    }

    public Task saveTasks(Task task) {
        return repository.saveTasks(task);
    }

    public Project updateProject(Project project) {

        if(project.getName() == null ||
                project.getName().isBlank()) {
            throw new InvalidInputException(
                    "Project name cannot be empty");
        }

        if(project.getDescription() == null ||
                project.getDescription().isBlank()) {
            throw new InvalidInputException(
                    "Project description cannot be empty");
        }
        return repository.updateProject(project);

    }

    public void updateSubProject(SubProject subProject) {

        if(subProject.getName() == null ||
                subProject.getName().isBlank()) {
            throw new InvalidInputException(
                    "Subproject name cannot be empty");
        }

        if(subProject.getPricePerHour() < 0) {
            throw new InvalidInputException(
                    "Price per hour cannot be negative");
        }

        if(subProject.getHours() < 0) {
            throw new InvalidInputException(
                    "Hours cannot be negative");
        }

        repository.updateSubProject(subProject);
    }

    public void updateTask(Task task) {

        if (task.getName() == null || task.getName().isBlank()) {
            throw new InvalidInputException("Task name cannot be empty");
        }

        if (task.getHours() < 0) {
            throw new InvalidInputException("Hours cannot be negative");
        }

        if (task.getPricePerHour() < 0) {
            throw new InvalidInputException("Price per hour cannot be negative");
        }
        repository.updateTask(task);
    }

    public void deleteProject(int projectId) {

        findProject(projectId);

        repository.deleteProject(projectId);
    }

    public SubProject createSubProject(SubProject subProject) {
        if(subProject.getName() == null || subProject.getName().isBlank()) {
            throw new InvalidInputException("Subproject name cannot be empty");
        }

        if(subProject.getPricePerHour() < 0) {
            throw new InvalidInputException("Price per hour cannot be negative");
        }

        if(subProject.getHours() < 0) {
            throw new InvalidInputException("Hours cannot be negative");
        }

        return repository.createSubProject(subProject);
    }

    public void deleteSubProject(int id) {

        findSubProject(id);

        repository.deleteSubProject(id);
    }

    public int calculateTotalHours(int projectId) {
        List<SubProject> subProjects = repository.findSubProjectsForProject(projectId);
        int totalHours = 0;
        for (SubProject subProject : subProjects) {
            List<Task> tasks = repository.findTasksBySubproject(subProject.getId());
            for (Task task : tasks) {
                totalHours += task.getHours();
            }
        }
        return totalHours;
    }

    public LocalDate ProjectDeadline(int projectId) {
        int maxEmployeeHours = repository.findMaxEmployeeHours(projectId);
        int days = maxEmployeeHours / 8;
        return LocalDate.now().plusDays(days);
    }

    public List<User> findEmployeesInProject(int projectId) {
        return repository.findEmployeesInProject(projectId);
    }

    public int calculateHoursSubproject(int subProjectId) {
        List<Task> tasks = repository.findTasksBySubproject(subProjectId);
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getHours();
        }
        return totalHours;
    }

    public List<SubProject> findSubProjectHours(int projectId) {
        List<SubProject> subProjects = repository.findSubProjectsForProject(projectId);
        for (SubProject subProject : subProjects) {
            int hours = calculateHoursSubproject(subProject.getId());
            subProject.setHours(hours);
        }
        return subProjects;
    }

}
