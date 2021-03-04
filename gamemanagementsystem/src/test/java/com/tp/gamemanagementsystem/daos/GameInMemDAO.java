package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.GamePlatform;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("serviceTest")
public class GameInMemDAO implements GameDAO{

    private List<Game> allGames = new ArrayList<>();


    @Override
    public Game createGame(String title, String category, Integer year, List<Integer> platforms) throws NullTitleException, NullCategoryException, NullYearException, NullPlatformException {
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
        if(platforms == null)
        {
            throw new NullPlatformException("Game must have at least one(1) platoform!");
        }
        int id=0;
        for(Game toCheck: allGames)
        {
            if(toCheck.getGameID()>id)
            {
             id = toCheck.getGameID();
            }
        }
        id++;
        Game toCopy = new Game(id,title,year,category);
        toCopy.setGameID(id);
        allGames.add(toCopy);
        return new Game(toCopy);
    }

    @Override
    public Game getGameByID(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID == null)
        {
            throw new NullIDException("Cannot retrieve a game with a null ID");
        }
        Game toReturn = null;
        for(int i =0; i<allGames.size();i++) {
            {
                if (allGames.get(i).getGameID().equals(gameID))
                {
                    toReturn = new Game(allGames.get(i));
                }
            }
        }
        if(toReturn == null)
        {
            throw new InvalidIDException("Cannot find a game with ID "+gameID+"!");
        }
        return toReturn;

    }

    @Override
    public List<Game> getGameCollection() {
        List<Game> copyCollection = new ArrayList<>();
        for(Game toCopy : allGames)
        {
            copyCollection.add(new Game(toCopy));
        }
        return copyCollection;
    }


    @Override
    public List<Game> getGameByCategory(String category) throws NullCategoryException {
        if (category == null)
        {
            throw new NullCategoryException("Cannot retrieve a game with a null category!");
        }
        List<Game> copyCollection = new ArrayList<>();
        for(Game toCopy : allGames)
        {
            if(toCopy.getCategory().equals(category))
            {
                copyCollection.add(new Game(toCopy));
            }
        }
        if(copyCollection.isEmpty())
        {
            throw new NullCategoryException("Cannot find a game with year "+category+"!");
        }
        return copyCollection;
    }

    @Override
    public List<Game> getGameByYear(Integer year) throws NullYearException {
        if(year == null)
        {
            throw new NullYearException("Cannot retrieve a game with a null year!");
        }
        List<Game> copyCollection = new ArrayList<>();
        for(Game toCopy : allGames)
        {
            if(toCopy.getReleaseYear().equals(year)) {
                copyCollection.add(new Game(toCopy));
            }
        }
        if(copyCollection.isEmpty())
        {
            throw new NullYearException("Cannot find a game with year "+year+"!");
        }

        return copyCollection;
    }

    @Override
    public void editGame(Integer gameID, String title,  String category, Integer releaseDate) throws NullIDException, InvalidIDException, NullCategoryException, NullYearException, NullTitleException{
        if(gameID == null)
        {
            throw new NullIDException("Cannot retrieve a game with a null ID");
        }
        if(title == null)
        {
            throw new NullTitleException("Cannot create a game with a null title!");
        }
        if(category == null)
        {
            throw new NullCategoryException("Cannot create a game with a null category!");
        }
        if(releaseDate == null)
        {
            throw new NullYearException("Cannot create a game with a null year!");
        }
        Game toEdit = null;
        for (int i = 0; i < allGames.size(); i++) {
            if(allGames.get(i).getGameID().equals(gameID))
            {
               toEdit.getGameID();
                toEdit.setTitle(title);
                toEdit.setCategory(category);
                toEdit.setReleaseYear(releaseDate);
                allGames.set(i, toEdit);
                break;
            }

        }
        if(toEdit == null)
        {
            throw new InvalidIDException("Cannot find a book with ID "+gameID+"!");

        }

    }

    @Override
    public void deleteGame(Integer gameID) throws NullIDException, InvalidIDException {
        if(gameID == null)
        {
            throw new NullIDException("Cannot retrieve a game with a null ID");
        }
        int removeIndex = -1;
        for (int i = 0; i < allGames.size(); i++) {
            if(allGames.get(i).getGameID().equals(gameID))
            {
                removeIndex =i;
                break;
            }

        }
        if(removeIndex != -1)
        {
            allGames.remove(removeIndex);
        }
        else{
            throw new InvalidIDException("Cannot find a game with ID "+gameID+"!");
        }


    }

    @Override
    public void saveImageToDB(String gameName, String url) throws NullTitleException, NullURLException {

    }

}
