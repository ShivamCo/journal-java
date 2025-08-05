package com.journal.journal.controllers;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.repository.JournalRepository;
import com.journal.journal.services.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal-entry")
public class JournalController {

    @Autowired
    private JournalServices journalServices;


    @GetMapping
    public List<JournalEntity> getAllEntry() {
        return journalServices.getAll();
    }

    @PostMapping
    public void addEntry(@RequestBody JournalEntity newJournal) {
        journalServices.addOne(newJournal);
    }

    @DeleteMapping("/delete/{id}")
    public void removeTask(@PathVariable("id") ObjectId id){
            journalServices.deleteOne(id);
    }


}
