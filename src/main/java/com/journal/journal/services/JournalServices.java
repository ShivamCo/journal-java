package com.journal.journal.services;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServices {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserServices userServices;


    @Transactional
    public void saveEntry(JournalEntity journalEntry, String userName) {
        try {
            UserEntity user = userServices.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntity savedOne = journalRepository.save(journalEntry);
            user.getJournalEntities().add(savedOne);
            userServices.saveUser(user);
        } catch (Exception e) {

            throw new RuntimeException("An Error Occurred" + e);
        }

    }


    public void saveEntry(JournalEntity journalEntry) {


        journalRepository.save(journalEntry);

    }

    public List<JournalEntity> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String userName) {

        try {
            UserEntity user = userServices.findByUserName(userName);
            boolean removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userServices.saveUser(user);

                journalRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An Error occurred while deleted the entry", e);
        }


    }


}
