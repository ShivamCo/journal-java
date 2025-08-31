package com.journal.journal.controllers;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.repository.JournalRepository;
import com.journal.journal.repository.UserRepository;
import com.journal.journal.services.JournalServices;
import com.journal.journal.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userServices.getAll();
    }


    @PostMapping
    public void createUser(@RequestBody UserEntity user){
        userServices.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user, @PathVariable String userName ){
UserEntity userInDB = userServices.findByUserName(userName);
if(userInDB != null){
    userInDB.setUserName(user.getUserName());
    userInDB.setPassword(user.getPassword());
    userServices.saveEntry(userInDB);
} return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
