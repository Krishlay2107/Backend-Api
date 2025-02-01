package com.springbootProject.Journalapp.repository;

import com.springbootProject.Journalapp.entity.CacheEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigureApiRepo extends MongoRepository<CacheEntity, ObjectId> {


}
