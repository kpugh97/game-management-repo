package com.tp.gamemanagementsystem.controllers;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Review;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requests.UpdateReviewRequest;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    GameManagementService service;

    //return all reviews by most recent
    @GetMapping("/reviews")
    public List<Review> getAllReviews()
    {

        List<Review> allReviews = service.getAllReviews();
        return allReviews;
    }

    //reviews by game ID
    @PostMapping("add/review")
    public ResponseEntity makeReview(@RequestBody Review review)
    {
        Review newReview = null;
        try
        {
            newReview = service.makeReview(review.getReviewTitle(), review.getReviewText(),review.getRating(), review.getGameID());
        }
        catch(NullIDException | InvalidIDException | NullTitleException | NullReviewException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(newReview);
    }

    //reviews by game ID
    @GetMapping("/review/game/ID")
    public List<Review> getReviewsByGameID(@RequestBody Integer gameID)
    {
        List<Review> allReviews = null;
        try
        {
            allReviews = service.getReviewsByGameID(gameID);
        }
        catch(NullIDException | InvalidIDException e)
        {
            e.printStackTrace();
        }
        return allReviews;
    }

    //reviews by game name
    @GetMapping("/review/game")
    public List<Review> getReviewsByGame(@RequestBody String title)
    {
        List<Review> allReviews = null;
        try
        {
            allReviews = service.getReviewsByGameName(title);
        }
        catch(NullTitleException e)
        {
            e.printStackTrace();
        }
        return allReviews;
    }

    //edit a game review by the review ID
    @PutMapping("/edit/review")
    public String editReview(@RequestBody UpdateReviewRequest request)
    {
        List<Review> allReviews = null;
        try
        {
            service.editReview(request.getReviewID(), request.getReview(), request.getRating());
        }
        catch(NullIDException | NullReviewException e)
        {
            e.getMessage();
        }
        return "Review successfully edited!";
    }

    //delete a game review by the review ID
    @DeleteMapping("/delete/review")
    public String deleteReview(@RequestBody Integer reviewID)
    {
        try
        {
            service.deleteReview(reviewID);
        }
        catch(NullIDException e)
        {
            e.getMessage();
        }
        return "Review successfully deleted!";
    }

}
