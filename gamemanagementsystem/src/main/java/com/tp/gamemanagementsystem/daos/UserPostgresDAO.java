package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.daos.mappers.GameMapper;
import com.tp.gamemanagementsystem.daos.mappers.IntegerMapper;
import com.tp.gamemanagementsystem.daos.mappers.UserMapper;
import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.InvalidUsernameException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.models.Game;
import com.tp.gamemanagementsystem.models.User;
import com.tp.gamemanagementsystem.models.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
    public UserList getUserList(String userName) throws InvalidUsernameException {
        if(userName == null)
        {
            throw new InvalidUsernameException("Cannot retrieve a user with a null username!");
        }
//        UserList list = null;
//        try
//        {
//            template.query();
//        }
        return null;
    }


    @Override
    public void deleteUser(Integer userID) throws NullIDException {
        if(userID == null)
        {
            throw new NullIDException("Cannot delete a user with a null user ID!");
        }
        template.update("DELETE FROM \"UserLists\" WHERE \"userID\" =  ?",userID);
        template.update("DELETE FROM \"Users\" WHERE \"userID\" =  ?",userID);
        template.update("DELETE FROM \"Reviews\" WHERE \"userID\"= ?",userID);



    }
}
