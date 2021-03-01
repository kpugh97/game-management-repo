package com.tp.gamemanagementsystem.services;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void fakeTest()
    {
        assertTrue(false);
    }

}
