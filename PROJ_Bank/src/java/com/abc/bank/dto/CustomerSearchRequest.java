package com.abc.bank.dto;

import java.io.*;

public class CustomerSearchRequest implements Serializable {
    private String partialLastName;
    private String partialFirstName;
    private String partialCity;
    private State state;
    private int maxMatchCount;
}
