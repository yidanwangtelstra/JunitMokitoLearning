package com.linkedin.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherResponseTest {

    private static final WeatherMain WEATHER_MAIN = new WeatherMain(65);
    private static final WeatherResponse WEATHER_RESPONSE = new WeatherResponse(WEATHER_MAIN);

    @Test
    public void getMainTest(){
        assertEquals(WEATHER_MAIN, WEATHER_RESPONSE.getMain());
    }
}
