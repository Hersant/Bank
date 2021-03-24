package com.bse.bankAccount.dao;

import com.bse.bankAccount.model.Operation;
import com.bse.bankAccount.model.OperationType;
import com.bse.bankAccount.service.AccountServiceImpl;
import com.bse.bankAccount.web.exceptions.AccountNumberException;
import com.bse.bankAccount.web.exceptions.AmountException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class AccountDao {
    // A logger is used to keep traces
    private static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    /*
    Since the purpose of this mini app isn't data persistence, a Map can be used as repository.
    However using an in-memory such as H2 with JPA can be a great idea.
     */
    private static Map<String, List<Operation>> accountsRepo = new HashMap<>(); // String represents the account number
    static {
        // Initialize data
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(OperationType.DEPOSIT, String.valueOf(200), new BigDecimal(200)));
        accountsRepo.put("FR123", operations);
    }

    public synchronized BigDecimal withdraw(String accountNum, BigDecimal value) throws AmountException, AccountNumberException { // To prevent concurrent access to the balance
        BigDecimal balance = new BigDecimal(0);

        if (!accountsRepo.containsKey(accountNum)) {
            logger.log(Level.WARNING, "Failed to withdraw " + String.valueOf(value) + " euros from account " + accountNum);
            throw new AccountNumberException("Please check the account number and try again.");
        } else if (value.compareTo(new BigDecimal(0)) <= 0) {
            logger.log(Level.WARNING, "Failed to withdraw " + String.valueOf(value) + " euros from account " + accountNum);
            throw new AmountException("Sorry the desired amount must be greater than 0.");
        } else {
            // Retrieve the balance of the last transaction, then compare it to the desired amount
            List<Operation> ops = accountsRepo.get(accountNum);
            balance = (ops.get(ops.size() -1)).getBalance();

            if (value.compareTo(balance) > 0) {
                logger.log(Level.WARNING, "Failed to withdraw " + String.valueOf(value) + " euros from account " + accountNum);
                throw new AmountException("Sorry there isn't enough money in the account.");
            } else {
                balance = balance.subtract(value); // Balance update
                Operation op = new Operation(OperationType.WITHDRAWAL, "-" + String.valueOf(value), balance);
                ops.add(op);
                logger.log(Level.INFO, op.toString());
            }
        }
        return balance;
    }

    public synchronized BigDecimal deposit(String accountNum, BigDecimal value) throws AmountException, AccountNumberException { // To prevent concurrent access to the balance
        BigDecimal balance = new BigDecimal(0);

        if (!accountsRepo.containsKey(accountNum)) {
            logger.log(Level.WARNING, "Failed to deposit " + String.valueOf(value) + " euros into account " + accountNum);
            throw new AccountNumberException("Please check the account number and try again.");
        } else if (value.compareTo(new BigDecimal(0)) <= 0) {
            logger.log(Level.WARNING, "Failed to deposit " + String.valueOf(value) + " euros into account " + accountNum);
            throw new AmountException("Sorry the amount must be greater than 0.");
        } else {
            // Retrieve the balance of the last transaction, then add it to the amount
            List<Operation> ops = accountsRepo.get(accountNum);
            balance = (ops.get(ops.size() -1)).getBalance();
            balance = balance.add(value); // Balance update
            Operation op = new Operation(OperationType.DEPOSIT, String.valueOf(value), balance);
            ops.add(op);
            logger.log(Level.INFO, op.toString());
        }
        return balance;
    }

    public List<Operation> statement(String accountNum) throws AccountNumberException {
        if (!accountsRepo.containsKey(accountNum)) {
            logger.log(Level.WARNING, "Failed to print statement of account " + accountNum);
            throw new AccountNumberException("Please check the account number and try again.");
        }
        return accountsRepo.get(accountNum);
    }
}
