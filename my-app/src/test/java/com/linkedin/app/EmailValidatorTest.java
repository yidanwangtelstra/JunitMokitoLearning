package com.linkedin.app;

import com.linkedin.app.EmailValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class EmailValidatorTest {
  EmailValidator emailValidator = new EmailValidator();

  // using Parameterized tests to cover vvarious email formats
  @ParameterizedTest
  @ValueSource(strings = {
          "",
          "@gmail.com",
          "test@",
            "testgmail.com",
            "test@gmailcom",
            "test@gmail.",
            "test@.com",
            "test@ .com",
            "test@-gmail.com",
            "test@gmail.",
            "test@gmail. ",
            "test@gmail.123",
            "test,@gmail.com",
            "test#@gmail.com"
  })
    public void testInvalidEmailsTrue(String email) {
        assertFalse(emailValidator.isValid(email));
    }

@ParameterizedTest
@ValueSource(strings = {
        "user@example.com",
        "user.name@example.com",
        "user123@exmaple.com",
        "user_name@example.com"
})
public void testValidEmails(String email) {
    assertTrue(emailValidator.isValid(email));
  }

  // my answer
  @Test
  public void testEmptyEmail() {
    assertFalse(emailValidator.isValid(null));
    assertFalse(emailValidator.isValid(""));
  }

  @Test
  public void noLocalOrDomainPart()  {
    assertFalse(emailValidator.isValid("@gmail.com"));
    assertFalse(emailValidator.isValid("test@"));
  }

  @Test
  public void noAtMark(){
    assertFalse(emailValidator.isValid("testgmail.com"));
  }

  @Test
  public void noDotInDomain(){
    assertFalse(emailValidator.isValid("test@gmailcom"));
  }

  @Test
  public void domainWithOnlyOnePart() {
    assertFalse(emailValidator.isValid("test@gmail."));
    assertFalse(emailValidator.isValid("test@.com"));
  }

  @Test
  public void domainIsEmptyOrContiansInvalidCharacters() {
    assertFalse(emailValidator.isValid("test@ .com"));
    assertFalse(emailValidator.isValid("test@-gmail.com"));
  }

  @Test
  public void topLevelDomainEmptyOrContainsInvalidCharacters() {
    assertFalse(emailValidator.isValid("test@gmail."));
    assertFalse(emailValidator.isValid("test@gmail. "));
    assertFalse(emailValidator.isValid("test@gmail.123"));
  }

  @Test
  public void localPartContainsInvalidCharacters() {
    assertFalse(emailValidator.isValid("test,@gmail.com"));
    assertFalse(emailValidator.isValid("test#@gmail.com"));
  }

}