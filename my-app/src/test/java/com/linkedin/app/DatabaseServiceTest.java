package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DatabaseServiceTest {

    @Mock
    private DatabaseConnection dbConnectionMock;

    @InjectMocks
    private DatabaseService underTest;

    @Test
    public void databaseConnectionFailure() throws Exception {
        when(dbConnectionMock.getConnection()).thenThrow(new DatabaseException("Not able to connect to the database"));
        assertThrows(DatabaseException.class, () -> underTest.getData());
    }
}