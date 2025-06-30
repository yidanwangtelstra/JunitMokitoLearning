package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WeatherApplicationIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void getCurrentTemperature(){
        when(restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q=Adelaide&appid=123456&units=imperial", WeatherResponse.class))
            .thenReturn(new WeatherResponse(new WeatherMain(65)));
        Double temperature = testRestTemplate.getForObject("/getCurrentTemperature/Adelaide", Double.class);
        assertEquals(65, temperature);
    }
}
