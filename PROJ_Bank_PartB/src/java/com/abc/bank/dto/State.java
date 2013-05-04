package com.abc.bank.dto;

import java.io.*;
import java.util.*;

import com.programix.util.*;

public class State implements Serializable {
    public static final State[] ZERO_LEN_ARRAY = new State[0];

    private Integer id;
    private String code; // MN
    private String name; // Minnesota
    
    public State() {
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
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

    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        } else if ( obj == null || !getClass().equals(obj.getClass()) ) {
            return false;
        }

        State other = (State) obj;
        return ObjectTools.isSame(id, other.id) &&
               ObjectTools.isSame(code, other.code) &&
               ObjectTools.isSame(name, other.name);
    }

    public int hashCode() {
        return id != null ? id : 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + "[");
        sb.append("id=" + id);
        sb.append(", code=" + code);
        sb.append(", name=" + name);
        sb.append("]");
        return sb.toString();
    }

    public static State[] toArray(Collection<State> states) {
        return states.toArray(ZERO_LEN_ARRAY);
    }
}
