package com.tp.gamemanagementsystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("serviceTest")
public class GameManagementServiceTest {

    @Autowired
    GameManagementService testService;



    @BeforeEach
    public void setup()
    {

    }

    @Test
    public void getGameByIDTest()
    {

    }

    @Test
    public void getGameByIDInvalidID()
    {

    }

    @Test
    public void createGameTest()
    {

    }

    @Test
    public void createGameTestInvalidID()
    {

    }

    @Test
    public void createGameTestInvalidTitle()
    {

    }

    @Test
    public void createGameTestInvalidCategory()
    {

    }

    @Test
    public void createGameTestInvalidYear()
    {

    }

    @Test
    public void createGameTestInvalidPlatform()
    {

    }

    @Test
    public void getGameByYearTest()
    {

    }

    @Test
    public void getGameByYearInvalidYear()
    {

    }

    @Test
    public void getGameByCategoryTest()
    {

    }

    @Test
    public void getGameByCategoryInvalidCategory()
    {

    }

    @Test
    public void deleteGameTest()
    {

    }

    @Test
    public void deleteGameTestInvalidID()
    {

    }

    @Test
    public void editGameTest()
    {

    }

    @Test
    public void editGameInvalidID()
    {

    }

    @Test
    public void editGameInvalidTitle()
    {

    }

    @Test
    public void editGameInvalidCategory()
    {

    }

    @Test
    public void editGameInvalidYear()
    {

    }

}
