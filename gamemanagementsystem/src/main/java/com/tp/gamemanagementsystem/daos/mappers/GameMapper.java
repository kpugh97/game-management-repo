package com.tp.gamemanagementsystem.daos.mappers;

import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Platform;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game gameMap = new Game();
        gameMap.setGameID(resultSet.getInt("gameID"));
        gameMap.setTitle(resultSet.getString("title"));
        gameMap.setCategory(resultSet.getString("category"));
        gameMap.setReleaseYear(resultSet.getInt("year"));

        return gameMap;
    }


}
