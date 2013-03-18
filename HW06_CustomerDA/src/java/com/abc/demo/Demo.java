package com.abc.demo;

import com.abc.da.*;
import com.abc.dto.*;
import com.programix.da2.*;
import com.programix.da2.exception.*;
import com.programix.value.*;

public final class Demo {
    private static DASource createDaSource() {
        ConfigDASource source = new ConfigDASource();
        
        ValueMap config = new ValueMap();
        config.put(DAFactory.DA_IMPLEMENTATION_CLASSNAME, 
            "com.abc.da.jdbc.CustomerDAJDBCImpl");
        config.put("jdbc.driver", "org.hsqldb.jdbcDriver");
        config.put("jdbc.url", "jdbc:hsqldb:hsql://localhost:3500");
        config.put("jdbc.user", "sa");
        config.put("jdbc.pass", "");
        
        source.addConfig("com.abc.da.CustomerDA", config);
        
        return source;
    }
    
    public static void main(String[] args) {
        try {
            DASource daSource = createDaSource();
            CustomerDA cda = daSource.getDA(CustomerDA.class);
            
            Customer c1 = cda.getCustomer(2005);
            System.out.println("c1=" + c1);
            
            c1.setEmail("someone@somewhere.com");
            Customer c2 = cda.saveCustomer(c1);
            System.out.println("c2=" + c2);
        } catch ( DAException x ) {
            x.printStackTrace();
        }
    }
}
