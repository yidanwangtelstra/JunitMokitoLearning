package com.linkedin.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getId(){
        assertEquals(1, new Task(1).getId());
    }
}
