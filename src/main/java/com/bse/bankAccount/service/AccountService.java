package com.bse.bankAccount.service;

import com.bse.bankAccount.model.Operation;
import com.bse.bankAccount.web.exceptions.AccountNumberException;
import com.bse.bankAccount.web.exceptions.AmountException;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    BigDecimal withdraw(String accountNumber, BigDecimal amount) throws AmountException, AccountNumberException; // Returns the new balance
    BigDecimal deposit(String accountNumber, BigDecimal amount) throws AmountException, AccountNumberException; // Returns the new balance
    List<Operation> statement(String accountNumber) throws AccountNumberException; // Returns the account statement (history)
}
