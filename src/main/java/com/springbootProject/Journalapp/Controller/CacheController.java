package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.entity.CacheEntity;
import com.springbootProject.Journalapp.services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API")
public class CacheController {

     @Autowired
     private CacheService cacheService;


      @PostMapping
     public ResponseEntity<?> postApi(@RequestBody CacheEntity entry){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String userName=authentication.getName();
           cacheService.saveCacheEntry(entry.getKey(), entry.getValue());
            return  new ResponseEntity<>(HttpStatus.CREATED);

      }

}
