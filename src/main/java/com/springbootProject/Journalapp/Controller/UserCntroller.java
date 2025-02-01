package com.springbootProject.Journalapp.Controller;

import com.springbootProject.Journalapp.apiresponses.WeatherResponse;
import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.repository.UserEntryRepo;
import com.springbootProject.Journalapp.services.CacheService;
import com.springbootProject.Journalapp.services.UserServices;
import com.springbootProject.Journalapp.services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCntroller {

      @Autowired
      private UserServices userServices;

      @Autowired
      private CacheService cacheService;


      @Autowired
       private WeatherServices weatherServices;
      @Autowired
     private  UserEntryRepo    userEntryRepo;


//     @GetMapping
//     public List<User> getAllUser(){
//         return userServices.getAll();
//     }




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

   @GetMapping("/g")
    public  ResponseEntity<?> Greeting(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       WeatherResponse weatherResponse= weatherServices.getWeather("London");


       System.out.println(weatherResponse);

       String greeting="";
          if (weatherResponse!=null){
              greeting=  " its feels like  "+weatherResponse.getCurrent().getTemperature();
          }else {
              greeting += ", but we're unable to fetch the weather details for Mumbai right now.";
          }
                //  userEntryRepo.deleteByuserName(authentication.getName());
        return  new ResponseEntity<>("hii "+   authentication.getName() +  greeting,HttpStatus.OK);
    }



}
