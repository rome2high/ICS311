package com.abc.bank.dto;

import java.io.*;
import java.util.*;

public final class CustomerId implements Serializable {
    public static final CustomerId[] ZERO_LEN_ARRAY = new CustomerId[0];
    
    private final int id;

    public CustomerId(int id) {
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

        CustomerId other = (CustomerId) obj;
        return id == other.id;
    }

    public int hashCode() {
        return id;
    }

    public String toString() {
        return String.valueOf(id);
    }
    
    public static CustomerId[] toArray(Collection<CustomerId> ids) {
        return ids.toArray(ZERO_LEN_ARRAY);
    }
}
