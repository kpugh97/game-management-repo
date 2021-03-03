package com.tp.gamemanagementsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requests.CreateGameRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:4200")
public class GameController {
    @Autowired
    GameManagementService service;

    //TODO: make this a response entity
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

    @GetMapping("/image")
    public ResponseEntity getImage(@RequestBody String name)
    {
        //make get request to this uri with parameters of this string name passed in from the body
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.giantbomb.com/api/search/?api_key=d43060a7cedfdd5c627a315fcd1965ae6366d127&format=json&query="
                        + URLEncoder.encode(name, StandardCharsets.UTF_8) + "&resources=game")).build();
        try
        {
            //right now we a response of all the information we want put into a string
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            //do something with that response body: preferably take the string and parse it into a way that we get a
            //data type (like a map) to assign all the value to keys
            ObjectMapper mapper = new ObjectMapper();
            LinkedHashMap<String, LinkedHashSet<Integer>> linkedMap = mapper.readValue(response.body(),LinkedHashMap.class);
            System.out.println(linkedMap);
            for(Map.Entry entry : linkedMap.entrySet())
            {
                System.out.println("Key:"+ entry.getKey()+"Values: "+ entry.getValue());
            }

        }
        catch (IOException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
       throw new UnsupportedOperationException();
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
