package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.rowmapper.ProjectRowMapper;
import com.example.projectcalculator.model.*;
import com.example.projectcalculator.rowmapper.ProjectRowMapper;
import com.example.projectcalculator.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.beans.Transient;
import java.util.List;


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

    public boolean login(String username, String password) {
        final String sql = "SELECT * FROM user WHERE user.name = ? AND user.password = ?";
        return !template.query(sql, new UserRowMapper(), username, password).isEmpty();
    }

    public User findUser(String username) {
        final String sql = """
                SELECT * FROM user
                WHERE user.name = ?
                """;
        return template.queryForObject(sql, new UserRowMapper(), username);
    }

    public List<Project> findAllProjects() {
        String sql = """
                    SELECT project.id,
                           project.name,
                           project.project_leader,
                           user.name AS user_name,
                           user.email AS user_email
                           -- , project.description
                    FROM project
                    JOIN user ON project.project_leader = user.id
                """;
        return template.query(sql, (rs, rowNum) -> {
            Project project = new Project();
            project.setId(rs.getInt("id"));
            project.setName(rs.getString("name"));
            project.setProjectLeader(mapProjectLeader(rs));
            // project.setDescription(rs.getString("description"));
            return project;
        });
    }

    private User mapProjectLeader(ResultSet rs) throws SQLException {
        User projectLeader = new User();
        projectLeader.setId(rs.getInt("project_leader"));
        projectLeader.setName(rs.getString("user_name"));
        projectLeader.setEmail(rs.getString("user_email"));
        return projectLeader;
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

    public void updateSubProject(SubProject subProject){

    }

    public void updateTasks(Task task){

    }
}