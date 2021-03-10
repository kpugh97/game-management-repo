package com.tp.gamemanagementsystem.controllers;

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

    @PostMapping("/newgame")
    public ResponseEntity createGame(@RequestBody CreateGameRequest request)
    {
        Game toReturn = null;
        try {
            toReturn = service.createGame(request.getTitle(), request.getCategory(),request.getReleaseYear(), request.getPlatforms());
            getImage(request.getTitle());
        }catch (InvalidIDException | NullTitleException| NullCategoryException| NullYearException| NullPlatformException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }


    @GetMapping("/game")
    public ResponseEntity getGameCollection()
    {
        return ResponseEntity.ok(service.getGameCollection());
    }

    @GetMapping("/game/id/{gameID}")
    public ResponseEntity getGameByID(@PathVariable Integer gameID)
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


    @GetMapping("game/genre/{category}")
    public ResponseEntity getGameByCategory(@PathVariable String category)
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

    @GetMapping("game/title/{title}")
    public ResponseEntity getGameByTitle(@PathVariable String title)
    {
        List<Game> game =null;
        try {
            game = service.getGamesByTitle(title);
        }
        catch (NullTitleException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(game);
    }



    @GetMapping("/game/year/{year}")
    public ResponseEntity getGameByYear(@PathVariable Integer year)
    {
        List<Game> game =null;
        try {
            game = service.getGamesByYear(year);
        }
        catch (NullYearException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(game);
    }

    @PutMapping("/edit/game")
    public ResponseEntity editGame(@RequestBody Game editGame)
    {
        try {
            service.editGame(editGame.getGameID(),editGame.getTitle(),editGame.getCategory(),editGame.getReleaseYear());
        }
        catch (InvalidIDException | NullIDException | NullYearException | NullTitleException | NullCategoryException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Game successfully edited!");
    }

    @DeleteMapping("/delete/game/{gameID}")
    public ResponseEntity deleteGame(@PathVariable Integer gameID)
    {
        try {
            service.deleteGame(gameID);
        }
        catch (InvalidIDException | NullIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Game successfully deleted!");
    }

    @GetMapping("/image/{name}")
    public ResponseEntity getImage(@PathVariable String name)
    {
        //make get request to this uri with parameters of this string name passed in from the body
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.giantbomb.com/api/search/?api_key=d43060a7cedfdd5c627a315fcd1965ae6366d127&format=json&query="
                        + URLEncoder.encode(name, StandardCharsets.UTF_8) + "&resources=game")).build();
        ImageResponse imageURL = new ImageResponse();
        try
        {
            //right now we a response of all the information we want put into a string
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int urlStart = response.body().indexOf("\"medium_url\":");
            int urlEnd = response.body().indexOf(",",urlStart);

            //if we want just the url or more than this result add or subtract less of my substring
            imageURL.setUrl(response.body().substring(urlStart+14,urlEnd-1));
            //trimming out escapes in the string
            String trimString = imageURL.getUrl().replaceAll("\\\\","");
            //set that trimmed string as our new url
            imageURL.setUrl(trimString);
            System.out.println(imageURL.getUrl());
            service.saveImageToDB(name,imageURL.getUrl());
        }
        catch (IOException | InterruptedException | NullTitleException | NullURLException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return  ResponseEntity.ok(imageURL);
    }

}
