package edu.school21.sockets.repositories;


import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getLong("id"), rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}