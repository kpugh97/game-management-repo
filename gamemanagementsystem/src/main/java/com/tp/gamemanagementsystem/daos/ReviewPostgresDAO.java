package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.daos.mappers.IntegerMapper;
import com.tp.gamemanagementsystem.daos.mappers.ReviewMapper;
import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile({"mainApp","DAOTesting"})
public class ReviewPostgresDAO implements ReviewDAO {

    @Autowired
    JdbcTemplate template;

    public Review getReviewByID(Integer reviewID) throws InvalidIDException, NullIDException
    {
        if(reviewID == null)
        {
            throw new NullIDException("Cannot get a review with a null ID!");
        }
        Review someReview = null;
        try
        {
           someReview = template.queryForObject("SELECT * FROM \"Reviews\"\n" +
                    "WHERE \"reviewID\"= ?", new ReviewMapper(),reviewID);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("Cannot find a review with ID "+reviewID+"!");
        }
        return someReview;
    }

    @Override
    public Review makeReview(String reviewTitle, String review, Integer rating, Integer gameID) throws NullTitleException, NullReviewException, NullIDException, InvalidIDException{
        if(gameID == null)
        {
            throw new NullIDException("Cannot make a review for a game with a null ID!");
        }
        if(reviewTitle == null)
        {
            throw new NullTitleException("Review must have a title that is not null!");
        }
        if(review == null)
        {
            throw new NullReviewException("The review cannot be null!");
        }
        Review newReview = new Review();
        try {
            Integer reviewID = template.queryForObject("INSERT INTO \"Reviews\" (\"reviewTitle\",\"rating\",\"gameID\",\"reviewText\") VALUES (?,?,?,?) RETURNING \"reviewID\"", new IntegerMapper("reviewID"),
                    reviewTitle,
                    rating,
                    gameID,
                    review);
            newReview.setReviewID(reviewID);
            newReview.setReviewTitle(reviewTitle);
            newReview.setReviewText(review);
            newReview.setGameID(gameID);
            newReview.setRating(rating);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new InvalidIDException("Cannot make a review for a game with ID "+gameID+"!");
        }
        return newReview;
    }

    @Override
    public List<Review> getAllReviews(){

        List<Review> allReviews = template.query("SELECT gs.\"gameID\",\"title\",\"reviewID\",\"title\",\"category\",\"reviewTitle\",\"reviewText\",\"rating\" FROM \"Games\" as gs\n"+
                "INNER JOIN \"Reviews\" as rs ON rs.\"gameID\"=gs.\"gameID\"\n"+
                "ORDER BY rs.\"reviewID\" DESC",new ReviewMapper());
        return allReviews;
    }

    @Override
    public List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID == null)
        {
            throw new NullIDException("Cannot retrieve a game with a null ID");
        }
        List<Review> allReviews = null;
        try {
            allReviews = template.query("SELECT gs.\"gameID\", \"reviewID\", \"title\",\"category\",\"reviewTitle\",\"reviewText\",\"rating\" FROM \"Games\" as gs\n"+
                    "INNER JOIN \"Reviews\" as rs ON rs.\"gameID\"=gs.\"gameID\"\n"+
                    "WHERE gs.\"gameID\" = ?",new ReviewMapper(),gameID);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("Cannot find a game with ID"+gameID+"!");
        }
        return allReviews;
    }

    @Override
    public List<Review> getReviewsByGameName(String title) throws NullTitleException {
        if(title == null)
        {
            throw new NullTitleException("Cannot retrieve a game with a null ID");
        }
        List<Review> allReviews = null;
        try {
            allReviews = template.query("SELECT gs.\"gameID\", \"reviewID\",\"title\",\"category\",\"reviewTitle\",\"reviewText\",\"rating\" FROM \"Games\" as gs\n"+
                    "INNER JOIN \"Reviews\" as rs ON rs.\"gameID\"=gs.\"gameID\"\n"+
                    "WHERE gs.\"title\" = ?",new ReviewMapper(),title);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new NullTitleException("Cannot find a game with title"+title+"!");
        }
        return allReviews;
    }

    @Override
    public void deleteReview(Integer reviewID) throws NullIDException{
        if(reviewID == null)
        {
            throw new NullIDException("Cannot delete a review with a null ID!");
        }
        template.update("DELETE FROM \"Reviews\" WHERE \"reviewID\"= ?",reviewID);
    }

    @Override
    public void editReview(Integer reviewID, String review, Integer rating)throws NullIDException, NullReviewException {
        if(reviewID==null)
        {
            throw new NullIDException("Cannot edit a review with a null ID!");
        }
        if(review == null)
        {
            throw new NullReviewException("You cannot make an empty review");
        }
        template.update("UPDATE \"Reviews\" SET \"reviewText\" = ? , \"rating\" = ? WHERE \"reviewID\"= ?",review,rating,reviewID);

    }
}
