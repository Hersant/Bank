package com.bse.bankAccount.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {
    final private OperationType opType; // Withdrawal or Deposit
    final private LocalDateTime date; // Date (and time) of the operation
    final private String amount; // Amount deposited or withdrawn (String, so it'll be easy to print -50 for example)
    final private BigDecimal balance; // Remaining balance

    public Operation(OperationType opType, String amount, BigDecimal balance) {
        this.opType = opType;
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "opType=" + opType +
                ", date=" + date +
                ", amount='" + amount + '\'' +
                ", balance=" + balance +
                '}';
    }
}
