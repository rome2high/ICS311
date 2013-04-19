package com.abc.bank.dto;

import java.io.*;
import java.math.*;

public class Account implements Serializable {
    private AccountId id;
    private AccountType type;
    private BigDecimal balance;
    private CustomerId[] owners; // *always* at least one
}
