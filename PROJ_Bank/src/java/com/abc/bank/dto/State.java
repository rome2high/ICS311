package com.abc.bank.dto;

import java.io.*;

public class State implements Serializable {
    private int id;
    private String code; // MN
    private String name; // Minnesota
    
    public State() {
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
