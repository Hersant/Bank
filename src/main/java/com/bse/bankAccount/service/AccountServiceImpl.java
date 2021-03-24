package com.bse.bankAccount.service;

import com.bse.bankAccount.dao.AccountDao;
import com.bse.bankAccount.model.Operation;
import com.bse.bankAccount.web.exceptions.AccountNumberException;
import com.bse.bankAccount.web.exceptions.AmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements com.bse.bankAccount.service.AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public BigDecimal withdraw(String accountNumber, BigDecimal amount) throws AmountException, AccountNumberException {
        return accountDao.withdraw(accountNumber, amount);
    }

    @Override
    public BigDecimal deposit(String accountNumber, BigDecimal amount) throws AmountException, AccountNumberException {
        return accountDao.deposit(accountNumber, amount);
    }

    @Override
    public List<Operation> statement(String accountNumber) throws AccountNumberException {
        return accountDao.statement(accountNumber);
    }
}
