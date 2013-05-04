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
    
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        } else if ( obj == null || !getClass().equals(obj.getClass()) ) {
            return false;
        }

        AccountId other = (AccountId) obj;
        return id == other.id;
    }

    public int hashCode() {
        return id;
    }

    public String toString() {
        return String.valueOf(id);
    }
}
