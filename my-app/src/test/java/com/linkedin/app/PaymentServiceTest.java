package com.linkedin.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

  @Spy
  private AccountService accountServiceSpy;

  @InjectMocks
  private PaymentService underTest;

  @Test
  public void processPaymentWithSpy() {
    // using doReturn to stub the getBalance method and make sure the real getBalance method is not called
    doReturn(666.0).when(accountServiceSpy).getBalance("account1");

    boolean result = underTest.processPayment("account1", 500.0);

    System.out.println("result of test1: " + result);
    assertTrue(result);
    verify(accountServiceSpy, times(3)).getBalance("account1");
    verify(accountServiceSpy).debit("account1", 500.0);
  }

  @Test
  public void insufficientBalanceWithSpy() {
    doReturn(300.0).when(accountServiceSpy).getBalance("account2");

    boolean result = underTest.processPayment("account2", 500.0);

    assertFalse(result);
    verify(accountServiceSpy).getBalance("account2");
    verify(accountServiceSpy, never()).debit(anyString(), anyDouble());
  }
}
