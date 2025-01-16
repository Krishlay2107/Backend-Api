package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.entity.JournalEntry;
import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.services.JournalEntryServices;
import com.springbootProject.Journalapp.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    @Autowired
     private JournalEntryServices journalEntryServices;

    @Autowired
    private UserServices userServices;


    @GetMapping
    public ResponseEntity<?> getAlljournalentriesofUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userServices.findByUsername(userName);
        List<JournalEntry>all=user.getJournalEntries();
        // here i am finding the all the  user present in the entry
        if(all!=null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/add")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
         try {
             Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
             String userName=authentication.getName();
             myEntry.setDate(LocalDateTime.now());

             journalEntryServices.saveEntry(myEntry,userName);
             return  new ResponseEntity<>(myEntry,HttpStatus.CREATED);
         } catch (Exception e) {
             return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }

    }

    @GetMapping("/id/{myId}")
    public  ResponseEntity<JournalEntry> findById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
          User user=userServices.findByUsername(userName);
        List<JournalEntry> collect=  user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<JournalEntry> journalEntry= journalEntryServices.getbyID(myId);
            if(journalEntry.isPresent()){
                return  new ResponseEntity<>( journalEntry.get(), HttpStatus.OK);
            }
        }

                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removeFlag = journalEntryServices.deleteById(myId, userName);
        if (removeFlag) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userServices.findByUsername(userName);

        //  and this find the  all journalEntry of the user
        List<JournalEntry> collect=  user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());

        if (!collect.isEmpty()){
            // this is used to find the same id journal entry
            Optional<JournalEntry> journalEntry= journalEntryServices.getbyID(myId);
             if (journalEntry.isPresent()){
                  JournalEntry oldEntry=journalEntry.get();
                 oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
                 oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
                 journalEntryServices.saveEntry(oldEntry); // Save updated entry
                 return new ResponseEntity<>(oldEntry, HttpStatus.OK);
             }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
