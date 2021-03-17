package com.tp.gamemanagementsystem.daos;

import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.InvalidUsernameException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.models.User;
import com.tp.gamemanagementsystem.models.UserList;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User createUser(String userName) throws InvalidUsernameException;
    User getUserByName(String userName) throws InvalidUsernameException;
    User getUserByID(Integer userID) throws InvalidIDException, NullIDException;
    public List<UserList> getUserList(String userName) throws InvalidUsernameException;
    void deleteUser(Integer userID) throws NullIDException;

}
