package com.springbootProject.Journalapp.Controller;



import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    //  used to handle the admin controlll

   @Autowired
   private UserServices userServices;

    @GetMapping("/all-users")
     public ResponseEntity<?> getAllUser(){
           List<User> users =userServices.getAll();
            if(users!=null && !users.isEmpty()){
                 return  new ResponseEntity<>(users, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
@PostMapping("/createAdmin")
      public  ResponseEntity<?> createAdmin(@RequestBody User user){
        userServices.saveNewAdmin(user);
         return  new ResponseEntity<>(user,HttpStatus.CREATED);
}


}
