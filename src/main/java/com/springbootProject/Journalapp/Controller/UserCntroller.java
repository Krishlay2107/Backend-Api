package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.repository.UserEntryRepo;
import com.springbootProject.Journalapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserCntroller {

      @Autowired
      private UserServices userServices;
      @Autowired
     private  UserEntryRepo    userEntryRepo;
     @GetMapping
     public List<User> getAllUser(){
         return userServices.getAll();
     }




     @PutMapping("/update")
       public ResponseEntity<?>  updateUser(@RequestBody  User user ){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName=authentication.getName();
           User userInDb= userServices.findByUsername(userName);

                userInDb.setUserName(user.getUserName());
                 userInDb.setPassword(user.getPassword());
                  userServices.saveNewUser(userInDb);


             return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

     @DeleteMapping
      public  ResponseEntity<?> deleteByUserName(){

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         userEntryRepo.deleteByuserName(authentication.getName());
          return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }



}
