package com.friends.accountservice.exceptions;

public class AccountServiceException extends Exception {

    public AccountServiceException(String errorMsg) {
        super(errorMsg);
    }
}
