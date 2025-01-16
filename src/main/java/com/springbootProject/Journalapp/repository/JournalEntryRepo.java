package com.springbootProject.Journalapp.repository;

import com.springbootProject.Journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
   // List<JournalEntry> findByUserName(String userName);

}
