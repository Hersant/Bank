package com.bse.bankAccount.web.controller;

import com.bse.bankAccount.model.Operation;
import com.bse.bankAccount.service.AccountService;
import com.bse.bankAccount.web.exceptions.AccountNumberException;
import com.bse.bankAccount.web.exceptions.AmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping("/account/withdraw/{account}/{amount}")
    public BigDecimal withdraw(@PathVariable String account, @PathVariable BigDecimal amount) throws AmountException, AccountNumberException {
        return accountService.withdraw(account, amount);
    }

    @PutMapping("/account/deposit/{account}/{amount}")
    public BigDecimal deposit(@PathVariable String account, @PathVariable BigDecimal amount) throws AmountException, AccountNumberException {
        return accountService.deposit(account, amount);
    }

    @GetMapping("/account/statement/{account}")
    public List<Operation> getStatement(@PathVariable String account) throws AccountNumberException {
        return accountService.statement(account);
    }
}
