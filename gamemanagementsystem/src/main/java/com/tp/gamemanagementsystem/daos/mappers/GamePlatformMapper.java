package com.tp.gamemanagementsystem.daos.mappers;

import com.tp.gamemanagementsystem.models.GamePlatform;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GamePlatformMapper implements RowMapper<GamePlatform> {

    @Override
    public GamePlatform mapRow(ResultSet resultSet, int i) throws SQLException {
        GamePlatform gp = new GamePlatform();
        gp.setPlatformID(resultSet.getInt("platformID"));
        gp.setGameID(resultSet.getInt("gameID"));
        return gp;
    }
}
