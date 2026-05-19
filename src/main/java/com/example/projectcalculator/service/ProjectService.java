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

    public List<Project> findAllProjects() {
        return repository.findAllProjects();
    }

    public Project findProject(Project project) {
        return repository.findProject(project);
    }

    public void createProject(Project project) {
        repository.createProject(project);
    }

    public SubProject findSubProject(int id) {
        return repository.findSubProject(id);
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

    public List<Task> findTasksBySubproject(int sub_project_id) {
        return repository.findTasksBySubproject(sub_project_id);
    }

    public Project saveProject(Project project){
        return repository.saveProject(project);
    }

    public SubProject saveSubProject(SubProject subProject){
        return repository.saveSubProject(subProject);
    }

   public Task saveTasks(Task task){
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

    public Project deleteProject(Project project) {
        return repository.deleteProject(project);

    }

    public SubProject createSubProject(SubProject subProject) {
        return repository.createSubProject(subProject);
    }

    public void deleteSubProject(int id) {
        repository.deleteSubProject(id);
    }

    //public void deleteTasks(Task task) {

    //}

}
