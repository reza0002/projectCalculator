package com.example.projectcalculator.service;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Task;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public boolean userLogin(String username, String password) {
        return repository.login(username, password);
    }


    public User findUser(String username) {
        return repository.findUser(username);
    }

    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    public List<Project> findAllProjects() {
        return repository.findAllProjects();
    }

    public Project findProject(int projectId) {
        return repository.findProject(projectId);
    }

    // overload hvis man vil brug navn i stedet for id
    public Project findProject(String projectName) {
        return repository.findProject(projectName);
    }

    public SubProject findSubProject(int subProjectId) {
        return repository.findSubProject(subProjectId);
    }

    public List<SubProject> findSubProjectsForProject(int projectId) {
        return repository.findSubProjectsForProject(projectId);
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }

    public Task saveTask(Task task) {
        return repository.saveTasks(task);
    }

    public void deleteTask(int id) {
        repository.deleteTask(id);
    }

    public Task findTaskById(int id) {
        return repository.findTaskById(id);
    }

    public List<Task> findTasksBySubproject(int subProjectId) {
        return repository.findTasksBySubproject(subProjectId);
    }

    public Project saveProject(Project project) {
        final int leaderId = project.getProjectLeader().getId();
        project.setEmployees(repository.findEmployeesById(project.getEmployeeIds()));

        project.getEmployees().removeIf(user -> user.getId() == leaderId);
        return repository.saveProject(project);
    }

    public SubProject saveSubProject(SubProject subProject) {

        // kæmpe hack, skal rettes senere
        subProject.setDescription("");
        subProject.setPricePerHour(1200);
        var tasks = findTasksBySubproject(subProject.getId());
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getHours();
        }
        subProject.setHours(totalHours);

        return repository.saveSubProject(subProject);
    }

    public Task saveTasks(Task task) {
        return repository.saveTasks(task);
    }

    public Project updateProject(Project project) {
        return repository.updateProject(project);

    }

    public void updateSubProject(SubProject subProject) {
        repository.updateSubProject(subProject);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    public void deleteProject(int projectId) {
        repository.deleteProject(projectId);
    }

    public SubProject createSubProject(SubProject subProject) {
        return repository.createSubProject(subProject);
    }

    public void deleteSubProject(int id) {
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
