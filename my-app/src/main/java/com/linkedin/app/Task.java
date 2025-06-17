package com.linkedin.app;

public class Task {

  private Integer id;
  private static int counter = 0;
  private String description;

  public Task(String description) {
    this.id = counter++;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

}
