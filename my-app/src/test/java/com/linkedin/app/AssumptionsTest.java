package com.linkedin.app;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

public class AssumptionsTest {

  @Test
  void onlyIfEnvVarIsSet() {
    String dbUrl = System.getenv("DB_URL");
    assumeTrue(dbUrl != null && !dbUrl.isEmpty(), () -> "Test skipped: DB_URL environment variable is not set");
    // Test logic here
    System.out.println("This test runs only if DB_URL environment variable is set" + dbUrl);
  }
}
