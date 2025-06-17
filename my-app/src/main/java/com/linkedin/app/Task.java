package com.linkedin.app;

public class Task {

  private int id;
  private static int counter = 0;

  public Task() {
    this.id = counter++;
  }

  public int getId() {
    return id;
  }

}
