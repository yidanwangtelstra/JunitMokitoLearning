package com.linkedin.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    private static final String CITY = "Adelaide";
    private static final double TEMP = 66;
    private static final String API_KEY = "123";


    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(weatherService, "apiKey", API_KEY);
    }

    @Test
    public void getWeather(){
         WeatherResponse weatherResponse = new WeatherResponse(new WeatherMain(TEMP));
         when(restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q="+CITY+"&appid="+API_KEY+"&units=imperial", WeatherResponse.class)).thenReturn(weatherResponse);

         WeatherResponse reponse = weatherService.getWeather(CITY);
         assertEquals(weatherResponse, reponse);
    }

    @Test
    public void getWeathe_apiError(){
        when(restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q="+CITY+"&appid="+API_KEY+"&units=imperial", WeatherResponse.class)).thenThrow(new RestClientException("Error occurred"));
        Exception e = assertThrows(RestClientException.class, () -> {
            weatherService.getWeather(CITY);
        });
        assertEquals("Error occurred", e.getMessage());
    }
}
