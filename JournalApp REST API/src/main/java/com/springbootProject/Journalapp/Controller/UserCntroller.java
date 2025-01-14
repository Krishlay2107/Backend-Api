package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserCntroller {

      @Autowired
      private UserServices userServices;
     
     @GetMapping
     public List<User> getAllUser(){
         return userServices.getAll();
     }




     @PutMapping("/{userName}")
       public ResponseEntity<?>  updateUser(@RequestBody  User user,@PathVariable String userName ){
           User userInDb= userServices.findByUsername(userName);
            if(userInDb!=null){
                userInDb.setUserName(user.getUserName());
                 userInDb.setPassword(user.getPassword());
                  userServices.saveEntry(userInDb);
                   return new   ResponseEntity<>(HttpStatus.ACCEPTED);
            }
             return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }


}
