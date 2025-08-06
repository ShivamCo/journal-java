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


    public JournalEntity updateOne(ObjectId id, JournalEntity updatedEntry) {
        JournalEntity currentEntry = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal entry not found with id: " + id));

        currentEntry.setTitle(updatedEntry.getTitle());
        currentEntry.setDescription(updatedEntry.getDescription());

        return journalRepository.save(currentEntry);
    }



}
