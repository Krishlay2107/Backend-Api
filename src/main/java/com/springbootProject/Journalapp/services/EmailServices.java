package com.springbootProject.Journalapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServices {
    @Autowired
    private JavaMailSender javaMailSender;

     public  void  sendEmail(String to, String subject, String body){
         try{
             SimpleMailMessage mail= new SimpleMailMessage();
             mail.setTo(to);
             mail.setSubject(subject);
              mail.setText(body);
              javaMailSender.send(mail);


         }catch (Exception e){
System.out.println("not capable of sending message ");
         }

     }





}
