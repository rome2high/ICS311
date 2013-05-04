package com.abc.bank.dto;

public class AccountTransferResponse {
    private Account sourceAccount; // with new balance
    private Account destinationAccount; // with new balance
    private String transferReceiptCode; // not stored in database
    
    public AccountTransferResponse() {
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getTransferReceiptCode() {
        return transferReceiptCode;
    }

    public void setTransferReceiptCode(String transferReceiptCode) {
        this.transferReceiptCode = transferReceiptCode;
    }
}
