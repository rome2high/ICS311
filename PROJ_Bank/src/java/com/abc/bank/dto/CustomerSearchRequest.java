package com.abc.bank.dto;

import java.io.*;

public class CustomerSearchRequest implements Serializable {
    private String partialLastName;
    private String partialFirstName;
    private String partialCity;
    private State state;
    private int maxMatchCount;
    
    public CustomerSearchRequest() {
    }

    public String getPartialLastName() {
        return partialLastName;
    }

    public void setPartialLastName(String partialLastName) {
        this.partialLastName = partialLastName;
    }

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        this.partialFirstName = partialFirstName;
    }

    public String getPartialCity() {
        return partialCity;
    }

    public void setPartialCity(String partialCity) {
        this.partialCity = partialCity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getMaxMatchCount() {
        return maxMatchCount;
    }

    public void setMaxMatchCount(int maxMatchCount) {
        this.maxMatchCount = maxMatchCount;
    }
}
