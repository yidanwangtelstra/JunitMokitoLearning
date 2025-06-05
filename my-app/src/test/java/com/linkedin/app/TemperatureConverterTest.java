package com.linkedin.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemperatureConverterTest {

    @Test
    public void celsiusToFahrenheitCorrectOutput(){
        double result = TemperatureConverter.celsiusToFahrenheit(20);
        assertEquals(((20 * 9 / 5) + 32), result);
    }

    @Test
    public void celsiusToFahrenheitException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.celsiusToFahrenheit(-300);
        });
        assertEquals("Temperature cannot be below absolute zero (-273.15°C)", exception.getMessage());
    }

    @Test
    public void fahrenheitToCelsiusException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.fahrenheitToCelsius(-500);
        });
        assertEquals("Temperature cannot be below absolute zero (-459.67°F)", exception.getMessage());
    }

}
