package com.linkedin.app;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    public void personFields(){
        Person person = new Person("Kelly J", 30, "kellyJ@test.com", "123 Main St, Springfield, USA");
        assertAll("Person fields",
                () -> assertEquals("Kelly J", person.getName()),
                () -> assertEquals(30, person.getAge()),
                () -> assertEquals("kellyJ@test.com", person.getEmail()),
                () -> assertEquals("123 Main St, Springfield, USA", person.getAddress())
        );
    }
}
