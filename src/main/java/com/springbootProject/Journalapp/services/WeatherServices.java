package com.springbootProject.Journalapp.services;

import com.springbootProject.Journalapp.apiresponses.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServices {

   @Autowired
     private  RestTemplate restTemplate;

    private    static final String apikey="8531beda04141a15168f6421e69343a1";

    private static final String API = "http://api.weatherstack.com/current?access_key=ApiKey&query=CITY";


      public WeatherResponse  getWeather(String city){
          String finalUrl = API.replace("ApiKey", apikey).replace("CITY", city);;
      ResponseEntity<WeatherResponse> exchange= restTemplate.exchange(finalUrl, HttpMethod.GET,null,WeatherResponse.class);
          return exchange.getBody();

     }


}
