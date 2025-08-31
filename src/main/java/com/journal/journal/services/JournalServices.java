package com.journal.journal.services;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserServices userServices;

    public void saveEntry(JournalEntity journalEntry, String userName){

    UserEntity user = userServices.findByUserName(userName);
    journalEntry.setDate(LocalDateTime.now());
    JournalEntity savedOne = journalRepository.save(journalEntry);
    user.getJournalEntities().add(savedOne);
    userServices.saveEntry(user);

    }

    public void saveEntry(JournalEntity journalEntry){


        journalRepository.save(journalEntry);

    }

    public List<JournalEntity> getAll(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){

        UserEntity user = userServices.findByUserName(userName);
        user.getJournalEntities().removeIf(x-> x.getId().equals(id));
        userServices.saveEntry(user);

        journalRepository.deleteById(id);
    }

}
