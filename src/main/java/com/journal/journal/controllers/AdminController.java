package com.journal.journal.controllers;


import com.journal.journal.entity.UserEntity;
import com.journal.journal.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){

    List<UserEntity> all = userServices.getAll();

    if(all != null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;

    }


    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody UserEntity user){
        userServices.saveAdmin(user);
    }


}
