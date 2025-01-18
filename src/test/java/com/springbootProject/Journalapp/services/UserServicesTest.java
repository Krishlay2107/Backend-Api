package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.repository.UserEntryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserEntryRepo userEntryRepo;

    @Test
    public  void  testFindByUserName(){
        assertEquals(4, 2 + 2);
        assertNotNull(userEntryRepo.findByUserName("ram"));
    }




}
