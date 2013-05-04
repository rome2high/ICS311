package com.abc.bank.dto;

import java.io.*;

public final class CustomerId implements Serializable {
    private final int id;

    public CustomerId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
