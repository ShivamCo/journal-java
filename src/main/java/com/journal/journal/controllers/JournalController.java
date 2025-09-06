package com.journal.journal.controllers;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import com.journal.journal.services.JournalServices;
import com.journal.journal.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalServices journalServices;

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userServices.findByUserName(userName);
        List<JournalEntity> all = user.getJournalEntities();
        if(all != null){
            return new ResponseEntity<>( all, HttpStatus.OK );
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntity myEntry) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userServices.findByUserName(userName);

        try {
            journalServices.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED );
        } catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST );
        }
    };

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userServices.findByUserName(userName);
        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
if(!collect.isEmpty()){
    Optional<JournalEntity> journalEntity = journalServices.findById(myId);
    if(journalEntity.isPresent()){
        return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
    }
}


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userServices.findByUserName(userName);

        journalServices.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userServices.findByUserName(userName);

        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntity> journalEntry = journalServices.findById(myId);

            if(journalEntry.isPresent()){

                JournalEntity old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() :  old.getTitle());
                old.setContent(newEntry.getContent() !=  null && !newEntry.getContent().equals("")? newEntry.getContent() :  old.getContent());
                journalServices.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);

            }


        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
