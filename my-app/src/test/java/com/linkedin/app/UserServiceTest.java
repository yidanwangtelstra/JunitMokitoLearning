package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// we use @ExtendWith because we only want to test the service code, not the repository code, so we need to mock the repository
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private final User USER = new User("Jane Doe", "jane.doe@example.com");

    @Test
    public void saveUserTest(){
        when(userRepository.save(USER)).thenReturn(USER);

        User result = userService.saveUser(USER);
        verify(userRepository).save(USER);
        assertEquals(USER, result);
    }

    @Test
    public void getUserByIdTest(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));

        User foundUser = userService.getUserById(1L).orElse(null);

        verify(userRepository).findById(anyLong());
        assertEquals(USER, foundUser);
    }
}
