package com.linkedin.app;



import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {

  @ParameterizedTest
  @ValueSource(strings = {"", "a", "racecar", "RACEcar"})
  public void isPalindrome(String str){
    assertTrue(StringUtils.isPalindrome(str));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hello", "world", "java", "test"})
  public void isNotPalindrome(String str){
    assertFalse(StringUtils.isPalindrome(str));
  }
}