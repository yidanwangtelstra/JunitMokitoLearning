package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {
  private static TaskManager taskManager;
  private Task task;

  @BeforeEach
  void setUp() {
    taskManager = new TaskManager();
    task = new Task("Read books");
    assertEquals(0, taskManager.count());
  }

  @Test
  public void addTask() {
    taskManager.add(task);

    assertTrue(taskManager.exists(task.getId()));
    assertEquals(1, taskManager.count());
  }

  @Test
  public void addTask_duplicateTasks() {
    Task task1 = new Task("Clean kitchen");
    Task task2 = new Task("Do laundry");

    taskManager.add(task1);
    taskManager.add(task2);
    assertTrue(taskManager.exists(task1.getId()));
    assertTrue(taskManager.exists(task2.getId()));
    assertEquals(2, taskManager.count());
  }

  @Test
  public void removeTask() {
    taskManager.add(task);
    int preTaskCount = taskManager.count();

    taskManager.remove(task.getId());

    assertFalse(taskManager.exists(task.getId()));
    assertEquals(preTaskCount - 1, taskManager.count());
  }

  @Test
  public void getTask() {
    taskManager.add(task);

    Task result = taskManager.get(task.getId());

    assertEquals(task, result);
  }
}
