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
    public SubProject createSubProject(SubProject subProject){

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

    }

    @Transactional
    public SubProject saveSubProject(SubProject subProject){

    }

    @Transactional
    public Task saveTasks(Task tasks){

    }

    @Transactional
    public boolean deleteProject(Project project) {
        final String sql = """
            DELETE FROM project
            WHERE id = ?
            """;
        return template.update(sql, project.getId());
    }


    @Transactional
    public void deleteSubProject(int id){
        String sql = "DELETE FROM sub_project WHERE id = ?";
        template.update(sql, id);
    }

    @Transactional
    public boolean deleteTasks(Task task){

    }

    public User login(String username, String password){

    }

    @Transactional
    public User findUser(String username){

    }

    public List<Project> getProjects() {
        String sql = """
                    SELECT p.id AS p_id, p.name AS p_name,
                           u.id AS u_id, u.name AS u_name, u.password
                    FROM project p
                    JOIN user u ON p.project_leader = u.id
                """;
        return template.query(sql, new ProjectRowMapper());
    }

    @Transactional
    public Project createProject(Project project) {
        String sql = "INSERT INTO project (name, project_leader, description, id) VALUES (?, ?, ?, ?)";
        template.update(sql, project.getName(), project.getDescription(), project.getProjectLeader().getId());
        return project;
    }

    private List<Project> findProjects(Project project) {

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