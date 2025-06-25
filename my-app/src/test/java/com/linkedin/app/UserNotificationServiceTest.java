package com.linkedin.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserNotificationServiceTest {

    @Mock
    private EmailService emailService;

    @Mock
    private Logger logger;

    @InjectMocks
    private UserNotificationService underTest;

    private static final String EMAIL = "test@example.com";
    private static final String MESSAGE = "Hello, User!";

    @Test
    void notifyUser() throws EmailException {
        underTest.notifyUser(EMAIL, MESSAGE);
        verify(emailService).sendEmail(anyString(), anyString(), anyString());
        verify(logger).info("Attempting to send notification to " + EMAIL);
        verify(logger).info("Notification sent to " + EMAIL);
    }

    @Test
    void notifyUser_EmailException() throws EmailException {
        doThrow(new EmailException("Email sending failed"))
                .when(emailService).sendEmail(anyString(), anyString(), anyString());

//        when(emailService.sendEmail(anyString(), anyString(), anyString()).thenThrow(new EmailException("Email sending failed")));

        underTest.notifyUser(EMAIL, MESSAGE);
        verify(emailService).sendEmail(anyString(), anyString(), anyString());
        verify(logger).log(eq(Level.SEVERE), eq("Failed to send email to " + EMAIL), any(EmailException.class));
    }
}