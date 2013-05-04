package com.abc.bank.dto;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public InsufficientFundsException(String arg0) {
        super(arg0);
    }

    public InsufficientFundsException(Throwable arg0) {
        super(arg0);
    }
}
