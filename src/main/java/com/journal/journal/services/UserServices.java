package com.journal.journal.services;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.repository.JournalRepository;
import com.journal.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(UserEntity newTask){
        userRepository.insert(newTask);
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }


    public UserEntity updateUser(ObjectId id, UserEntity updatedEntry) {
        UserEntity currentEntry = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal entry not found with id: " + id));

//        currentEntry.setTitle(updatedEntry.getTitle());
//        currentEntry.setDescription(updatedEntry.getDescription());
''
        return userRepository.save(currentEntry);
    }



}
