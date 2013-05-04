package com.abc.bank.dto;

import java.io.*;

public class Customer implements Serializable {
    private CustomerId id;
    private String firstName;
    private String lastName;
    private Address mailingAddress;
}
