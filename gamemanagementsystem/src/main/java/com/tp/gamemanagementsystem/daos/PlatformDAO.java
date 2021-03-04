package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullPlatformException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Platform;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"mainApp","DAOTesting","serviceTest"})
public interface PlatformDAO {
    List<Platform> getAllPlatforms();
    Platform getPlatformByID(Integer platID) throws NullIDException, InvalidIDException;
    List<Game> getGamesByPlatformID(Integer platID) throws NullIDException, InvalidIDException;
    List<Game> getGamesByPlatformName(String name) throws NullTitleException;
    Platform addPlatform(String name) throws NullTitleException;
    void deletePlatform(Integer platID) throws NullIDException, InvalidIDException;
    void updatePlatformName(Integer platID, String name) throws NullTitleException, NullIDException, InvalidIDException;
}
