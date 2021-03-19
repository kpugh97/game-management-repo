package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.daos.mappers.*;
import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.InvalidUsernameException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.Review;
import com.tp.gamemanagementsystem.models.User;
import com.tp.gamemanagementsystem.models.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile({"mainApp","DAOTesting","serviceTest"})
public class UserPostgresDAO implements UserDAO{


    @Autowired
    JdbcTemplate template;

    @Override
    public User createUser(String userName) throws InvalidUsernameException {
        if(userName == null)
        {
            throw new InvalidUsernameException("Cannot create a new user with a null username!");
        }
        User newUser = new User();
        try {
            Integer userID = template.queryForObject("INSERT INTO \"Users\" (\"userName\") VALUES (?) RETURNING \"userID\"", new IntegerMapper("userID"),
                    userName);
            newUser.setUserID(userID);
            newUser.setUserName(userName);
        }
        catch (DuplicateKeyException e)
        {
            throw new InvalidUsernameException("A user with that name has already been created. Try again!");
        }
        return newUser;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> userList =  template.query("SELECT * FROM \"Users\"", new UserMapper());
        return userList;
    }

    @Override
    public User getUserByName(String userName) throws InvalidUsernameException {
        if(userName == null)
        {
            throw new InvalidUsernameException("Cannot retrieve a user with a null username!");
        }
        User toReturn = null;
        try
        {
            toReturn= template.queryForObject("SELECT * FROM \"Users\" WHERE \"userName\"= ? ", new UserMapper(),userName);
        }
        catch (EmptyResultDataAccessException e)
        {
         throw new InvalidUsernameException("Cannot find a user with the name "+userName+"!");
        }
        return toReturn;
    }

    @Override
    public User getUserByID(Integer userID) throws InvalidIDException, NullIDException {
        if(userID == null)
        {
            throw new NullIDException("Cannot retrieve a user with a null user ID!");
        }
        User toReturn = null;
        try
        {
            toReturn= template.queryForObject("SELECT * FROM \"Users\" WHERE \"userID\"= ? ", new UserMapper(),userID);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("Cannot find a user with ID "+userID+"!");
        }
        return toReturn;
    }

    @Override
    public List<UserList> getUserList(String userName) throws InvalidUsernameException {
        if(userName == null)
        {
            throw new InvalidUsernameException("Cannot retrieve a user with a null username!");
        }
        List<UserList> list = null;
        try
        {
            list = template.query("SELECT \"title\", us.\"userID\" ,\"userName\", gs.\"gameID\", ul.\"statusID\" FROM \"Users\" as us\n" +
                    "INNER JOIN \"UserLists\" as ul\n" +
                    "ON us.\"userID\" = ul.\"userID\"\n" +
                    "INNER JOIN \"Games\" as gs \n" +
                    "ON gs.\"gameID\" = ul.\"gameID\"\n" +
                    "INNER JOIN \"Status\" as st \n" +
                    "ON st.\"statusID\" = ul.\"statusID\"\n" +
                    "WHERE \"userName\" = ?", new UserListMapper(),userName);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new InvalidUsernameException("Cannot find a user list for a user with name "+userName+"!");
        }
        return list;
    }


    @Override
    public void deleteUser(Integer userID) throws NullIDException {
        if(userID == null)
        {
            throw new NullIDException("Cannot delete a user with a null user ID!");
        }
        template.update("DELETE FROM \"UserLists\" WHERE \"userID\" =  ?",userID);
        template.update("DELETE FROM \"UserReviews\" WHERE \"userID\"= ?",userID);
        template.update("DELETE FROM \"Reviews\" WHERE \"userID\"= ?",userID);
        template.update("DELETE FROM \"Users\" WHERE \"userID\" =  ?",userID);

    }

    @Override
    public void addGameToUserList(Integer userID, Integer gameID) throws InvalidIDException, NullIDException {
        if(userID == null)
        {
            throw new NullIDException("Cannot edit a user with a null ID");
        }
        if(gameID == null)
        {
            throw new NullIDException("Cannot add a game with a null ID");
        }
        try {
            template.update("INSERT INTO \"UserLists\" (\"userID\",\"gameID\", \"statusID\")\n" +
                    "VALUES (?,?,?)", userID, gameID,3);
        }
        catch (DataIntegrityViolationException | EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("ERROR! Cannot make changes to this list! Invalid user or game ID!");
        }
    }

    @Override
    public void deleteGameFromUserList(Integer userID, Integer gameID) throws InvalidIDException, NullIDException {
        if(userID == null)
        {
            throw new NullIDException("Cannot edit a user with a null ID");
        }
        if(gameID == null)
        {
            throw new NullIDException("Cannot add a game with a null ID");
        }
        try {
            template.update("DELETE FROM \"UserLists\" \n" +
                    "WHERE \"userID\"= ? \n"+
                    "AND \"gameID\"= ? ", userID, gameID);
        }
        catch (DataIntegrityViolationException | EmptyResultDataAccessException e)
        {
            throw new InvalidIDException("ERROR! Cannot make changes to this list! Invalid user or game ID!");
        }
    }

    @Override
    public void editUserGameInfo(Integer userID, Integer gameID, Integer statusID) throws InvalidIDException, NullIDException {
        if(userID == null)
        {
            throw new NullIDException("Cannot edit a user with a null ID");
        }
        if(gameID == null)
        {
            throw new NullIDException("Cannot add a game with a null ID");
        }
        try {
            template.update("UPDATE \"UserLists\" SET  \"statusID\"= ?\n" +
                    "WHERE \"userID\"= ? \n" +
                    "AND \"gameID\"= ? ", statusID, userID, gameID);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new InvalidIDException("ERROR! Cannot make changes to this list! Invalid user, game, or status ID!");
        }
    }

    public List<Review> getUserReviews(String userName) throws InvalidUsernameException
    {
        if(userName == null)
        {
            throw new InvalidUsernameException("Cannot retrieve reviews for a user with a null username!");
        }
        List<Review> list = null;
        try {
            list = template.query("SELECT  \"userName\", \"reviewTitle\", \"reviewText\", rs.\"reviewID\", \"rating\", \"gameTitle\", \"gameID\", us.\"userID\" FROM \"Users\" as us\n" +
                    "INNER JOIN \"UserReviews\" as ur\n" +
                    "ON us.\"userID\" = ur.\"userID\"\n" +
                    "INNER JOIN \"Reviews\" as rs\n" +
                    "ON rs.\"reviewID\" = ur.\"reviewID\"\n" +
                    "AND rs.\"userID\" = ur.\"userID\"\n" +
                    "WHERE \"userName\" = ? ",new ReviewMapper(),userName);
        }
        catch (DataIntegrityViolationException | EmptyResultDataAccessException e)
        {
            throw new InvalidUsernameException("Cannot retrieve reviews for a user with username "+userName+"!");
        }
        return list;
    }
}
