package com.tp.gamemanagementsystem.services;

import com.tp.gamemanagementsystem.daos.GameDAO;
import com.tp.gamemanagementsystem.daos.PlatformDAO;
import com.tp.gamemanagementsystem.daos.ReviewDAO;
import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.GamePlatform;
import com.tp.gamemanagementsystem.models.Platform;
import com.tp.gamemanagementsystem.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class GameManagementService {
    @Autowired
    GameDAO dao;

    @Autowired
    PlatformDAO platdao;

    @Autowired
    ReviewDAO reviewdao;

    public List<Game> getGameCollection() {
        return dao.getGameCollection();
    }

    public Game getGameByID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID<0) {
            throw new InvalidIDException("Invalid ID input!");
        }
        return  dao.getGameByID(gameID);
    }

    public Game createGame(Integer gameID, String title, String category, Integer year, List<Integer> platforms) throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException {
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
        if(year < 0 || year > currYear) {
            throw new NullYearException("Invalid year input!");
        }
        
        return dao.createGame(title, category, year, platforms);
    }

    public List<Game> getGameByYear(Integer year) throws NullYearException {
        //current year
        Year y = Year.now();
        //set that year to an int
        int currYear = y.getValue();
        if(year < 0 || year > currYear) {
            throw new NullYearException("Invalid year input!");
        }
        return dao.getGameByYear(year);

    }

    public List<Game> getGameByCategory(String category) throws NullCategoryException {
        if(category.trim().length() <= 0)
        {
            throw new NullCategoryException("Cannot search a null category!");
        }
        return dao.getGameByCategory(category);
    }

    public Platform getPlatformByID(Integer platID) throws NullIDException, InvalidIDException {
        if(platID < 0)
        {
            throw new InvalidIDException("Cannot search an ID with a negative value");

        }
        return platdao.getPlatformByID(platID);

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

    public void deleteGame(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID<0)
        {
            throw new InvalidIDException("Invalid ID Input!");
        }
        dao.deleteGame(gameID);
    }

    public void editGame(Integer gameID, String title, String category, Integer year) throws NullIDException, InvalidIDException, NullTitleException, NullYearException, NullCategoryException {
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
        dao.editGame(gameID, title, category, year);
    }

    public List<Platform> getAllPlatforms() {
        return platdao.getAllPlatforms();
    }

    public void deletePlatform(Integer platID) throws NullIDException {
        platdao.deletePlatform(platID);
    }

    public Platform addPlatform(String name) throws NullTitleException {
        return platdao.addPlatform(name);
    }

    public void updatePlatform(Integer platID, String name) throws NullTitleException, NullIDException {
        platdao.updatePlatformName(platID, name);
    }


    public List<Review> getReviewsByGameName(String title) throws NullTitleException {
        return reviewdao.getReviewsByGameName(title);
    }

    public List<Review> getReviewsByGameID(Integer gameID) throws NullIDException, InvalidIDException {
        return reviewdao.getReviewsByGameID(gameID);
    }

    public List<Review> getAllReviews() {
        return reviewdao.getAllReviews();
    }

    public void deleteReview(Integer reviewID) throws NullIDException {
        reviewdao.deleteReview(reviewID);
    }

    public void editReview(Integer reviewID, String review, Integer rating) throws NullIDException, NullReviewException {
        reviewdao.editReview(reviewID, review, rating);
    }

    public Review makeReview(String title, String reviewText, Integer rating, Integer gameID) throws NullIDException, NullTitleException, NullReviewException, InvalidIDException {
        return reviewdao.makeReview(title, reviewText, rating, gameID);
    }
}


