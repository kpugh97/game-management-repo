package com.tp.gamemanagementsystem.models;

import java.util.List;

public class Game {

    private Integer gameID;
    private String title;
    private Integer releaseYear;
    private String category;


    //empty default constructor for testing
    public Game()
    {

    }

    //creating a new game or editing a game
    public Game(Integer gameID, String title, Integer releaseYear, String category)
    {
        this.gameID = gameID;
        this.title = title;
        this.releaseYear = releaseYear;
        this.category = category;


    }

    //updating a new game
    public Game(Game that)
    {
        this.gameID = that.gameID;
        this.title = that.title;
        this.releaseYear = that.releaseYear;
        this.category = that.category;
    }


    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
