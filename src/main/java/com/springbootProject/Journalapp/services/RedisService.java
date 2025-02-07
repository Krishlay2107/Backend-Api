package com.springbootProject.Journalapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

     public <T> T get(String key, Class<T> EntityClass){
         try {
           Object o =redisTemplate.opsForValue().get(key);
            if(o!=null){
                ObjectMapper mapper= new ObjectMapper();
                return  mapper.readValue(o.toString(),EntityClass);
            }else {
                System.out.println("No data found in Redis for key: {}"+ key);
                return null;
            }
         } catch (Exception e) {
             System.out.println("Exception"+e);
              return  null;
         }
     }

    public void set(String key, Object o, long ttl){
        try {
             ObjectMapper a= new ObjectMapper();
            String jsonVal = a.writeValueAsString(o);
        redisTemplate.opsForValue().set(key,jsonVal,ttl, TimeUnit.SECONDS);

        } catch (Exception e) {
           System.out.println("Exception occurred while saving data to Redis: "+ e);
        }
    }

}
