package com.abc.bank.dto;

public class AccountTransferResponse {
    private Account sourceAccount; // with new balance
    private Account destinationAccount; // with new balance
    private String transferReceiptCode; // not stored in database
    
}
