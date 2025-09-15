package com.journal.journal.services;

import com.journal.journal.api.response.WeatherResponse;
import com.journal.journal.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<apiKey>", apiKey).replace("<city>", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

}
