package com.tp.gamemanagementsystem.models;

public class Review {

    private Integer reviewID;
    private String reviewTitle;
    private String reviewText;
    private Integer rating;
    private Integer gameID;
    private String title;


    //default constructor for testing
    public Review()
    {

    }

    //create a new review or edit new review
    public Review(Integer reviewID, String reviewTitle, String reviewText, Integer rating)
    {
        this.reviewID = reviewID;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    //copy constructor
    public Review(Review that)
    {
        this.reviewID = that.reviewID;
        this.reviewTitle = that.reviewTitle;
        this.reviewText = that.reviewText;
        this.rating = that.rating;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getGameTitle() {
       return title;
    }

    public void setGameTitle(String title) {
        this.title = title;
    }
}
