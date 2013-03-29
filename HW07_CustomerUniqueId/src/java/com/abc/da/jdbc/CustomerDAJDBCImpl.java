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
    
    private Customer getCustomer(int id, Connection con) 
            throws NotFoundDAException, SQLException {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = 
                " SELECT lname, fname, email " +
                " FROM customer " +
                " WHERE id = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if ( !rs.next() ) {
                throw new NotFoundDAException("customer", id);
            }
            Customer c = new Customer();
            c.setId(id);
            c.setLastName(rs.getString(1));
            c.setFirstName(rs.getString(2));
            c.setEmail(rs.getString(3));
            return c;
        } finally {
            JDBCTools.closeQuietly(rs);
            JDBCTools.closeQuietly(stmt);
        }
    }

    @Override
    public Customer getCustomer(int id) 
        throws NotFoundDAException, DAException {
        
        Connection con = null;
        try {
            con = createConnection();
            return getCustomer(id, con);
        } catch ( SQLException x ) {
            throw new DAException(x);
        } finally {
            JDBCTools.closeQuietly(con);
        }
    }
    
    private int saveCustomer(Customer customer, Connection con) 
            throws NotFoundDAException, SQLException {

        PreparedStatement stmt = null;
        try {
            if ( customer.getId() == null ) {
                // new customer, generate a unique customer ID
                
                // FIXME - replace this exception with code to add a new
                // customer to the database with a newly generated id
                throw new SQLException("" +
                		"adding new customers not YET supported");
            } else {
                // existing customer, update their information
                int id = customer.getId();
                String sql = 
                    " UPDATE customer " +
                        " SET lname = ?, fname = ?, email = ? " +
                        " WHERE id = ? ";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, customer.getLastName());
                stmt.setString(2, customer.getFirstName());
                stmt.setString(3, customer.getEmail());
                stmt.setInt(4, id);
                int rowCount = stmt.executeUpdate();
                if ( rowCount == 0 ) {
                    throw new NotFoundDAException("customer", id);
                }
                return id;
            }
        } finally {
            JDBCTools.closeQuietly(stmt);
        }
    }
    
    @Override
    public Customer saveCustomer(Customer customer) 
            throws NotFoundDAException, DAException {

        Connection con = null;
        boolean success = false;
        try {
            con = createConnection();
            con.setAutoCommit(false);
            int id = saveCustomer(customer, con);
            Customer savedCustomer = getCustomer(id, con);
            con.commit();
            success = true;
            return savedCustomer;
        } catch ( SQLException x ) {
            throw new DAException(x);
        } finally {
            rollbackIfUnsuccessful(con, success);
            // no need to set autocommit back, we're closing it
            JDBCTools.closeQuietly(con);
        }
    }
    
    private static void rollbackIfUnsuccessful(Connection con, 
                                               boolean success) {
        
        if ( con != null ) {
            if ( !success ) {
                try {
                    con.rollback();
                } catch ( SQLException x ) {
                    // ignore, we made our best attempt
                }
            }
        }
    }
}
