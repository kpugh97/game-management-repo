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

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:4200")
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
    @GetMapping("/platform/id/{platID}")
    public ResponseEntity getPlatformByID(@PathVariable Integer platID)
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
    @GetMapping("/platform/games/{platID}")
    public ResponseEntity getPlatformGamesByID(@PathVariable Integer platID)
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
    @GetMapping("/platform/platname/{name}")
    public ResponseEntity getGamesByPlatformName(@PathVariable String name)
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
    public ResponseEntity addPlatform(@RequestBody String name)
    {
        Platform newPlatform = null;
        try
        {
            newPlatform = service.addPlatform(name);
        }
        catch (NullTitleException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(newPlatform);
    }

    @PutMapping("/edit/platform")
    public ResponseEntity updatePlatform(@RequestBody Platform plat)
    {
        try
        {
            service.updatePlatform(plat.getPlatformID(),plat.getName());
        }
        catch (NullIDException | NullTitleException | InvalidIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( e.getMessage());

        }
        return ResponseEntity.ok("Platform successfully updated!");
    }

    @DeleteMapping("/delete/platform/{platID}")
    public ResponseEntity deletePlatform(@PathVariable Integer platID)
    {
        try
        {
            service.deletePlatform(platID);
        }
        catch (NullIDException | InvalidIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( e.getMessage());
        }
        return ResponseEntity.ok("Platform successfully deleted!");
    }

}
