package com.abc.da.jdbc;

import java.sql.*;

import com.abc.da.*;
import com.abc.dto.*;
import com.programix.da2.exception.*;
import com.programix.sql.*;
import com.programix.value.*;

public class CustomerDAJDBCImpl implements CustomerDA {
    private String url;
    private String user;
    private String pass;
    
    public CustomerDAJDBCImpl() {
    }
    
    @Override
    public void init(ValueMap config) throws DAException {
        try {
            String classname = config.getString("jdbc.driver");
            Class.forName(classname);
            url = config.getString("jdbc.url");
            user = config.getString("jdbc.user");
            pass = config.getString("jdbc.pass");
        } catch ( ClassNotFoundException x ) {
            throw new DAException(x);
        }
    }
    
    @Override
    public void shutdown() {
        // nothing to do for this particular implementation
    }
    
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
    
    @Override
    public Customer getCustomer(int id) 
        throws NotFoundDAException, DAException {
        
        Connection con = null;
        try {
            con = createConnection();
            
            // FIXME ================================================
            return null;
        } catch ( SQLException x ) {
            throw new DAException(x);
        } finally {
            JDBCTools.closeQuietly(con);
        }
    }
    
    @Override
    public Customer saveCustomer(Customer customer) 
            throws NotFoundDAException, DAException {

        Connection con = null;
        try {
            con = createConnection();
            
            // FIXME ================================================
            return null;
        } catch ( SQLException x ) {
            throw new DAException(x);
        } finally {
            JDBCTools.closeQuietly(con);
        }
    }
}
