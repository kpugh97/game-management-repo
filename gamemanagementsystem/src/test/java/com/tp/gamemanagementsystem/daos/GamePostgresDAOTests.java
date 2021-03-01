package com.tp.gamemanagementsystem.daos;

import static org.junit.jupiter.api.Assertions.*;

import com.tp.gamemanagementsystem.exceptions.*;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@ActiveProfiles("DAOTesting")
public class GamePostgresDAOTests {
    @Autowired
    GamePostgresDAO testDAO;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void setup()
    {
        template.update("TRUNCATE \"GamePlatforms\", \"Reviews\", \"Games\", \"Platforms\" RESTART IDENTITY;");

        template.update( "INSERT INTO \"Platforms\" (\"name\") VALUES ('SEGA Dreamcast'), ('Playstation 5')" );
    }


    @Test
    public void addGameTest() throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);
        Platform PS4 = new Platform();
        PS4.setPlatformID(2);

        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);

        Game toReturn = testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms);

        assertEquals(1,toReturn.getGameID());
        assertEquals("Sonic Adventure",toReturn.getTitle());

        List<Game> allGames = testDAO.getGameCollection();
        assertEquals(1,allGames.get(0).getGameID());
        assertEquals("Sonic Adventure",allGames.get(0).getTitle());
    }

    @Test
    public void addGameNullTitle()
    {
        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);

        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        Game newGame = new Game();
        newGame.setTitle(null);
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);
        assertThrows(NullTitleException.class,()-> testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms));
    }

    @Test
    public void addGameNullCategory()
    {
        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);

        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory(null);
        newGame.setReleaseYear(1998);
        assertThrows(NullCategoryException.class,()-> testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms));

    }

    @Test
    public void addGameNullYear()
    {
        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);

        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(null);
        assertThrows(NullYearException.class,()-> testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms));
    }

    @Test
    public void addGameInvalidPlatforms()
    {
        List<Integer> platforms = new ArrayList<>();
        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);
        platforms.add(3);
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);
        assertThrows(InvalidIDException.class,()-> testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms));
    }

    @Test
    public void addGameNullPlatforms()
    {
        List<Integer> platforms = new ArrayList<>();
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);
        assertThrows(NullPlatformException.class,()-> testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms));
    }

    @Test
    public void getGameByIDTest() throws InvalidIDException, NullIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        List<Integer> platforms = new ArrayList<>();
        List<Integer> platforms2 = new ArrayList<>();
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);

        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);
        Platform PS4 = new Platform();
        PS4.setPlatformID(2);
        platforms.add(1);
        platforms.add(2);
        testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms);

        Game game2 = new Game();
        game2.setTitle("Sonic Adventure 2");
        game2.setCategory("RPG");
        game2.setReleaseYear(2000);
        platforms2.add(2);
        testDAO.createGame(game2.getTitle(),game2.getCategory(),game2.getReleaseYear(),platforms2);

        assertEquals("Sonic Adventure", testDAO.getGameByID(1).getTitle());
        assertEquals("Sonic Adventure 2",testDAO.getGameByID(2).getTitle());
    }

    @Test
    public void getGameByIDNull()
    {
        assertThrows(NullIDException.class,()->testDAO.getGameByID(null));
    }

    @Test
    public void getGameByIDInvalid() throws InvalidIDException, NullIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        List<Integer> platforms = new ArrayList<>();
        List<Integer> platforms2 = new ArrayList<>();
        Game newGame = new Game();
        newGame.setGameID(null);
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);

        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);
        Platform PS4 = new Platform();
        PS4.setPlatformID(2);
        platforms.add(1);
        platforms.add(2);
        testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms);
        assertEquals("Sonic Adventure", testDAO.getGameByID(1).getTitle());
        assertThrows(InvalidIDException.class,()->testDAO.getGameByID(2).getTitle());
    }

    @Test
    public void getGameByCategoryTest() throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        List<Integer> platforms = new ArrayList<>();
        List<Integer> platforms2 = new ArrayList<>();
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);

        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);
        Platform PS4 = new Platform();
        PS4.setPlatformID(2);
        platforms.add(1);
        platforms.add(2);
        testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms);

        Game game2 = new Game();
        game2.setTitle("Sonic Adventure 2");
        game2.setCategory("RPG");
        game2.setReleaseYear(2000);
        platforms2.add(2);
        testDAO.createGame(game2.getTitle(),game2.getCategory(),game2.getReleaseYear(),platforms2);

        Game game3 = new Game();
        game3.setTitle("SMITE");
        game3.setCategory("MOBA");
        game3.setReleaseYear(2012);
        testDAO.createGame(game3.getTitle(),game3.getCategory(),game3.getReleaseYear(),platforms2);

        assertEquals("Sonic Adventure", testDAO.getGameByCategory("RPG").get(0).getTitle());
        assertEquals("Sonic Adventure 2",testDAO.getGameByCategory("RPG").get(1).getTitle());
        assertEquals("SMITE",testDAO.getGameByCategory("MOBA").get(0).getTitle());
    }

    @Test
    public void getGameByCategoryTestInvalidCat()
    {
        assertThrows(NullCategoryException.class,()-> testDAO.getGameByCategory("MMORPG"));
    }

    @Test
    public void getGameByCategoryTestNull()
    {
        assertThrows(NullCategoryException.class,()-> testDAO.getGameByCategory(null));
    }

    @Test
    public void getGameByYearTest() throws InvalidIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        List<Integer> platforms = new ArrayList<>();
        List<Integer> platforms2 = new ArrayList<>();
        Game newGame = new Game();
        newGame.setTitle("Sonic Adventure");
        newGame.setCategory("RPG");
        newGame.setReleaseYear(1998);

        Platform dreamCast = new Platform();
        dreamCast.setPlatformID(1);
        Platform PS4 = new Platform();
        PS4.setPlatformID(2);
        platforms.add(1);
        platforms.add(2);
        testDAO.createGame(newGame.getTitle(),newGame.getCategory(),newGame.getReleaseYear(),platforms);

        Game game2 = new Game();
        game2.setTitle("Sonic Adventure 2");
        game2.setCategory("RPG");
        game2.setReleaseYear(2000);
        platforms2.add(2);
        testDAO.createGame(game2.getTitle(),game2.getCategory(),game2.getReleaseYear(),platforms2);

        assertEquals("Sonic Adventure", testDAO.getGameByYear(1998).get(0).getTitle());
        assertEquals("Sonic Adventure 2",testDAO.getGameByYear(2000).get(0).getTitle());
    }

    @Test
    public void getGameByYearInvalidYear()
    {
        assertThrows(NullYearException.class,()-> testDAO.getGameByYear(1995));
    }

    @Test
    public void getGameByYearNull()
    {
        assertThrows(NullYearException.class,()-> testDAO.getGameByYear(null));
    }

    @Test
    public void editGameTest() throws InvalidIDException,NullIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        List<Integer> platforms = new ArrayList<>();
        List<Integer> platforms2 = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);

        template.update( "INSERT INTO \"Platforms\" (\"name\") VALUES ('Mobile')" );

        Platform mobile = new Platform();
        mobile.setPlatformID(3);
        platforms2.add(3);
        testDAO.createGame("Sonic Adventure", "RPG",1998, platforms);
        testDAO.editGame(1,"Pokemon Go", "Simulation",2016);

    }

    @Test
    public void editGameNullTitle()
    {
        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        assertThrows(NullTitleException.class, ()->testDAO.editGame(1,null,"RPG",2000));
    }

    @Test
    public void editGameNullCategory()
    {
        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        assertThrows(NullCategoryException.class, ()->testDAO.editGame(1,"Cool Game",null ,2020));

    }

    @Test
    public void editGameNullYear()
    {
        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        assertThrows(NullYearException.class, ()->testDAO.editGame(1,"Cool Game","RPG",null));

    }

    @Test
    public void editGameNullID()
    {
        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        assertThrows(NullIDException.class, ()->testDAO.editGame(null,"Cool Game","RPG",2020));
    }

    @Test
    public void deleteGameTest() throws InvalidIDException, NullIDException, NullTitleException, NullCategoryException, NullYearException, NullPlatformException
    {
        List<Integer> platforms = new ArrayList<>();
        platforms.add(1);
        platforms.add(2);
        testDAO.createGame("Sonic Adventure", "RPG",1998, platforms);
        assertEquals(1,testDAO.getGameByID(1).getGameID());
        assertEquals("Sonic Adventure",testDAO.getGameByID(1).getTitle());
        testDAO.deleteGame(1);
        assertThrows(InvalidIDException.class, ()->testDAO.getGameByID(1));
    }

    @Test
    public void deleteGameNullID()  {
        assertThrows(NullIDException.class,()->testDAO.deleteGame(null));
    }

}
