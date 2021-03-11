package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.daos.mappers.*;
import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@Component
@Profile({"mainApp","DAOTesting","serviceTest"})
public class GamePostgresDAO implements GameDAO {

    @Autowired
    JdbcTemplate template;


    @Override
    public List<Game> getGameCollection() {
        List<Game> allGames = template.query("SELECT \"Games\".\"gameID\", \"title\", \"category\", \"year\", \"imageSrc\", \"desc\",\"statusID\" FROM \"Games\" \n"+
                "ORDER BY \"gameID\" DESC", new GameMapper());
        return allGames;
    }

    @Override
    public Game createGame(String title, String category, Integer year, List<Integer> platforms, String desc) throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException, NullDescriptionException {
        if(title == null)
        {
            throw new NullTitleException("Cannot create a game with a null title!");
        }
        if(category == null)
        {
            throw new NullCategoryException("Cannot create a game with a null category!");
        }
        if(year == null)
        {
            throw new NullYearException("Cannot create a game with a null year!");
        }
        if(platforms.isEmpty())
        {
            throw new NullPlatformException("Cannot create a game with a null platform!");
        }
        if(desc == null)
        {
            throw new NullDescriptionException("Cannot create a game with a null description!");
        }
        Game newGame = new Game();
        Integer gameID = template.queryForObject( "INSERT INTO \"Games\" (\"title\", \"category\", \"year\", \"desc\") VALUES (?, ?, ?, ?) RETURNING \"gameID\"", new IntegerMapper("gameID"),
                title,
                category,
                year,
                desc);
        newGame.setGameID(gameID);
        newGame.setReleaseYear(year);
        newGame.setTitle(title);
        newGame.setCategory(category);
        newGame.setDesc(desc);
            for (int i = 0; i < platforms.size(); i++) {
                try {
                    template.query("INSERT INTO \"GamePlatforms\" (\"platformID\",\"gameID\") VALUES (?, ?) RETURNING \"platformID\",\"gameID\"", new GamePlatformMapper(),
                            platforms.get(i),
                            newGame.getGameID());
                }catch (DataIntegrityViolationException e) {
                    throw new InvalidIDException("Cannot add a game on a platform with ID " + platforms.get(i)+"!");
                }
            }
        return newGame;
    }

