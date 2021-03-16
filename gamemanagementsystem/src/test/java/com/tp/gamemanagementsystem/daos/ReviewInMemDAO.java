package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Review;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("serviceTest")
public class ReviewInMemDAO implements ReviewDAO {

    List<Review> reviewList = new ArrayList<>();
    List<Game> allGames = new ArrayList<>();

    @Override
    public List<Review> getAllReviews()
    {
        List<Review> copyReviews = new ArrayList<>();
        for(Review toCopy: reviewList)
        {
            copyReviews.add(toCopy);
        }
        return copyReviews;
    }

    @Override
    public Review getReviewByID(Integer reviewID) throws NullIDException, InvalidIDException {
        if(reviewID == null)
        {
            throw new NullIDException("Cannot retrieve a review with null ID!");
        }
        Review toReturn = null;
        return toReturn;
    }

    @Override
    public Review makeReview(String reviewTitle, String review, Integer rating, Integer gameID,Integer userID) throws NullTitleException, NullReviewException, NullIDException, InvalidIDException {
        if(reviewTitle == null)
        {
            throw new NullTitleException("Cannot make a review with a null title!");
        }
        if(review == null)
        {
            throw new NullReviewException("Cannot make a null review!");

        }
        if(gameID == null)
        {
            throw new NullIDException("Cannot make a review for a game with a null game ID!");
        }
        int id = 0;
        for(Review toCheck : reviewList)
        {
            if(toCheck.getGameID()>id)
            {
                id = toCheck.getGameID();
            }
        }
        id++;
        Review newReview = new Review(id, reviewTitle, review, rating);
        reviewList.add(newReview);
        return new Review(newReview);
    }

    @Override
    public void deleteReview(Integer reviewID) throws NullIDException, InvalidIDException {
        if(reviewID == null)
        {
            throw new NullIDException("Cannot delete a book with a null ID!");
        }
        int removeIndex = -1;
        for(int i =0; i< reviewList.size();i++)
        {
            if(reviewList.get(i).getReviewID().equals(reviewID))
            {
                removeIndex = i;
                break;
            }
        }
        if(removeIndex!=-1)
        {
            reviewList.remove(removeIndex);
        }
        else
        {
            throw new InvalidIDException("Cannot find book with ID "+ reviewID +" to delete!");
        }

    }

    @Override
    public void editReview(Integer reviewID, String review, Integer rating) throws NullIDException, NullReviewException, InvalidIDException {
        if(reviewID==null)
        {
            throw new NullIDException("Cannot edit a review with a null ID!");
        }
        if(review==null)
        {
            throw new NullReviewException("There must be some text in the review!");
        }
        Review toEdit = null;
        for(int i =0; i< reviewList.size(); i++)
        {
            if(reviewList.get(i).getReviewID()==reviewID)
            {
                toEdit.setReviewID(reviewID);
                toEdit.setReviewText(review);
                if(rating!=null) {
                    toEdit.setRating(rating);
                }
                break;
            }
        }
        if(toEdit == null)
        {
            throw new InvalidIDException("Cannot find a review with ID "+reviewID+"!");
        }

    }

    @Override
    public List<Review> getReviewsByGameName(String title) throws NullTitleException {
        if(title == null)
        {
            throw new NullTitleException("Cannot retrieve a review for a game with a null title!");
        }
        List<Review> toReturn = new ArrayList<>();
        for(int i =0; i< reviewList.size();i++)
        {
            if(reviewList.get(i).getGameTitle().equals(title))
            {
                toReturn.add(reviewList.get(i));
            }
        }
        if(toReturn.isEmpty())
        {
            throw new NullTitleException("Cannot retrieve a review for a game with title "+title+"!");
        }
        return toReturn;
    }


    @Override
    public List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID == null)
        {
            throw new NullIDException("Cannot retrieve a review for a game with a null ID!");
        }
        List<Review> toReturn = new ArrayList<>();
        for(int i =0; i< reviewList.size();i++)
        {
            if(reviewList.get(i).getGameID().equals(gameID))
            {
                toReturn.add(reviewList.get(i));
            }
        }
        if(toReturn.isEmpty())
        {
            throw new InvalidIDException("Cannot retrieve a review for a game with ID "+gameID+"!");
        }
        return toReturn;
    }


}
