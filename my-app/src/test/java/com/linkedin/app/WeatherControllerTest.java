package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCurrentTemperatureSuccess() throws Exception {
        String city = "Adelaide";
        double temperature = 75.0;
        WeatherResponse weatherResponse = new WeatherResponse(new WeatherMain(temperature));
        when(weatherService.getWeather(city)).thenReturn(weatherResponse);

        mockMvc.perform(get("/getCurrentTemperature/{city}", city))
                .andExpect(status().isOk())
                .andExpect(content().string("75.0"));
    }

    @Test
    public void testGetCurrentTemperatureFailure() throws Exception {
        String city = "NonExistentCity";
        when(weatherService.getWeather(city)).thenReturn(null);

        mockMvc.perform(get("/getCurrentTemperature/{city}", city))
                .andExpect(status().isInternalServerError());
    }
}
