package com.journal.journal.controllers;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.services.JournalServices;
import com.journal.journal.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<?> getAllUser() {

        List<UserEntity> allUsers = userServices.getAllUsers();

        if (allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody UserEntity newUser) {

        try {
            userServices.addUser(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }


    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity userToBeUpdated) {

        try {
            userServices.addUser();
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }


    }


}
