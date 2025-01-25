package com.springbootProject.Journalapp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

     @Autowired
    private EmailServices emailServices;
     @Test
      public  void  emailsendTest(){
         emailServices.sendEmail("krishlaysharma3720@gmail.com","greeting ",
                 "kyseee hoo bhai good morning ");
      }


}
