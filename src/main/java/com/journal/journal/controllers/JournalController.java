package com.journal.journal.controllers;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal-entry")
public class JournalController {

   @Autowired
    private JournalRepository journalRepository;


    @GetMapping
    public List<JournalEntity> getAllEntry(){
       return journalRepository.findAll();
    }

    @PostMapping
    public void addEntry(@RequestBody JournalEntity newJournal){
        journalRepository.insert(newJournal);
    }

}
