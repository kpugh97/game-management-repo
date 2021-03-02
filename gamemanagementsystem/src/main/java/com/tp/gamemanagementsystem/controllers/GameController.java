package com.tp.gamemanagementsystem.controllers;

import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.GamePlatform;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requests.CreateGameRequest;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:4200")
public class GameController {
    @Autowired
    GameManagementService service;

    @PostMapping("/newgame")
    public Game createGame(@RequestBody CreateGameRequest request)
    {
        Game toReturn = null;
        try {
            toReturn = service.createGame(request.getGameID(), request.getTitle(), request.getCategory(),request.getReleaseYear(), request.getPlatforms());
        }catch (InvalidIDException | NullTitleException| NullCategoryException| NullYearException| NullPlatformException e)
        {
            e.getMessage();
        }
        return toReturn;
    }

    @GetMapping("/game")
    public List<Game> getGameCollection()
    {
        return service.getGameCollection();
    }

    @GetMapping("/game/id")
    public ResponseEntity getGameByID(@RequestBody Integer gameID)
    {
        Game game =null;
        try {
            game = service.getGameByID(gameID);
        }
        catch (InvalidIDException | NullIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("game/title")
    public ResponseEntity getGameByTitle(@RequestBody String title)
    {
        List<GamePlatform> game =null;
        try {
            game = service.getGameByTitle(title);
        }
        catch (NullTitleException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("game/category")
    public ResponseEntity getGameByCategory(@RequestBody String category)
    {
        List<Game> game =null;
        try {
            game = service.getGameByCategory(category);
        }
        catch (NullCategoryException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(game);
    }


    @GetMapping("/game/year")
    public ResponseEntity getGameByYear(@RequestBody Integer year)
    {
        List<Game> game =null;
        try {
            game = service.getGameByYear(year);
        }
        catch (NullYearException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(game);
    }

    @PutMapping("/edit/game")
    public String editGame(@RequestBody Game editGame)
    {
        try {
            service.editGame(editGame.getGameID(),editGame.getTitle(),editGame.getCategory(),editGame.getReleaseYear());
        }
        catch (InvalidIDException | NullIDException | NullYearException | NullTitleException | NullCategoryException e)
        {
            return e.getMessage();
        }
        return "Game successfully edited!";
    }

    @DeleteMapping("/delete/game")
    public String deleteGame(@RequestBody Integer gameID)
    {
        try {
            service.deleteGame(gameID);
        }
        catch (InvalidIDException | NullIDException e)
        {
            return e.getMessage();
        }
        return "Game successfully deleted!";
    }

}
