package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilityTest {

    @Test
    public void celsiusToFahrenheit() {
        try(MockedStatic<Utility> utility  = Mockito.mockStatic(Utility.class)){
            utility.when(() -> Utility.celsiusToFahrenheit(0))
                    .thenReturn(32.0);
            assertEquals(32.0, Utility.celsiusToFahrenheit(0));
        }
    }
}
