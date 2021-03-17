package com.tp.gamemanagementsystem.daos.mappers;

import com.tp.gamemanagementsystem.models.UserList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserListMapper implements RowMapper<UserList> {

    @Override
    public UserList mapRow(ResultSet resultSet, int i) throws SQLException {
        UserList list = new UserList();
        list.setUserID(resultSet.getInt("userID"));
        list.setUserName(resultSet.getString("userName"));
        list.setGameID(resultSet.getInt("gameID"));
        list.setGameName(resultSet.getString("title"));
        list.setStatusID(resultSet.getInt("statusID"));
        return list;
    }
}
