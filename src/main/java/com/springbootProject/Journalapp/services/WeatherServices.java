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

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Autowired
     private AppCache appCache;



    public WeatherResponse getWeather(String city) {
        String baseUrl = appCache.app_Cache.get("weather_Api");

        // Check if baseUrl is null
        if (baseUrl == null) {
            throw new IllegalStateException("Weather API base URL not found in the cache");
        }

        // Replace placeholders in the URL
        String finalUrl = baseUrl.replace("city", city).replace("apiKey", weatherApiKey);

        // Make the API request
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);

        // Return the response body
        return response.getBody();
    }

}

//private static final String API = "http://api.weatherstack.com/current?access_key=ApiKey&query=CITY";

