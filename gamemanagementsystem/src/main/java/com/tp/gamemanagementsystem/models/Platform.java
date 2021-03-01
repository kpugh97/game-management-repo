package com.tp.gamemanagementsystem.models;

import java.util.List;

public class Platform {
    private Integer platformID;
    private String name;

//    List<Game> allGames;

    public Integer getPlatformID() {
        return platformID;
    }

    public void setPlatformID(Integer platformID) {
        this.platformID = platformID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//
//    public List<Game> getAllGames() {
//        return allGames;
//    }
//
//    public void setAllGames(List<Game> allGames) {
//        this.allGames = allGames;
//    }
}
