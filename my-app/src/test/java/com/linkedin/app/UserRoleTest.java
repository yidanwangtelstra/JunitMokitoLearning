package com.linkedin.app;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTest {

  @ParameterizedTest
  @EnumSource(value = UserRole.class, names = {"ADMIN", "MODERATOR", "USER", "GUEST"})
  void hasWritePermission(UserRole userRole){
    switch(userRole){
      case ADMIN, MODERATOR ->{
        assertTrue(userRole.hasWritePermission());
        System.out.println("User role: " + userRole + " has write permission.");
      }
      case USER, GUEST -> {
        assertFalse(userRole.hasWritePermission());
        System.out.println("User role: " + userRole + " has write permission.");
      }
    }
  }
}
