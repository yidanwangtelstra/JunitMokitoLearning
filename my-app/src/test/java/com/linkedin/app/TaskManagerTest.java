package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskManagerTest {

  @Test
  public void addTask() {
    TaskManager taskManger = new TaskManager();
    Task task = new Task("Pick up dry cleaning");

    taskManger.add(task);

    assertTrue(taskManger.exists(task.getId()));
  }

  @Test
  public void addTask_duplicateTasks() {
    TaskManager taskManager = new TaskManager();
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
    TaskManager taskManager = new TaskManager();
    Task task = new Task("Make presentation");
    taskManager.add(task);
    int preTaskCount = taskManager.count();

    taskManager.remove(task.getId());

    assertFalse(taskManager.exists(task.getId()));
    assertEquals(preTaskCount - 1, taskManager.count());
  }
}
