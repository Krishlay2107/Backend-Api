package com.springbootProject.Journalapp.repository;

import com.springbootProject.Journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepo  extends MongoRepository<User,ObjectId> {
   User  findByUserName(String username);
   User deleteByuserName(String username);


}
