package com.abc.da;

import com.abc.dto.*;
import com.programix.da2.*;
import com.programix.da2.exception.*;

public interface CustomerDA extends GenericDA {
    Customer getCustomer(int id) throws NotFoundDAException, DAException;
    
    Customer saveCustomer(Customer customer) 
        throws NotFoundDAException, DAException;
}
