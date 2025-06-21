package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TimeoutTest {

  @Test
  @Timeout(value =3)
  void methodWithTimeout() throws InterruptedException {
    // Perform test logic that should complete within 5 seconds
    Thread.sleep(2000); // Simulating a lengthy operation
  }
}