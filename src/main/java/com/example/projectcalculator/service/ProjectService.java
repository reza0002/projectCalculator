package com.example.projectcalculator.service;

import com.example.projectcalculator.model.*;
import com.example.projectcalculator.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public User userLogin(String username, String password){
        return repository.login(username, password);
    }


    public User findUser(String username) {
        return repository.findUser(username);
    }

    public List<Project> findProjects() {
        return repository.findProjects();
    }

    public Project findProject(Project project){
        return repository.findProject(project);
    }

    public SubProject findSubProject(SubProject subProject){
        return repository.findSubProject(subProject);
    }

    public void addTask(Task task){
        repository.addTask(task);
    }

    public Task findTasks(Task task){
        return repository.findTasks(task);
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

    public void updateProject(){

    }

    public void updateSubProject(){

    }

    public void updateTasks(){

    }

    public void deleteProject(Project project){

    }

    public void deleteSubProject(SubProject subProject){

    }

    public void deleteTasks(Tasks tasks){

    }

}
