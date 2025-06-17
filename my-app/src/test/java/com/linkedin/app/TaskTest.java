package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
  private static Task task;
  private static final String DESCRIPTION  = "Write outline for article";

  @BeforeEach
  public void setUp(){
    task = new Task(DESCRIPTION);
  }


  @Test
  public void getId() {
    assertNotNull(task.getId());
  }

  @Test
  public void getDescription() {
    String result = task.getDescription();
    assertEquals(DESCRIPTION, result);
  }

  @Test
  public void setDescription() {
    task.setDescription("Pack for summer trip");
    assertEquals("Pack for summer trip", task.getDescription());
  }
}
