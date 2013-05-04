package com.abc.bank.demo;

import com.abc.bank.da.*;
import com.abc.bank.dto.*;
import com.programix.da2.*;
import com.programix.da2.exception.*;
import com.programix.value.*;

public class Demo {
    private static DASource createDaSource() {
        ValueMap config = ValueMap.createFromFile("src/sql/hsql.properties");

        config.put(DAFactory.DA_IMPLEMENTATION_CLASSNAME, 
            "com.abc.bank.da.jdbc.BankDAJDBCImpl");

        ConfigDASource source = new ConfigDASource();
        source.addConfig("com.abc.bank.da.BankDA", config);
        return source;
    }
    
    private static void loadStates(BankDA da) {
        try {
            State s1 = da.saveState(createStateDto("MN", "Minnesota"));
            System.out.println("s1=" + s1);
            da.saveState(createStateDto("NJ", "New Jersey"));
            State ak = da.saveState(createStateDto("AK", "Alasssssska"));
            State[] states = da.getSortedStates();
            if ( states == null ) {
                System.out.println("states=null");
            } else {
                System.out.println("states.length=" + states.length);
                for ( int i = 0; i < states.length; i++ ) {
                    System.out.println("  states[" + i + "]=" + states[i]);
                }
            }
            ak.setName("Alaska");
            State s2 = da.saveState(ak);
            System.out.println("s2=" + s2);
            states = da.getSortedStates();
            if ( states == null ) {
                System.out.println("states=null");
            } else {
                System.out.println("states.length=" + states.length);
                for ( int i = 0; i < states.length; i++ ) {
                    System.out.println("  states[" + i + "]=" + states[i]);
                }
            }
        } catch ( DAException x ) {
            System.err.println("Failed to load all the states: " + x);
        }
    }
    
    private static State createStateDto(String code, String name) {
        State s = new State();
        s.setCode(code);
        s.setName(name);
        return s;
    }
    
    public static void main(String[] args) {
        try {
            DASource daSource = createDaSource();
            BankDA bda = daSource.getDA(BankDA.class);
            loadStates(bda);
        } catch ( DAException x ) {
            x.printStackTrace();
        }
    }
}
