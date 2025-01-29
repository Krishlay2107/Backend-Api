package com.springbootProject.Journalapp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
     public  void testforemail(){
         redisTemplate.opsForValue().set("email","krishlaysharma3720@gmail.com");
          Object  sal=redisTemplate.opsForValue().get("sal");
          System.out.print(sal);

          int a=1;
     }


}
