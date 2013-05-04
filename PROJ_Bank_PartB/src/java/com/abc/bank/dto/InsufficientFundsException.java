package com.abc.bank.dto;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(Account account) {
        this("Insufficient funds available in account " + account.getId());
    }
    
    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }
}
