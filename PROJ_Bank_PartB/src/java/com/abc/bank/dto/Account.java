package com.abc.bank.dto;

import java.io.*;
import java.math.*;
import java.util.*;

import com.programix.util.*;

public class Account implements Serializable {
    public static final Account[] ZERO_LEN_ARRAY = new Account[0];

    private AccountId id;
    private AccountType type;
    private String account_number;
    private BigDecimal balance;
    private CustomerId[] owners; // *always* at least one
    
    public Account() {
    }

    public AccountId getId() {
        return id;
    }

    public void setId(AccountId id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CustomerId[] getOwners() {
        return owners;
    }

    public void setOwners(CustomerId[] owners) {
        this.owners = owners;
    }
    
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        } else if ( obj == null || !getClass().equals(obj.getClass()) ) {
            return false;
        }

        Account other = (Account) obj;
        return ObjectTools.isSame(id, other.id) &&
               ObjectTools.isSame(type, other.type) &&
               ObjectTools.isSame(account_number, other.account_number) &&
               ObjectTools.isSame(balance, other.balance) &&
               ObjectTools.isSameArray(owners, owners);
    }

    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + "[");
        sb.append("id=" + id);
        sb.append(", type=" + type);
        sb.append(", account_number=" + account_number);
        sb.append(", balance=" + balance);
        sb.append(", owners=");
        if ( ObjectTools.isEmpty(owners) ) {
            sb.append("none");
        } else {
            for ( int i = 0; i < owners.length; i++ ) {
                if ( i > 0 ) {
                    sb.append(", ");
                }
                sb.append(owners[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static Account[] toArray(Collection<Account> accounts) {
        return accounts.toArray(ZERO_LEN_ARRAY);
    }
}    
