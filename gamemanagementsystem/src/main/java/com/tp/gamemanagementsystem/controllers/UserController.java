package com.tp.gamemanagementsystem.controllers;


import com.tp.gamemanagementsystem.exceptions.InvalidIDException;
import com.tp.gamemanagementsystem.exceptions.InvalidUsernameException;
import com.tp.gamemanagementsystem.models.User;
import com.tp.gamemanagementsystem.services.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            service.createUser(userName);
        }
        catch(InvalidIDException | InvalidUsernameException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok(newUser);

    }

}
