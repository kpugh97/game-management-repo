package com.tp.gamemanagementsystem.daos.mappers;

import com.tp.gamemanagementsystem.models.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerMapper implements RowMapper<Integer> {
    String colName;
    public IntegerMapper(String colName)
    {
        this.colName = colName;
    }
    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(colName);
    }
}
