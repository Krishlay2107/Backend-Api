package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.entity.CacheEntity;
import com.springbootProject.Journalapp.repository.ConfigureApiRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private ConfigureApiRepo configureApiRepo;

    public CacheEntity saveCacheEntry(String key, String value) {
        // Create a new CacheEntity object
        CacheEntity cacheEntity = new CacheEntity(new ObjectId(), key, value);

        // Save the entity to the MongoDB database
        return configureApiRepo.save(cacheEntity);
    }
}
