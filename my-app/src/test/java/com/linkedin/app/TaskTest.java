package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TaskTest {

  @Test
  public void getId() {
    Task task = new Task("Write outline for article");
    assertNotNull(task.getId());
  }

  @Test
  public void getDescription(){
    Task task = new Task("Read a book");
    String result = task.getDescription();
    assertEquals("Read a book", result);
  }
}
