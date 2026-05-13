package com.example.projectcalculator.service;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Tasks;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.repository.ProjectRepository;
import org.springframework.stereotype.Service;


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

    public Project findProject(Project project){
        return repository.findProject(project);
    }

    public SubProject findSubProject(SubProject subProject){
        return repository.findSubProject(subProject);
    }

    public Tasks findTasks(Tasks tasks){
        return repository.findTasks(tasks);
    }

    public Project saveProject(Project project){
        return repository.saveProject(project);
    }

    public SubProject saveSubProject(SubProject subProject){
        return repository.saveSubProject(subProject);
    }

    public Tasks saveTasks(Tasks tasks){
        return repository.saveTasks(tasks);
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
