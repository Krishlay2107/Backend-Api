package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.Utility.JwtUtils;
import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.services.UserDetailsServiceImp;
import com.springbootProject.Journalapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Public")
public class PublicController {

    @Autowired
    private  AuthenticationManager authenticationManager;
     @Autowired
      private UserDetailsServiceImp userDetailsServiceImp;
      @Autowired
       private JwtUtils jwtUtils;



     @Autowired
     private UserServices userServices;

     @PostMapping("/signup")
     public ResponseEntity<?> signup(@RequestBody User user) {
         boolean flag= userServices.saveNewUser(user);
           if(flag){
                return new ResponseEntity<>(HttpStatus.CREATED);
           }else {
                return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
           }


     }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
       try {
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
           UserDetails userDetails= userDetailsServiceImp.loadUserByUsername(user.getUserName());
             String jwt=jwtUtils.generateToken(userDetails.getUsername());
              return new ResponseEntity<>(jwt,HttpStatus.CREATED);
       } catch (Exception e) {
          System.out.println("exception in authencation"+e);
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

     }
}
