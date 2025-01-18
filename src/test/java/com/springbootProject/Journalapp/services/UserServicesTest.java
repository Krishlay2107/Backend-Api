package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.repository.UserEntryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    // this method will take  dynamin inopiut and check the test by taking single input
@ParameterizedTest
@CsvSource({
        "ram",
        "vipul",
        "krishlayy",
        "aditya"
})
public  void  testFindByUserName(String userName){
        assertNotNull(userEntryRepo.findByUserName(userName));
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "2,3,5"
    })
    public  void test( int a, int b, int expected){
         assertEquals(expected,a+b);
     }




}
