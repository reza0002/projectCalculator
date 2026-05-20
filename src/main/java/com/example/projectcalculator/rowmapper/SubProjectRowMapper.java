package com.example.projectcalculator.rowmapper;

import com.example.projectcalculator.model.SubProject;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubProjectRowMapper implements RowMapper<SubProject> {
    @Override
    public SubProject mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        SubProject subProject = new SubProject();

        subProject.setId(rs.getInt("id"));
        subProject.setName(rs.getString("name"));
        subProject.setDescription(rs.getString("description"));
        subProject.setPrice_per_hour(rs.getInt("price_per_hour"));
        subProject.setHours(rs.getInt("hours"));
        subProject.setProject_id(rs.getInt("project_id"));
        subProject.setDone(rs.getBoolean("is_done"));
        return subProject;
    }
}