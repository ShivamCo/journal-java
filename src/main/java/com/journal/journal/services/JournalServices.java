package com.journal.journal.services;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServices {

    @Autowired
    JournalRepository journalRepository;

    public List<JournalEntity> getAll(){
        return journalRepository.findAll();
    }

    public void addOne(JournalEntity newTask){
       journalRepository.insert(newTask);
    }

    public void deleteOne(ObjectId id){
        journalRepository.deleteById(id);
    }


    public void updateOne(ObjectId id) {
        Optional<JournalEntity> currentEntry = journalRepository.findById(id);

        if (currentEntry.isPresent()) {
            JournalEntity entry = currentEntry.get();

            entry.setTitle("Updated Title");
            entry.setDescription("Description");

            journalRepository.save(entry);
        } else {

            throw new RuntimeException("Journal entry not found with id: " + id);
        }
    }



}
