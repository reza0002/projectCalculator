package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.Tasks;
import com.example.projectcalculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.beans.Transient;
import java.util.List;

@org.springframework.stereotype.Repository

@Repository
public class ProjectRepository {

    private final JdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    public ProjectRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Transactional
    public Project saveProject(Project project) {

    }

    @Transactional
    public SubProject saveSubProject(SubProject subProject){

    }

    @Transactional
    public Tasks saveTasks(Tasks tasks){

    }

    @Transactional
    public boolean deleteProject(Project project){

    }

    @Transactional
    public boolean deleteSubProject(SubProject subProject){

    }

    @Transactional
    public boolean deleteTasks(Tasks tasks){

    }

    public User login(String username, String password){

    }

    @Transactional
    public User findUser(String username){

    }

    private List<Project> findProjects(Project project){

    }

    private List<SubProject> findSubProjects(SubProject subProject){

    }

    private List<Tasks> findTasks(Tasks tasks){

    }

    public void updateProject(String username, Project project){

    }

    public void updateSubProject(SubProject subProject){

    }

    public void updateTasks(Tasks tasks){

    }
}