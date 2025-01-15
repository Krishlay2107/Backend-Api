package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
     private UserEntryRepo userEntryRepo;

     private   PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public User saveEntry(User user) {
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          user.setRoles(Arrays.asList("USER"));
        return userEntryRepo.save(user); // Return the saved user
    }

//    public User saveNewEntry(User user) {
//        return userEntryRepo.save(user); // Return the saved user
//    }


    public List<User> getAll(){
           return  userEntryRepo.findAll();
       }

        public  User findByUsername(String username){
           return userEntryRepo.findByUserName(username);
        }

      public Optional<User> findById(ObjectId id){
        return  userEntryRepo.findById(id);

      }

       public  void  deleteById(ObjectId id){
           userEntryRepo.deleteById(id);

       }





}
