package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.apiresponses.WeatherResponse;
import com.springbootProject.Journalapp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
     private  RedisService redisService;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
          WeatherResponse redisWeatherResponse=  redisService.get("weather_of_"+city,WeatherResponse.class);

           if(redisWeatherResponse!=null){
                return redisWeatherResponse;

           }
            else{
               // Retrieve the weather API URL from the cache and replace placeholders
               String finalUrl = appCache.recordsforApi.get("weather_Api")
                       .replace("CITY", city)
                       .replace("ApiKey", weatherApiKey);
               // Make the GET request to the weather API
               ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
               WeatherResponse body=response.getBody();
               if (body != null) {
                   redisService.set(city, body, 900);
               }
                 return body;


           }

    }

}

//private static final String API = "http://api.weatherstack.com/current?access_key=ApiKey&query=CITY";

