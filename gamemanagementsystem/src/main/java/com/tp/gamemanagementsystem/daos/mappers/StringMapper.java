package com.tp.gamemanagementsystem.daos.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringMapper implements RowMapper<String>{
    String colName;
    public StringMapper(String colName)
    {
        this.colName = colName;
    }

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(colName);
    }
}
