package com.abc.bank.dto;

import java.io.*;
import java.util.*;

import com.programix.util.*;

public class Customer implements Serializable {
    public static final Customer[] ZERO_LEN_ARRAY = new Customer[0];
    
    private CustomerId id;
    private String firstName;
    private String lastName;
    private Address mailingAddress;
    
    public Customer() {
    }

    public CustomerId getId() {
        return id;
    }

    public void setId(CustomerId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
    
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        } else if ( obj == null || !getClass().equals(obj.getClass()) ) {
            return false;
        }

        Customer other = (Customer) obj;
        return ObjectTools.isSame(id, other.id) &&
               ObjectTools.isSame(firstName, other.firstName) &&
               ObjectTools.isSame(lastName, other.lastName) &&
               ObjectTools.isSame(mailingAddress, other.mailingAddress);
    }

    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getName() + "[");
        sb.append("id=" + id);
        sb.append(", firstName=" + firstName);
        sb.append(", lastName=" + lastName);
        sb.append(", mailingAddress=" + mailingAddress);
        sb.append("]");
        return sb.toString();
    }
    
    public static Customer[] toArray(Collection<Customer> customers) {
        return customers.toArray(ZERO_LEN_ARRAY);
    }
}
