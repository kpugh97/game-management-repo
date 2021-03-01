package com.tp.gamemanagementsystem.daos;

import static org.junit.jupiter.api.Assertions.*;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
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
public class PlatformPostgresDAOTests {

    @Autowired
    PlatformPostgresDAO testDAO;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void setup()
    {
        template.update("TRUNCATE \"GamePlatforms\", \"Reviews\", \"Games\", \"Platforms\" RESTART IDENTITY;");

        template.update( "INSERT INTO \"Platforms\" (\"name\") VALUES ('Gamecube'), ('Playstation 5')" );
    }

    @Test
    public void addPlatformTest() throws NullTitleException
    {
        Platform newPlatform = testDAO.addPlatform("Nintendo Switch");
        assertEquals(3,newPlatform.getPlatformID());
    }

    @Test
    public void addPlatformNullName()
    {
     assertThrows(NullTitleException.class, ()-> testDAO.addPlatform(null));
    }

    @Test
    public void deletePlatform() throws NullIDException, InvalidIDException
    {

        assertEquals(1, testDAO.getPlatformByID(1).getPlatformID());
        assertEquals("Gamecube", testDAO.getPlatformByID(1).getName());
        testDAO.deletePlatform(1);
        assertThrows(InvalidIDException.class, ()->testDAO.getPlatformByID(1));
    }

    @Test
    public void deletePlatformNullID()
    {
        assertThrows(NullIDException.class, ()->testDAO.deletePlatform(null));
    }

    @Test
    public void editPlatform() throws InvalidIDException, NullIDException, NullTitleException
    {
        Platform somePlat = testDAO.getPlatformByID(1);
        assertEquals("Gamecube",somePlat.getName());
        testDAO.updatePlatformName(1,"Cooler Gamecube");
        somePlat.setName(testDAO.getPlatformByID(1).getName());
        assertEquals("Cooler Gamecube",somePlat.getName());
    }

    @Test
    public void editPlatformNullID()
    {
        assertThrows(NullIDException.class, ()-> testDAO.updatePlatformName(null,"Cooler Name"));
    }

    @Test
    public void editPlatformNullName()
    {
        assertThrows(NullTitleException.class, ()-> testDAO.updatePlatformName(1,null));
    }
}
