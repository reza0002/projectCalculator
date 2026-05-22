package com.example.projectcalculator.rowmapper;

import com.example.projectcalculator.model.Task;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setId(rs.getInt("id"));
        task.setName(rs.getString("name"));
        task.setPricePerHour(rs.getInt("price_per_hour"));
        task.setSubProjectId(rs.getInt("sub_project_id"));
        task.setHours(rs.getInt("hours"));
        task.setDone(rs.getBoolean("is_done"));
        task.setAssigneeId(rs.getInt("assigneeId"));

        return task;
    }
}