package com.abc.bank.dto;

import java.io.*;

public class AccountType implements Serializable {
    private int id;
    private String code; // SAV
    private String name; // Savings
    
    public AccountType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
