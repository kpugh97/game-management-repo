package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.GamePlatform;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"mainApp","DAOTesting"})
public interface GameDAO {
    Game createGame(String title, String category, Integer year, List<Integer> platforms) throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException;
    Game getGameByID(Integer gameID) throws NullIDException, InvalidIDException;
    List<Game> getGameCollection();
    List<Game> getGameByCategory(String category) throws NullCategoryException;
    List<GamePlatform> getGameByTitle(String title) throws NullTitleException;
    List<Game> getGameByYear(Integer year) throws NullYearException;
    void editGame(Integer gameID, String title, String category,  Integer releaseDate) throws NullIDException, InvalidIDException, NullTitleException, NullYearException, NullCategoryException;
    void deleteGame(Integer gameID) throws NullIDException, InvalidIDException;
    void updateGamePlatform(Integer gameID, Integer platformToChange, List<Integer>platforms) throws NullIDException, NullPlatformException, InvalidIDException;

}
