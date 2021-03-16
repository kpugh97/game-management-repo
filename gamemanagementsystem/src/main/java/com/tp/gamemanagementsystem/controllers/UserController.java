package com.tp.gamemanagementsystem.controllers;


import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.InvalidUsernameException;
import com.tp.gamemanagementsystem.exceptions.NullIDException;
import com.tp.gamemanagementsystem.models.User;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {


    @Autowired
    GameManagementService service;

    @PostMapping("newuser")
    public ResponseEntity createUser(@RequestBody String userName)
    {
        User newUser = null;
        try{
            newUser = service.createUser(userName);
        }
        catch(InvalidIDException | InvalidUsernameException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok(newUser);

    }

    @GetMapping("/users")
    public ResponseEntity createUser()
    {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity getUserByName(@PathVariable String userName)
    {
        User user = null;
        try
        {
            user = service.getUserByName(userName);
        }
        catch (InvalidUsernameException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/id/{userID}")
    public ResponseEntity getUserByID(@PathVariable Integer userID)
    {
        User user = null;
        try
        {
            user = service.getUserByID(userID);
        }
        catch (NullIDException | InvalidIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/delete/{userID}")
    public ResponseEntity deleteUser(@PathVariable Integer userID)
    {
        try
        {
            service.deleteUser(userID);
        }
        catch (NullIDException | InvalidIDException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("User successfully deleted!");
    }



}
