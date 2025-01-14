package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.entity.JournalEntry;
import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.services.JournalEntryServices;
import com.springbootProject.Journalapp.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    @Autowired
     private JournalEntryServices journalEntryServices;

    @Autowired
    private UserServices userServices;


    @GetMapping("/{userName}")
    public ResponseEntity<?> getAlljournalentriesofUser(@PathVariable String userName){
        User user=userServices.findByUsername(userName);
        List<JournalEntry>all=user.getJournalEntries();
// here i am finding the all the  user present in the entry
        if(all!=null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
         try {
             myEntry.setDate(LocalDateTime.now());

             journalEntryServices.saveEntry(myEntry,userName);
             return  new ResponseEntity<>(myEntry,HttpStatus.CREATED);
         } catch (Exception e) {
             return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }

    }

    @GetMapping("/id/{myId}")
    public  ResponseEntity<JournalEntry> findById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry= journalEntryServices.getbyID(myId);
                 if(journalEntry.isPresent()){
                      return  new ResponseEntity<>( journalEntry.get(), HttpStatus.OK);
                 }
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{userName}/{myId}")
    public   ResponseEntity<?> deleteById(@PathVariable  ObjectId myId,@PathVariable String userName){

        journalEntryServices.deleteById(myId,userName);
         return  new ResponseEntity<>( HttpStatus.NO_CONTENT);


    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry,@PathVariable String userName) {
        JournalEntry oldEntry = journalEntryServices.getbyID(id).orElse(null);
       if (oldEntry!=null) {

            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            journalEntryServices.saveEntry(oldEntry); // Save updated entry
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
