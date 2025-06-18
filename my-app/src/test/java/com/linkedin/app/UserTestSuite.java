package com.linkedin.app;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UserRegistrationTest.class,
        UserLoginTest.class
})
public class UserTestSuite {
}
