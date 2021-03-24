package com.bse.bankAccount.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AccountNumberException extends Exception {
    public AccountNumberException(String message) {
        super(message);
    }
}
