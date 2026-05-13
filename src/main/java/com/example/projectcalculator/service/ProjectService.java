package com.example.projectcalculator.service;

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



}
