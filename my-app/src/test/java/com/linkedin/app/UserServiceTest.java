package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService underTest;

    @Mock
    private UserRepository userRepository;

    private static final String USERNAME_1 = "Alice";
    private static final String USERNAME_2 = "Bob";

    @Test
    public void createUser() {

        underTest.createUser(USERNAME_1);
        verify(userRepository).save(USERNAME_1);
    }

    @Test
    public void create_multipleUsers(){
        underTest.createUser(USERNAME_1);
        underTest.createUser(USERNAME_2);

        verify(userRepository).save(USERNAME_1);
        verify(userRepository).save(USERNAME_2);
        verifyNoMoreInteractions(userRepository);
        verify(userRepository, never()).update(USERNAME_1);
    }
}