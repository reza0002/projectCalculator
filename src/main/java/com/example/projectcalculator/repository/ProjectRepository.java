package com.example.projectcalculator.repository;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.SubProject;
import com.example.projectcalculator.model.User;
import com.example.projectcalculator.model.*;
import com.example.projectcalculator.rowmapper.SubProjectRowMapper;
import com.example.projectcalculator.rowmapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Transactional
    public SubProject createSubProject(SubProject subProject) {
        String sql = """
                INSERT INTO sub_project
                (name, description, hours, price_per_hour, project_id)
                VALUES (?, ?, ?, ?, ?)
                """;
        template.update(
                sql,
                subProject.getName(),
                subProject.getDescription(),
                subProject.getHours(),
                subProject.getPrice_per_hour(),
                subProject.getProject_id()
        );
        return subProject;
    }

    @Transactional
    public Project saveProject(Project project) {
        String sql = """
                INSERT INTO project (name, project_leader, description) VALUES (?, ?, ?);
                """;
        template.update(sql,
                project.getName(),
                project.getProjectLeader().getId(),
                project.getDescription());

        return project;
    }

    @Transactional
    public SubProject saveSubProject(SubProject subProject) {
        String sql = """
                INSERT INTO sub_project
                (name, description, hours, price_per_hour, project_id)
                VALUES (?, ?, ?, ?, ?)
                """;
        template.update(
                sql,
                subProject.getName(),
                subProject.getDescription(),
                subProject.getHours(),
                subProject.getPrice_per_hour(),
                subProject.getProject_id()
        );
        return subProject;
    }

    @Transactional
    public Project deleteProject(Project project) {
        final String sql = """
                DELETE FROM project
                WHERE id = ?
                """;
        template.update(sql, project.getId());
        return project;
    }

    @Transactional
    public void deleteSubProject(int id) {
        String sql = "DELETE FROM sub_project WHERE id = ?";
        template.update(sql, id);
    }

    public boolean login(String username, String password) {
        var user = findUser(username);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    public User findUser(String username) {
        String sql = "SELECT * FROM user WHERE name = ?";
        return template.queryForObject(sql, new UserRowMapper(), username);
    }

    public List<User> findAllUsers() {
        return template.query("SELECT * FROM user", new UserRowMapper());
    }

    @Transactional
    public Project createProject(Project project) {
        KeyHolder keyholder = new GeneratedKeyHolder();
        String sql = "INSERT INTO project (name, project_leader) VALUES (?, ?)";
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getName());
            ps.setInt(2, project.getProjectLeader().getId());
            return ps;
        }, keyholder);

        project.setId(Objects.requireNonNull(keyholder.getKey()).intValue());
        return project;
    }

    public Project findProject(int projectId) {
        final String sql = """
                SELECT id, name
                FROM project
                WHERE id = ?
                """;
        final RowMapper<Project> rowMapper = (rs, rowNum) -> new Project(
                rs.getInt("id"),
                rs.getString("name")
        );

        return template.queryForObject(sql, rowMapper, projectId);
    }

    public Project findProject(String projectName) {
        final String sql = """
                SELECT id, name
                FROM project
                WHERE name = ?
                """;
        final RowMapper<Project> rowMapper = (rs, rowNum) -> new Project(
                rs.getInt("id"),
                rs.getString("name")
        );
        return template.queryForObject(sql, rowMapper, projectName);
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

    public SubProject findSubProject(int id) {
        String sql = """
                SELECT *
                FROM sub_project
                WHERE id = ?
                """;

        return template.queryForObject(
                sql,
                (rs, rowNum) -> new SubProject(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price_per_hour"),
                        rs.getInt("hours"),
                        rs.getInt("project_id")
                ),
                id
        );
    }

    public List<SubProject> findSubProjectsForProject(int projectId) {
        return template.query("SELECT * FROM sub_project WHERE project_id = ?", new SubProjectRowMapper(), projectId);
    }

    public void addTask(Task task) {
        String sql =
                "INSERT INTO task (name, hours, price_per_hour, sub_project_id) VALUES (?, ?, ?, ?)";
        template.update(sql, task.getName(), task.getHours(), task.getPricePerHour(), task.getSub_project_id());
    }

    public void deleteTask(int id) {
        String sql =
                "DELETE FROM task WHERE id = ?";
        template.update(sql, id);
    }

    @Transactional
    public Task saveTasks(Task task) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO task (name, hours, price_per_hour, sub_project_id) VALUES (?, ?, ?, ?)";

        template.update(connection -> {
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

        return template.query(sql, rowMapper, sub_project_id);
    }

    public Task findTaskById(int id) {
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
        return template.queryForObject(sql, rowMapper, id);
    }

    public Project updateProject(Project project) {
        final String sql = """
                  UPDATE project
                  SET
                  id = ?,
                  name = ?,
                  projectLeader = ?,
                  WHERE description = ?
                """;
        template.update(sql, project.getId(),
                project.getName(),
                project.getProjectLeader(),
                project.getDescription());
        return project;
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

    public void updateTask(Task task) {
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