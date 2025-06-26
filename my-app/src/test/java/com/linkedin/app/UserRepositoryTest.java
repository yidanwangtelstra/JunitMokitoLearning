package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//  @DataJpaTest sets up in-memory database and configures relevant beans, allowing you test repository interactions without loading the full application context
//  by default @DataJpaTest wraps each test method in a transaction that is rolled back after the test completes, ensuring a clean state for each test
 @DataJpaTest
public class UserRepositoryTest {
    private final User USER = new User("John Doe", "john.done@example.com");

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        // Save the user to the repository
        User savedUser = userRepository.save(USER);

        assertEquals(USER, savedUser);
    }

    @Test
    public void findByIdTest(){
        User savedUser = userRepository.save(USER);
        User result = userRepository.findById(savedUser.getId()).orElse(null);
        assertEquals(USER, result);
    }

}
