package com.springbootProject.Journalapp.cache;

import com.springbootProject.Journalapp.entity.CacheEntity;
import com.springbootProject.Journalapp.repository.ConfigureforApi;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {



    @Autowired
    private ConfigureforApi configureforApi;

    public Map<String, String> app_Cache= new HashMap<>();

    @PostConstruct
    public void init() {
        List<CacheEntity> all = configureforApi.findAll();
        System.out.println("Cache Entities: " + all); // Print fetched data for debugging

        if (all.isEmpty()) {
            System.out.println("No data found in MongoDB!");
        }

        for (CacheEntity cacheEntity : all) {
            app_Cache.put(cacheEntity.getKey(), cacheEntity.getValue());
        }

    }
}
