package com.springbootProject.Journalapp.repository;

import com.springbootProject.Journalapp.entity.CacheEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ConfigureforApi extends MongoRepository<CacheEntity, ObjectId> {

    List<CacheEntity> findAll();
}
