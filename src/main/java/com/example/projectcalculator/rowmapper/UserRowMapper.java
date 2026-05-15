package com.example.projectcalculator.rowmapper;

import com.example.projectcalculator.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.jspecify.annotations.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}