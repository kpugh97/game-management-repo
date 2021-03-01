package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Review;
import org.springframework.context.annotation.Profile;

import java.util.List;

public interface ReviewDAO {

    //create a new review to type up
    List<Review> getAllReviews();
    Review makeReview(String reviewTitle, String review, Integer rating, Integer gameID) throws NullTitleException, NullReviewException, NullIDException, InvalidIDException;
    //return all the reviews for a game
    void deleteReview(Integer reviewID) throws NullIDException;
    void editReview(Integer reviewID, String review, Integer rating) throws NullIDException,NullReviewException;
    List<Review> getReviewsByGameName(String title) throws NullTitleException;
    List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException;

}