    @Override
    public Game getGameByID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID == null)
        {
            throw new NullIDException("Cannot find a game with a null ID");
        }
        Game toReturn = null;
        try {
            toReturn = template.queryForObject("SELECT * FROM \"Games\" WHERE \"gameID\" = \'" + gameID + "\'", new GameMapper());
        }
        catch(EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("Cannot find game with ID "+ gameID+"!", e);
        }
        return toReturn;
    }

    @Override
    public List<Game> getGameByCategory(String category) throws NullCategoryException {
        if(category == null)
        {
            throw new NullCategoryException("Cannot retrieve a game with a null category!");
        }
        List<Game> toReturn = null;
        toReturn = template.query("SELECT * FROM \"Games\" WHERE \"category\" = ?", new GameMapper(),category);
        if(toReturn.isEmpty())
        {
            throw new NullCategoryException("Cannot retrieve a game with the category "+category+"!");
        }
        return toReturn;
    }

    @Override
    public List<Game> getGamesByTitle(String title) throws NullTitleException {
        if(title == null)
        {
            throw new NullTitleException("Cannot retrieve a game with a null title!");
        }
        List<Game> toReturn = null;
        toReturn = template.query("SELECT * FROM \"Games\" WHERE \"title\" LIKE '%"+title+"%'", new GameMapper());
        return toReturn;
    }

    @Override
    public List<Game> getGamesByYear(Integer year) throws NullYearException {
        if(year == null)
        {
            throw new NullYearException("Cannot retrieve a game with a null year!");
        }
        List<Game> toReturn = null;
        toReturn = template.query("SELECT * FROM \"Games\" WHERE \"year\" = \'" + year + "\'", new GameMapper());
        if(toReturn.isEmpty())
        {
            throw new NullYearException("Cannot retrieve a game with year "+year+"!");
        }
        return toReturn;
    }



    @Override
    public void editGame(Integer gameID, String title, String category, Integer releaseDate) throws NullIDException, InvalidIDException, NullTitleException, NullYearException, NullCategoryException {
        if (gameID == null) {
            throw new NullIDException("Cannot edit a game with a null ID!");
        }
        if (title == null) {
            throw new NullTitleException("Cannot edit a game with a null title!");
        }
        if (releaseDate == null) {
            throw new NullYearException("Cannot edit a game with a null release year!");
        }
        if (category == null) {
            throw new NullCategoryException("Cannot edit a game with a null category!");
        }
        //update game with new info
        try {
            template.update("UPDATE \"Games\" SET \"title\" = ? , \"category\" = ?, \"year\" = ? WHERE \"gameID\" = ? ;",title,category,releaseDate,gameID);
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidIDException("Cannot make changes to a game with ID " + gameID + "!");
        }
    }

    //delete a game by the gameID
    @Override
    public void deleteGame(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID == null)
        {
            throw new NullIDException("Cannot delete a game with a null ID");
        }
        template.update("DELETE FROM \"GamePlatforms\" WHERE \"gameID\"="+gameID);
        template.update("DELETE FROM \"Reviews\" WHERE \"gameID\"="+gameID);
        template.update("DELETE FROM \"Games\" WHERE \"gameID\"="+gameID);
    }


    //ADD IMAGE SEARCHED TO DATABASE TO PULL FROM
    @Override
    public void saveImageToDB(String gameName, String url) throws NullTitleException {
        if(gameName==null)
        {
            throw new NullTitleException("Cannot find a game with a null title");
        }
        if(url==null)
        {
            System.out.println("No URL to update.");
            return;
        }
        //check if there is a url in this column already
        String someURL = null;
        try {
            someURL = template.queryForObject("SELECT \"imageSrc\" FROM \"Games\" WHERE \"title\" = ?", new StringMapper("imageSrc"), gameName);
        }
        catch (EmptyResultDataAccessException e) {
            throw new NullTitleException("Cannot find a game with title " + gameName + "!");
        }
        if(someURL!=null)
        {
            //if there is an image already leave this method
            System.out.println("Source already supplied");
            return;
        }
        //if there is no image update this column
        else {
            //if there is a title and url add that url to the game with matching name
            try {
                template.update("UPDATE \"Games\" SET \"imageSrc\" = ? WHERE \"title\" = ?", url, gameName);
            } catch (EmptyResultDataAccessException e) {
                throw new NullTitleException("Cannot find a game with title " + gameName + "!");
            }
        }

    }

    @Override
    public void updateGameStatus(Integer gameID, Integer statusID) throws NullIDException, InvalidIDException {
        if(statusID == null)
        {
            throw new NullIDException("Cannot update a status with a null ID");
        }
        try {
            template.update("UPDATE \"Games\" SET \"statusID\" = ? WHERE \"gameID\" = ?",statusID,gameID);
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidIDException("Cannot change a status with ID " + statusID + "!");
        }

    }

    @Override
    public List<Game> getGamesByStatus(Integer statusID) throws NullIDException, InvalidIDException {
        if(statusID==null)
        {
            throw new NullIDException("Cannot get a game with a null status ID!");
        }
        List<Game> toReturn  = null;
        try
        {
            toReturn = template.query("SELECT * FROM \"Games\" WHERE \"statusID\" = ?", new GameMapper(),statusID);
        }
        catch (DataIntegrityViolationException | EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("Cannot find any games with status ID "+statusID+"!");
        }
        if(toReturn.isEmpty())
        {
           throw new InvalidIDException("Cannot find any games with status ID "+statusID+"!");
        }
        return toReturn;

    }
}
