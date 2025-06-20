package com.linkedin.app;

public class EmailValidator {

  public boolean isValid(String email) {
    if (email == null || email.isEmpty()) {
      return false;
    }

    String[] parts;

    try {
      parts = email.split("@");
    } catch (Exception e) {
      return false;
    }

    if (parts.length != 2) {
      return false;
    }

    String localPart = parts[0];
    String domainPart = parts[1];

    if (localPart.isEmpty() || domainPart.isEmpty()) {
      return false;
    }

    if (!domainPart.contains(".")) {
      return false;
    }

    String[] domainParts = domainPart.split("\\.");
    if (domainParts.length < 2) {
      return false;
    }

    String domainName = domainParts[0];
    String topLevelDomain = domainParts[1];

    if (domainName.isEmpty() || !domainName.matches("[a-zA-Z0-9]+")) {
      return false;
    }

    if (topLevelDomain.isEmpty() || !topLevelDomain.matches("[a-zA-Z]+")) {
      return false;
    }

    if (!localPart.matches("[a-zA-Z0-9._-]+")) {
      return false;
    }

    return true;
  }
}