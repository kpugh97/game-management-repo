package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Review;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"mainApp","DAOTesting","serviceTest"})
public interface ReviewDAO {

    //create a new review to type up
    List<Review> getAllReviews();
    Review makeReview(String reviewTitle, String review, Integer rating, Integer gameID, Integer userID) throws NullTitleException, NullReviewException, NullIDException, InvalidIDException;
    //return all the reviews for a game
    void deleteReview(Integer reviewID) throws NullIDException, InvalidIDException;
    void editReview(Integer reviewID, String review, Integer rating) throws NullIDException,NullReviewException, InvalidIDException;
    List<Review> getReviewsByGameName(String title) throws NullTitleException;
    List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException;
    Review getReviewByID(Integer reviewID) throws NullIDException, InvalidIDException;
}
