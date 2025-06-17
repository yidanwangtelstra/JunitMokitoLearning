package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskManagerTest {

  @Test
  public void addTask() {
    TaskManager taskManger = new TaskManager();
    Task task = new Task(1);

    taskManger.add(task);

    assertTrue(!taskManger.exists(task.getId()));
  }
}
