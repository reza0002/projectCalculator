package com.example.projectcalculator.rowmapper;

import com.example.projectcalculator.model.Project;
import com.example.projectcalculator.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        User projectLeader = new User();
        projectLeader.setId(rs.getInt("project_leader"));

        Project project = new Project();
        project.setId(rs.getInt("id"));
        project.setName(rs.getString("name"));
        project.setProjectLeader(projectLeader);
        project.setDescription(rs.getString("description"));
        return project;
    }
}