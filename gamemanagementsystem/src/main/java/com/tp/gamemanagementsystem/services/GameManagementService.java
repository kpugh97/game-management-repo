package com.tp.gamemanagementsystem.services;

import com.tp.gamemanagementsystem.daos.GameDAO;
import com.tp.gamemanagementsystem.daos.PlatformDAO;
import com.tp.gamemanagementsystem.daos.ReviewDAO;
import com.tp.gamemanagementsystem.daos.UserDAO;
import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Platform;
import com.tp.gamemanagementsystem.models.Review;
import com.tp.gamemanagementsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
@Profile({"mainApp","DAOTesting","serviceTest"})
public class GameManagementService {
    @Autowired
    GameDAO dao;

    @Autowired
    PlatformDAO platdao;

    @Autowired
    ReviewDAO reviewdao;

    @Autowired
    UserDAO userdao;


    //VIDEO GAME DAOS:

    public List<Game> getGameCollection() {
        return dao.getGameCollection();
    }

    public Game getGameByID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID<0) {
            throw new InvalidIDException("Invalid ID input!");
        }
        return  dao.getGameByID(gameID);
    }

    public List<String> getAllGenres()
    {
       return dao.getAllGenres();
    }

    public Game createGame(String title, String category, Integer year, List<Integer> platforms, String desc) throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException, NullDescriptionException {
        //current year
        Year y = Year.now();
        //set that year to an int
        int currYear = y.getValue();
        if(title.trim().length() <= 0)
        {
            throw new NullTitleException("Invalid title input!");

        }
        if(category.trim().length() <= 0)
        {
            throw new NullCategoryException("Invalid category input!");
        }
        if(desc.trim().length() <= 0)
        {
            throw new NullDescriptionException("Invalid description!");
        }
        if(year < 0 || year > currYear) {
            throw new NullYearException("Invalid year input!");
        }

        return dao.createGame(title, category, year, platforms, desc);
    }

    public List<Game> getGamesByYear(Integer year) throws NullYearException {
        //current year
        Year y = Year.now();
        //set that year to an int
        int currYear = y.getValue();
        if(year < 0 || year > currYear) {
            throw new NullYearException("Invalid year input!");
        }
        return dao.getGamesByYear(year);

    }

    public List<Game> getGameByCategory(String category) throws NullCategoryException {
        if(category.trim().length() <= 0)
        {
            throw new NullCategoryException("Cannot search a null category!");
        }
        return dao.getGameByCategory(category);
    }

    public List<Game> getGamesByTitle(String title) throws NullTitleException{
        if(title.trim().length() <= 0)
        {
            throw new NullTitleException("Cannot search a null category!");
        }
        return dao.getGamesByTitle(title);
    }


    public void deleteGame(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID<0)
        {
            throw new InvalidIDException("Invalid ID Input!");
        }
        dao.deleteGame(gameID);
    }

    public void editGame(Integer gameID, String title, String category, Integer year, String desc) throws NullIDException, InvalidIDException, NullTitleException, NullYearException, NullCategoryException, NullDescriptionException {
        if(gameID<0)
        {
            throw new InvalidIDException("Invalid ID input");
        }
        if(title.trim().length() <= 0)
        {
            throw new NullTitleException("Invalid title input!");

        }
        if(category.trim().length() <= 0)
        {
            throw new NullCategoryException("Invalid category input!");
        }
        if(desc.trim().length() <= 0)
        {
            throw new NullCategoryException("Invalid category input!");
        }
        dao.editGame(gameID, title, category, year, desc);
    }

    public void updateGameStatus(Integer gameID, Integer statusID) throws NullIDException, InvalidIDException
    {
        if(gameID < 0 || (statusID < 0 || statusID > 4))
        {
            throw new InvalidIDException("Invalid status ID!");
        }
        dao.updateGameStatus(gameID,statusID);
    }

    public List<Game> getGamesByStatus(Integer statusID) throws NullIDException, InvalidIDException
    {
        if(statusID < 0 || statusID > 4)
        {
            throw new InvalidIDException("Invalid status ID!");
        }
        return dao.getGamesByStatus(statusID);
    }

    //PLATFORM DAOS

    public List<Platform> getAllPlatforms() {
        return platdao.getAllPlatforms();
    }

    public Platform getPlatformByID(Integer platID) throws NullIDException, InvalidIDException {
        if(platID < 0)
        {
            throw new InvalidIDException("Cannot search an ID with a negative value");

        }
        return platdao.getPlatformByID(platID);

    }

    public void deletePlatform(Integer platID) throws NullIDException, InvalidIDException {
        if(platID < 0)
        {
            throw new InvalidIDException("Invalid ID input!");
        }
        platdao.deletePlatform(platID);
    }

    public Platform addPlatform(String name) throws NullTitleException {
        return platdao.addPlatform(name);
    }

    public void updatePlatform(Integer platID, String name) throws NullTitleException, NullIDException, InvalidIDException{
        if(platID<0)
        {
            throw new InvalidIDException("Invalid ID input!");
        }
        if(name.trim().length()<=0)
        {
            throw new NullTitleException("Invalid name for platform!");
        }
        platdao.updatePlatformName(platID, name);
    }

    public List<Game> getGamesByPlatformID(Integer platID) throws NullIDException, InvalidIDException {
        if(platID < 0)
        {
            throw new InvalidIDException("Cannot search an ID with a negative value");

        }
        return platdao.getGamesByPlatformID(platID);

    }

    public List<Game> getGamesByPlatformName(String name) throws NullTitleException {
        if (name.trim().length() <= 0) {
            throw new NullTitleException("There must be a name to search!");

        }
        return platdao.getGamesByPlatformName(name);

    }


    //REVIEW DAOS

    public List<Review> getAllReviews() {
        return reviewdao.getAllReviews();
    }

    public Review getReviewByID(Integer reviewID) throws NullIDException, InvalidIDException{
        return reviewdao.getReviewByID(reviewID);
    }

    public List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID < 0)
        {
            throw new InvalidIDException("Invalid ID input!");
        }
        return reviewdao.getReviewsByGameID(gameID);
    }

    public List<Review> getReviewsByGameName(String title) throws NullTitleException {
        if(title.trim().length() <= 0)
        {
            throw new NullTitleException("Invalid title input!");
        }
        return reviewdao.getReviewsByGameName(title);
    }

    public void deleteReview(Integer reviewID) throws NullIDException, InvalidIDException{
        if(reviewID<0)
        {
            throw new InvalidIDException("Invalid ID input!");
        }
        reviewdao.deleteReview(reviewID);
    }

    public void editReview(Integer reviewID, String review, Integer rating) throws NullIDException, NullReviewException, InvalidIDException, InvalidRatingException {
        if(reviewID < 0)
        {
            throw new InvalidIDException("Invalid ID input!");
        }
        if(review.trim().length() <= 0)
        {
            throw new NullReviewException("Review must have input other than just whitespace!");
        }
        if(rating < 0 || rating > 10)
        {
            throw new InvalidRatingException("Invalid rating input!");
        }
        reviewdao.editReview(reviewID, review, rating);
    }

    public Review makeReview(String title, String reviewText, Integer rating, Integer gameID, Integer userID) throws NullIDException, NullTitleException, NullReviewException, InvalidIDException,InvalidRatingException {
        if(title.trim().length()<=0)
        {
            throw new NullTitleException("Invalid title input!");
        }
        if(reviewText.trim().length()<=0)
        {
            throw new NullReviewException("Cannot make an empty review!");
        }
        if(rating<0||rating>10)
        {
            throw new InvalidRatingException("Invalid rating input!");

        }
        return reviewdao.makeReview(title, reviewText, rating, gameID, userID);
    }

    public void saveImageToDB(String gameName, String url) throws NullURLException, NullTitleException {
        dao.saveImageToDB(gameName,url);
    }


    //USER DAOS

    public User createUser(String userName)throws InvalidIDException , InvalidUsernameException{
        if(userName.trim().length()<=0 || userName.length()<3)
        {
            throw new InvalidUsernameException("Invalid username input! Must be 3 characters or more!");
        }
        return userdao.createUser(userName);
    }

    public User getUserByID (Integer userID) throws InvalidIDException, NullIDException{
        if(userID  <0 )
        {
            throw new InvalidIDException("Invalid ID input!");
        }

       return userdao.getUserByID(userID);
    }


    public User getUserByName (String userName) throws InvalidUsernameException{
        if(userName.trim().length() <= 0 || userName.length() < 3 )
        {
            throw new InvalidUsernameException("Invalid username input! Must be 3 characters or more!");
        }

        return userdao.getUserByName(userName);
    }


    public void deleteUser(Integer userID) throws InvalidIDException, NullIDException{
        if(userID <0)
        {
            throw new InvalidIDException("Invalid ID input!");
        }

        userdao.deleteUser(userID);
    }


    public List<User> getAllUsers() {
        return userdao.getAllUsers();
    }
}


