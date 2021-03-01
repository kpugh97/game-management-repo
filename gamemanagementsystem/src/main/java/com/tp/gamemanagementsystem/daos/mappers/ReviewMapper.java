package com.tp.gamemanagementsystem.daos.mappers;

import com.tp.gamemanagementsystem.models.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review reviewMap = new Review();
        reviewMap.setReviewID(resultSet.getInt("reviewID"));
        reviewMap.setReviewTitle(resultSet.getString("reviewTitle"));
        reviewMap.setReviewText(resultSet.getString("reviewText"));
        reviewMap.setRating(resultSet.getInt("rating"));
        reviewMap.setGameID(resultSet.getInt("gameID"));
        reviewMap.setGameTitle(resultSet.getString("title"));
        return reviewMap;
    }
}
