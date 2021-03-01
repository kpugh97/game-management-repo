package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Review;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("serviceTest")
public class ReviewInMemDAO implements ReviewDAO {

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public Review makeReview(String reviewTitle, String review, Integer rating, Integer gameID) throws NullTitleException, NullReviewException, NullIDException, InvalidIDException {
        return null;
    }

    @Override
    public void deleteReview(Integer reviewID) throws NullIDException {

    }

    @Override
    public void editReview(Integer reviewID, String review, Integer rating) throws NullIDException, NullReviewException {

    }

    @Override
    public List<Review> getReviewsByGameName(String title) throws NullTitleException {
        return null;
    }

    @Override
    public List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException {
        return null;
    }
}
