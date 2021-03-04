package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.GamePlatform;
import com.tp.gamemanagementsystem.models.Platform;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("serviceTest")
public class PlatformInMemDAO implements PlatformDAO{

    List<Platform> allPlatforms = new ArrayList<>();
    List<Game> allGames = new ArrayList<>();
    List<GamePlatform> gpList = new ArrayList<>();

    @Override
    public List<Platform> getAllPlatforms() {
        List<Platform> copyList = new ArrayList<>();
        for(Platform toCopy : allPlatforms)
        {
            copyList.add(new Platform(toCopy));
        }
        return copyList;
    }

    @Override
    public Platform addPlatform(String name) throws NullTitleException{
        if(name == null)
        {

        }
        int id = 0;
        for(Platform toCheck : allPlatforms) {
            if (toCheck.getPlatformID() > id) {
                id = toCheck.getPlatformID();
            }
        }
        id++;
        Platform toCopy = new Platform();
        toCopy.setName(name);
        toCopy.setPlatformID(id);
        allPlatforms.add(toCopy);
        return new Platform(toCopy);
    }
    @Override
    public Platform getPlatformByID(Integer platID) throws NullIDException, InvalidIDException {
        if(platID == null)
        {
            throw new NullIDException("Cannot retrieve a platform with a null ID!");
        }

        Platform toGet = null;
        for(int i =0; i<allPlatforms.size();i++)
        {
            if(allPlatforms.get(i).getPlatformID().equals(platID))
            {
                toGet = allPlatforms.get(i);
            }
        }
        if(toGet == null)
        {
            throw new InvalidIDException("Cannot find a platform with ID "+platID+"!");
        }
        return toGet;
    }

    @Override
    public void deletePlatform(Integer platID) throws NullIDException, InvalidIDException {
        if(platID == null)
        {
            throw new NullIDException("Cannot remove a platform with a null ID!");
        }
        int removeIndex = -1;
        for(int i = 0; i<allPlatforms.size();i++)
        {
            if(allPlatforms.get(i).getPlatformID().equals(platID))
            {
                removeIndex = i;
                break;
            }
        }
        if(removeIndex != -1)
        {
            allPlatforms.remove(removeIndex);
        }
        else
        {
            throw new InvalidIDException("Could not find a platform with ID "+platID+"!");
        }

    }

    @Override
    public void updatePlatformName(Integer platID, String name) throws NullTitleException, NullIDException, InvalidIDException{
        if(platID == null)
        {
            throw new NullIDException("Cannot edit a platform with a null ID!");
        }
        if(name == null)
        {
            throw new NullTitleException("Cannot edit a platform with a null name!");
        }
        Platform toEdit = null;
        for(int i = 0; i<allPlatforms.size();i++)
        {
            if(allPlatforms.get(i).getPlatformID().equals(platID))
            {
                toEdit.setName(name);
                allPlatforms.set(i, toEdit);
                break;
            }
        }
        if(toEdit == null)
        {
            throw new InvalidIDException("Could not find a platform with ID "+platID+"!");
        }

    }


    @Override
    public List<Game> getGamesByPlatformID(Integer platID) throws NullIDException, InvalidIDException {
        if(platID == null)
        {
            throw new NullIDException("Cannot retrieve a platform with a null ID!");
        }
        List<Game> toReturn = new ArrayList<>();
        for (int i = 0; i < allPlatforms.size(); i++) {
            if(allPlatforms.get(i).getPlatformID().equals(platID) && gpList.get(i).getPlatform().getPlatformID().equals(platID))
            {
                toReturn.add(gpList.get(i).getGame());
            }

        }
        if(toReturn.isEmpty())
        {
            throw new InvalidIDException("Cannot find any games on a platform with ID "+platID+"!");
        }
        return toReturn;

    }

    @Override
    public List<Game> getGamesByPlatformName(String name) throws NullTitleException {
        if(name == null)
        {
            throw new NullTitleException("Cannot retrieve a platform with a null name!");
        }
        List<Game> toReturn = new ArrayList<>();
        for (int i = 0; i < allPlatforms.size(); i++) {
            if(allPlatforms.get(i).getName().equals(name) && gpList.get(i).getPlatform().getName().equals(name))
            {
                toReturn.add(gpList.get(i).getGame());
            }

        }
        if(toReturn.isEmpty())
        {
            throw new NullTitleException("Cannot find any games on a platform with name "+name+"!");
        }
        return toReturn;
    }
}
