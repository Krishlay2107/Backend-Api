package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Public")
public class PublicController {



     @Autowired
     private UserServices userServices;

     @PostMapping("/post")
     public ResponseEntity<?> createUser(@RequestBody User user) {
         boolean flag= userServices.saveNewUser(user);
           if(flag){
                return new ResponseEntity<>(HttpStatus.CREATED);
           }else {
                return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
           }


     }
}
