package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Platform;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("serviceTest")
public class PlatformInMemDAO implements PlatformDAO{

    @Override
    public List<Platform> getAllPlatforms() {
        return null;
    }

    @Override
    public Platform getPlatformByID(Integer platID) throws NullIDException, InvalidIDException {
        return null;
    }

    @Override
    public List<Game> getGamesByPlatformID(Integer platID) throws NullIDException, InvalidIDException {
        return null;
    }

    @Override
    public List<Game> getGamesByPlatformName(String name) throws NullTitleException {
        return null;
    }

    @Override
    public Platform addPlatform(String name) throws NullTitleException{
        return null;
    }

    @Override
    public void deletePlatform(Integer platID) throws NullIDException {

    }

    @Override
    public void updatePlatformName(Integer platID, String name) throws NullTitleException, NullIDException{

    }
}
