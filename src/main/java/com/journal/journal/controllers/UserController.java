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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepository userRepository;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
 String userName = authentication.getName();
UserEntity userInDB = userServices.findByUserName(userName);
if(userInDB != null){
    userInDB.setUserName(user.getUserName());
    userInDB.setPassword(user.getPassword());
    userServices.saveNewUser(userInDB);
} return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping

    public ResponseEntity<?> deleteUserById(){

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
    userRepository.deleteByUserName(authentication.getName());


         return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
