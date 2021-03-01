package com.tp.gamemanagementsystem.daos;

import static org.junit.jupiter.api.Assertions.*;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.exceptions.NullReviewException;
import com.tp.gamemanagementsystem.exceptions.NullTitleException;
import com.tp.gamemanagementsystem.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("DAOTesting")
public class ReviewPostgresDAOTests {

    @Autowired
    ReviewPostgresDAO testDAO;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void setup()
    {
        template.update("TRUNCATE \"GamePlatforms\", \"Reviews\", \"Games\", \"Platforms\" RESTART IDENTITY;");
        template.update( "INSERT INTO \"Games\" (\"title\", \"category\", \"year\")\n" +
                "\tVALUES ('Sonic', 'RPG', 2010), ('Pokemon', 'RPG', 2012)" );
        template.update( "INSERT INTO \"Platforms\" (\"name\") VALUES ('Gamecube'), ('Playstation 2')" );

    }

    @Test
    public void addReviewTest() throws NullIDException, NullReviewException,NullTitleException, InvalidIDException
    {
        Review newReview = new Review();
        newReview = testDAO.makeReview("Pretty cool", "The game is really fun and the music is amazing!",8,1);
        assertEquals(1,newReview.getReviewID());
        assertEquals("Pretty cool",newReview.getReviewTitle());
        assertEquals(1,newReview.getGameID());
        assertEquals("The game is really fun and the music is amazing!",newReview.getReviewText());
        assertEquals(8,newReview.getRating());
        Review secondReview = testDAO.makeReview("Best game ever!", "This game is better than any other game I've played ever! Truly amazing!",10,2);
        assertEquals("Best game ever!",secondReview.getReviewTitle());
        assertEquals(2,secondReview.getGameID());
        assertEquals("This game is better than any other game I've played ever! Truly amazing!",secondReview.getReviewText());
        assertEquals(10,secondReview.getRating());
    }

    @Test
    public void addReviewNullTitle()
    {
        assertThrows(NullTitleException.class,()->testDAO.makeReview(null,"My brand new review!",8, 1 ));

    }

    @Test
    public void addReviewNullReview()
    {
        assertThrows(NullReviewException.class,()->testDAO.makeReview("New Review",null,8, 1 ));

    }

    @Test
    public void addReviewNullGameID()
    {
        assertThrows(NullIDException.class,()->testDAO.makeReview("New Review","My brand new review!",8, null ));

    }

    @Test
    public void editReviewTest() throws NullIDException, NullReviewException,NullTitleException, InvalidIDException
    {
        testDAO.makeReview("Pretty cool", "The game is really fun and the music is amazing!",8,1);
        assertEquals(1,testDAO.getReviewByID(1).getReviewID());
        assertEquals("Pretty cool",testDAO.getReviewByID(1).getReviewTitle());
        assertEquals(1,testDAO.getReviewByID(1).getGameID());
        assertEquals("The game is really fun and the music is amazing!",testDAO.getReviewByID(1).getReviewText());
        assertEquals(8,testDAO.getReviewByID(1).getRating());
        testDAO.editReview(1,"Edited: Turns out this game is much better than I thought! I can't give it a high enough score!",10);
        assertEquals(1,testDAO.getReviewByID(1).getGameID());
        assertEquals("Edited: Turns out this game is much better than I thought! I can't give it a high enough score!",testDAO.getReviewByID(1).getReviewText());
        assertEquals(10,testDAO.getReviewByID(1).getRating());
    }

    @Test
    public void editReviewNullID()
    {
        assertThrows(NullIDException.class,()->testDAO.editReview(null, "My brand new review!",7));

    }

    @Test
    public void editReviewNullReview()
    {
        assertThrows(NullReviewException.class,()->testDAO.editReview(1, null,5));

    }

    @Test
    public void deleteReviewTest()
    {


    }

    @Test
    public void deleteReviewNullID()
    {
        assertThrows(NullIDException.class, ()->testDAO.deleteReview(null));
    }



}
