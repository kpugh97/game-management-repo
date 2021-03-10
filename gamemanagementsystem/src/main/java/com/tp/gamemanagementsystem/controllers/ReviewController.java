package com.tp.gamemanagementsystem.controllers;

import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Review;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requests.UpdateReviewRequest;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:4200")
public class ReviewController {

    @Autowired
    GameManagementService service;

    //return all reviews by most recent
    @GetMapping("/reviews")
    public ResponseEntity getAllReviews()
    {

        List<Review> allReviews = service.getAllReviews();
        return ResponseEntity.ok(allReviews);
    }


    @GetMapping("/review/id/{reviewID}")
    public ResponseEntity getReviewByID(@PathVariable Integer reviewID)
    {
        Review toReturn = null;
        try {
            toReturn = service.getReviewByID(reviewID);
        }catch(NullIDException| InvalidIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    //reviews by game IDe
    @PostMapping("/add/review")
    public ResponseEntity makeReview(@RequestBody Review review)
    {
        Review newReview = null;
        try
        {
            newReview = service.makeReview(review.getReviewTitle(), review.getReviewText(),review.getRating(), review.getGameID());
        }
        catch(NullIDException | InvalidIDException | NullTitleException | NullReviewException | InvalidRatingException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(newReview);
    }

    //reviews by game ID
    @GetMapping("/review/gamereview/{gameID}")
    public ResponseEntity getReviewsByGameID(@PathVariable Integer gameID)
    {
        List<Review> allReviews = null;
        try
        {
            allReviews = service.getReviewsByGameID(gameID);
        }
        catch(NullIDException | InvalidIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(allReviews);
    }

    //reviews by game name
    @GetMapping("/review/gamename/{title}")
    public ResponseEntity getReviewsByGame(@PathVariable String title)
    {
        List<Review> allReviews = null;
        try
        {
            allReviews = service.getReviewsByGameName(title);
        }
        catch(NullTitleException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(allReviews);
    }

    //edit a game review by the review ID
    @PutMapping("/edit/review")
    public ResponseEntity editReview(@RequestBody UpdateReviewRequest request)
    {
        List<Review> allReviews = null;
        try
        {
            service.editReview(request.getReviewID(), request.getReview(), request.getRating());
        }
        catch(NullIDException | NullReviewException | InvalidIDException | InvalidRatingException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Review successfully edited!");
    }

    //delete a game review by the review ID
    @DeleteMapping("/delete/review/{reviewID}")
    public ResponseEntity deleteReview(@PathVariable Integer reviewID)
    {
        try
        {
            service.deleteReview(reviewID);
        }
        catch(NullIDException | InvalidIDException e)
        {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Review successfully deleted!");
    }

}
