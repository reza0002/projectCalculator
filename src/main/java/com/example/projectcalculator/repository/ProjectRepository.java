package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.*;
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
    public Task saveTasks(Task tasks){

    }

    @Transactional
    public boolean deleteProject(Project project){

    }

    @Transactional
    public boolean deleteSubProject(SubProject subProject){

    }

    @Transactional
    public boolean deleteTasks(Task task){

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

    public void addTask(Task task){
        String sql =
                "INSERT INTO task (name, hours, price_per_hour, sub_project_id) VALUES (?, ?, ?, ?)";
        template.update(sql,task.getName(),task.getHours(), task.getPricePerHour(),task.getSub_project_id());
    }

    private List<Task> findTasks(Task task){

    }

    public void updateProject(String username, Project project){

    }

    public void updateSubProject(SubProject subProject) {
        final String sql = """
                        UPDATE sub_project
                        SET
                            name = ?,
                            description = ? ,
                            hours = ?,
                            price_per_hour = ?
                        WHERE sub_project.id = ?;
                """;
        // går ud fra at subprojektet kommer med id'et sat
        template.update(sql,
                subProject.getName(),
                subProject.getDescription(),
                subProject.getHours(),
                subProject.getPrice_per_hour(),
                subProject.getId()
        );
    }

    public void updateTasks(Task task){

    }
}