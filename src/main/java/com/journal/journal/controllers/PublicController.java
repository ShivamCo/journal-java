package com.journal.journal.controllers;

import com.journal.journal.entity.UserEntity;
import com.journal.journal.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserServices userServices;


    @PostMapping("/create-user")
    public void createUser(@RequestBody UserEntity user){
        userServices.saveNewUser(user);
    }

}
