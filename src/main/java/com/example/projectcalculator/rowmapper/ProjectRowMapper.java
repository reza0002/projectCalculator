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
        User leader = new UserRowMapper().mapRow(rs, rowNum);
        Project project = new Project();
        project.setId(rs.getInt("project_id"));
        project.setName(rs.getString("project_name"));
        project.setProjectLeader(leader);
        return project;
    }
}