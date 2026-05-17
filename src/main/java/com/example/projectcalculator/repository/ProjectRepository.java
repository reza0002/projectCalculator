package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.projectcalculator.rowmapper.ProjectRowMapper;
import com.example.projectcalculator.rowmapper.UserRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ProjectRepository {

    private final JdbcTemplate template;

    public ProjectRepository(JdbcTemplate template) {
        this.template = template;
    }

//    @Transactional
//    public Project saveProject(Project project) {
//
//    }
//
//    @Transactional
//    public SubProject saveSubProject(SubProject subProject){
//
//    }
//
//
//    @Transactional
//    public boolean deleteProject(Project project){
//
//    }
//
//    @Transactional
//    public boolean deleteSubProject(SubProject subProject){
//
//    }
//
//
//    public User login(String username, String password){
//
//    }
//
//    @Transactional
//    public User findUser(String username){
//
//    }

    @Transactional
    public Project createProject(Project project) {
        String sql = "INSERT INTO project (name, project_leader, description, id) VALUES (?, ?)";
        template.update(sql, project.getName(), project.getDescription(), project.getProjectLeader().getId());
        return project;
    }

//    private List<Project> findProjects(Project project){
//
//    }
//
//    private List<SubProject> findSubProjects(SubProject subProject){
//
//    }


    public void addTask(Task task){
        String sql =
                "INSERT INTO task (name, hours, price_per_hour, sub_project_id) VALUES (?, ?, ?, ?)";
        template.update(sql,task.getName(),task.getHours(), task.getPricePerHour(),task.getSub_project_id());
    }


    public void deleteTask(int id){
        String sql =
                "DELETE FROM task WHERE id = ?";
        template.update(sql, id);
    }

    @Transactional
    public Task saveTasks(Task task){
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO task (name, hours, price_per_hour, sub_project_id) VALUES (?, ?, ?, ?)";

        template.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getHours());
            ps.setInt(3, task.getPricePerHour());
            ps.setInt(4, task.getSub_project_id());
            return ps;
        }, keyHolder);

        int taskId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        task.setId(taskId);
        return task;

    }


    public List<Task> findTasksBySubproject(int sub_project_id) {
        String sql =
                "SELECT * FROM task WHERE sub_project_id = ?";

        final RowMapper<Task> rowMapper = ((rs, rowNum) -> {
            final Task task = new Task(
                    rs.getString("name"),
                    rs.getInt("price_per_hour"),
                    rs.getInt("hours")
            );
            return task;
        });

        return template.query(sql, rowMapper, sub_project_id );
    }


    public Task findTaskById(int id){
        String sql =
                "SELECT * FROM task WHERE  id = ?";

        final RowMapper<Task> rowMapper = ((rs, rowNum) -> {
            final Task task = new Task(
                    rs.getString("name"),
                    rs.getInt("price_per_hour"),
                    rs.getInt("hours")
            );
            return task;
        });

        return template.queryForObject(sql, rowMapper, id );
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

    public void updateTask(Task task){
        final String sql = """
                UPDATE task
                SET
                name = ?,
                hours = ?,
                price_per_hour = ?
                WHERE id = ?;
                """;
        template.update(sql, task.getName(), task.getHours(), task.getPricePerHour());
    }
}