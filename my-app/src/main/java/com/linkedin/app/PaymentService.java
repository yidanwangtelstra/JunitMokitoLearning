package com.linkedin.app;

public class PaymentService {
  private AccountService accountService;

  public PaymentService(AccountService accountService) {
    this.accountService = accountService;
  }

  public boolean processPayment(String accountId, double amount) {
    double balance = accountService.getBalance(accountId);
    System.out.println("Balance of account " + accountId + " is " + balance);
    if (balance >= amount) {
      accountService.debit(accountId, amount);
        System.out.println("Debited " + amount + " from account " + accountId + ". New balance: " + accountService.getBalance(accountId));
      return true;
    }
    return false;
  }
}
