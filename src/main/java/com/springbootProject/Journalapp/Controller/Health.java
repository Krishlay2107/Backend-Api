package com.springbootProject.Journalapp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/health")
public class Health {
     public  String m1(){
          return " i am fine ";
     }

          public  String m2(){
               return " i am fine ";
          }

}


