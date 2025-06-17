package com.linkedin.app;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {

  private Map<Integer, Task> tasks;

  public TaskManager() {
    this.tasks = new HashMap<>();
  }

  public void add(Task task) {
    tasks.put(task.getId(), task);
  }

  public void remove(Integer taskId) {
    tasks.remove(taskId);
  }

  public boolean exists(Integer taskId) {
    return tasks.containsKey(taskId);
  }

  public int count() {
    return tasks.size();
  }

  public Task get(Integer id) {
    return tasks.get(id);
  }
}
