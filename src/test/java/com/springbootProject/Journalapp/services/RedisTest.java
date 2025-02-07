package com.springbootProject.Journalapp.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
@Disabled
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
     public  void testforemail(){
         redisTemplate.opsForValue().set("email","krishlaysharma3720@gmail.com");
          Object  mail=redisTemplate.opsForValue().get("email");
        Object  salary=redisTemplate.opsForValue().get("salary");
           System.out.println(mail);

        System.out.println(salary);
           int a=1;
     }


}
