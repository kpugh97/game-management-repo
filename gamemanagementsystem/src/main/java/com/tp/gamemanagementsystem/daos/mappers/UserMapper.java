package com.tp.gamemanagementsystem.daos.mappers;

import com.tp.gamemanagementsystem.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getInt("userID"));
        user.setUserName(resultSet.getString("userName"));
        return user;
    }
}
