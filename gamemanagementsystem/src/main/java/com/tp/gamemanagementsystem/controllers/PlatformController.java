package com.tp.gamemanagementsystem.controllers;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullPlatformException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Platform;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlatformController {
    @Autowired
    GameManagementService service;

    //get the platform by its ID
    @GetMapping("/platforms")
    public List<Platform> getAllPlatforms()
    {
        return service.getAllPlatforms();
    }


    //get the platform by its ID
    @GetMapping("/platform/id")
    public ResponseEntity getPlatformByID(@RequestBody Integer platID)
    {
        Platform plat = null;
        try {
            plat = service.getPlatformByID(platID);
        }
        catch (InvalidIDException | NullIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(plat);
    }

    //get all the games on one platform by the platform's ID
    @GetMapping("/platform/games/id")
    public ResponseEntity getPlatformGamesByID(@RequestBody Integer platID)
    {
        List<Game> allGames = null;
        try {
            allGames = service.getGamesByPlatformID(platID);
        }
        catch (InvalidIDException | NullIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(allGames);
    }

    //get all the games on one platform by the platform's name
    @GetMapping("/platform/games/platname")
    public ResponseEntity getPlatformGamesByID(@RequestBody String name)
    {
        List<Game> allGames = null;
        try {
            allGames = service.getGamesByPlatformName(name);
        }
        catch (NullTitleException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(allGames);
    }

    @PostMapping("/newplatform")
    public Platform addPlatform(String name)
    {
        Platform newPlatform = null;
        try
        {
            newPlatform = service.addPlatform(name);
        }
        catch (NullTitleException e)
        {
            e.getMessage();
        }
        return newPlatform;
    }

    @PutMapping("/edit/platform")
    public String updatePlatform(Integer platID, String name )
    {
        try
        {
            service.updatePlatform(platID,name);
        }
        catch (NullIDException | NullTitleException e)
        {
            e.getMessage();
        }
        return "Platform successfully updated!";
    }

    @DeleteMapping("/delete/platform")
    public String deletePlatform(Integer platID)
    {
        try
        {
            service.deletePlatform(platID);
        }
        catch (NullIDException e)
        {
            return e.getMessage();
        }
        return "Platform successfully deleted!";
    }

}
