package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.entity.JournalEntry;
import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
@Autowired
    private JournalEntryRepo journalEntryRepo;

@Autowired
private UserServices userServices;



@Transactional
    public void saveEntry(JournalEntry journalEntry,String userName) {

      try {
          User user = userServices.findByUsername(userName);
          JournalEntry savedEntry= journalEntryRepo.save(journalEntry);
          // journalEntries is list  so we  are adding saved journalEntry in entries to build small connection

          user.getJournalEntries().add(savedEntry);
          userServices.saveEntry(user);

      } catch (Exception e) {
          System.out.println(e);
           throw  new RuntimeException(e);
      }

    }
/// created a overloaded method for add after the updation
    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
      return journalEntryRepo.findAll();
    }

     public Optional<JournalEntry> getbyID(ObjectId id){
        return journalEntryRepo.findById( id);
     }

      public   void deleteById(ObjectId id,String userName){
         User user=userServices.findByUsername(userName);
          user.getJournalEntries().removeIf(x -> x.getId().equals(id));
          userServices.saveEntry(user);
          journalEntryRepo.deleteById(id);

      }


}
