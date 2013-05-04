package com.abc.bank.da;

import java.math.*;

import com.abc.bank.dto.*;
import com.programix.da2.*;
import com.programix.da2.exception.*;

public interface BankDA extends GenericDA {
    Customer getCustomer(CustomerId id) throws NotFoundDAException, DAException;
    
    // if no id, generate a unique id, otherwise updating existing
    Customer saveCustomer(Customer customer) 
        throws NotFoundDAException, DAException;
    
    CustomerSearchResponse findCustomers(CustomerSearchRequest req) 
        throws DAException;
    
    Account getAccount(AccountId id) throws NotFoundDAException, DAException;

    // if no id, generate a unique id, otherwise updating existing
    Account saveAccount(Account account) 
        throws NotFoundDAException, DAException;

    // zero-length array is none, NotFoundException is for missing customerId
    Account[] getAccounts(CustomerId id) 
        throws NotFoundDAException, DAException; 
    
    AccountTransferResponse transfer(BigDecimal amount, 
                                     AccountId sourceId, 
                                     AccountId destinationId) 
        throws InsufficientFundsException, NotFoundDAException, DAException;
    

    // return all account types sorted by state code 
    State[] getSortedStates() throws DAException;
    
    // if no id, generate a unique id, otherwise updating existing
    State saveState(State state) throws NotFoundDAException, DAException;
    
    // return all account types sorted by type code 
    AccountType[] getSortedAccountTypes() throws DAException;
    
    // if no id, generate a unique id, otherwise updating existing
    AccountType saveAccountType(AccountType type) 
        throws NotFoundDAException, DAException;
}
