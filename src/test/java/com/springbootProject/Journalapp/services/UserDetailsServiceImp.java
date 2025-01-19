package com.springbootProject.Journalapp.services;
// these is created for the MOCKITO testing purpose


import com.springbootProject.Journalapp.repository.UserEntryRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDetailsServiceImp {
@Autowired
    private  UserDetailsServiceImp userDetailsServiceImp;

@Mock
    private UserEntryRepo userEntryRepo;
@Test
  public  void LoadUserNameTest(){
   //  Mockito.when(userEntryRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Krishlay").password("a12345krishlay").build());

  }


}
