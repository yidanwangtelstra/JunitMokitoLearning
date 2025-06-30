package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WeatherMainTest {
    private static final WeatherMain WEATHER_MAIN = new WeatherMain(25.0);

    @Test
    public void getSetTempTest() {
        assertEquals(25.0, WEATHER_MAIN.getTemp());
        WEATHER_MAIN.setTemp(30.0);
        assertEquals(30.0, WEATHER_MAIN.getTemp());
    }
}
