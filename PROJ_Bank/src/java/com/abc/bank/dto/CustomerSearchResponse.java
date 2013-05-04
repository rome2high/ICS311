package com.abc.bank.dto;

import java.io.*;

public class CustomerSearchResponse implements Serializable {
    private Customer[] customers;    // Never null, zero-len if none matched 
                                     // (or too many many matched).
    private int matchedTooManyCount; // 0 if good, >0 if too many matched

    public CustomerSearchResponse() {
    }
    
    public boolean matchedTooMany() {
        return matchedTooManyCount > 0;
    }
    
    public boolean matchedAny() {
        return !matchedTooMany() && customers.length > 0;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public int getMatchedTooManyCount() {
        return matchedTooManyCount;
    }

    public void setMatchedTooManyCount(int matchedTooManyCount) {
        this.matchedTooManyCount = matchedTooManyCount;
    }
}
