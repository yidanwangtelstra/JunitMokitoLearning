package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @InjectMocks
  private TransactionService underTest;

  @Mock
  private LoggerService loggerService;

  @Captor
  private ArgumentCaptor<Timestamp> captor;

  @Test
  public void processTransaction() {
    Timestamp start = new Timestamp(System.currentTimeMillis());
    Transaction transaction = new Transaction("TX123", 10.50);

    underTest.processTransaction(transaction);

    verify(loggerService).logMessage(eq("TX123"), eq(10.50),
        captor.capture());
    Timestamp end = captor.getValue();
    System.out.println("Timestamp: " + end);
    assertTrue(areWithinSeconds(start, end, 5));
  }

  private boolean areWithinSeconds(Timestamp start, Timestamp end, int seconds) {
    long diff = end.getTime() - start.getTime();
    long diffSeconds = diff / 1000;
    return  diffSeconds <= seconds;
  }
}