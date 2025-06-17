package com.linkedin.app;

import java.util.HashMap;

public class TaskManager {
  HashMap<Integer, Task> tasks;
  public TaskManager(){
    this.tasks = new HashMap<>();
  }

  public void add(Task task) {
    tasks.put(task.getId(), task);
  }

  public boolean exists(int id) {
    return tasks.containsKey(id);
  }

}
