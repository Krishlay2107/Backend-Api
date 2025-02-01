package com.springbootProject.Journalapp.cache;

import com.springbootProject.Journalapp.entity.CacheEntity;
import com.springbootProject.Journalapp.repository.ConfigureApiRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {


    @Autowired
    private ConfigureApiRepo configureApiRepo;

    // Thread-safe ConcurrentHashMap for in-memory cache
    public Map<String, String> recordsforApi ;

    @PostConstruct
    public void init() {
        recordsforApi = new HashMap<>();
        List<CacheEntity> all=configureApiRepo.findAll();
         for(CacheEntity c:all){
             recordsforApi.put(c.getKey(), c.getValue());
         }
    }
}
