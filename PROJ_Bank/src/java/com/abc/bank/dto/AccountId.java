package com.abc.bank.dto;

import java.io.*;

public final class AccountId implements Serializable {
    private final int id;

    public AccountId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
