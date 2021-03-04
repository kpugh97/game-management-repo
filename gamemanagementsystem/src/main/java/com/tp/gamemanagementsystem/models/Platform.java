package com.tp.gamemanagementsystem.models;

import java.util.List;

public class Platform {
    private Integer platformID;
    private String name;

    public Platform()
    {

    }

    public Platform(Platform that)
    {
        this.platformID = that.platformID;
        this.name = that.name;
    }

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


}
