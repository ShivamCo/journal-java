package com.journal.journal.controllers;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.repository.JournalRepository;
import com.journal.journal.services.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal-entry")
public class JournalController {

    @Autowired
    private JournalServices journalServices;


    @GetMapping
    public ResponseEntity<?> getAllEntry() {

        List<JournalEntity> all = journalServices.getAll();

        if( all !=null && !all.isEmpty() ){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public void addEntry(@RequestBody JournalEntity newJournal) {
        journalServices.addOne(newJournal);
    }

    @DeleteMapping("/delete/{id}")
    public void removeTask(@PathVariable("id") ObjectId id) {
        journalServices.deleteOne(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") ObjectId id, @RequestBody JournalEntity journal) {
        try {
            JournalEntity updatedJournal = journalServices.updateOne(id, journal);
            return ResponseEntity.ok(updatedJournal);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }




}
