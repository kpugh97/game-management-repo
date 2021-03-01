package com.tp.gamemanagementsystem.models;

public class GamePlatform {
    private String gameTitle;

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    private String platformName;
    private Integer platformID;
    private Integer gameID;

    public Integer getPlatformID() {
        return platformID;
    }

    public void setPlatformID(Integer platformID) {
        this.platformID = platformID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }



}
