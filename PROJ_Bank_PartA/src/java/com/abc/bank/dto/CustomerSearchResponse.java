package com.abc.bank.dto;

import java.io.*;

public class CustomerSearchResponse implements Serializable {
    private Customer[] customers;    // never null, zero-len if none matched (or too many many matched)
    private int matchedTooManyCount; // 0 if good, >0 if too many matched

    public boolean matchedTooMany() {
        return matchedTooManyCount > 0;
    }
    
    public boolean matchedAny() {
        return !matchedTooMany() && customers.length > 0;
    }
}
