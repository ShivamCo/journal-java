package com.journal.journal.services;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.repository.JournalRepository;
import com.journal.journal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@Component
@Slf4j
public class UserServices {

    @Autowired
    private UserRepository userRepository;



    private  static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(UserEntity user){
        try {
            user.setPassword(passwordEncoder.encode((user.getPassword())));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e) {
            log.warn("warn for : {}", user.getUserName(),e );
            log.debug("gdgdfgsdfgdf", e);
            log.info("sdfsdfdsaaf", e);

        }


    }

    public void saveAdmin(UserEntity user){
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(UserEntity user){

        userRepository.save(user);
    }


    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public UserEntity findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
