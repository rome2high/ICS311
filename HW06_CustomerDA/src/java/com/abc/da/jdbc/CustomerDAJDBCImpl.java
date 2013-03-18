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
    	
    	ResultSet rs = null;
    	Customer tmpCust = new Customer();
    	PreparedStatement selectStmt;
		String sql= "SELECT * FROM customer where id = ?";
		
		String colName = "";
		String rowVal = "";
        
        Connection con = null;
        try {
            con = createConnection();

            // FIXME ================================================
            selectStmt = con.prepareStatement(sql);
            selectStmt.setInt(1, id);
            rs = selectStmt.executeQuery();
            
            while(rs.next()){
	            for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
	            	colName = rs.getMetaData().getColumnName(i).toUpperCase();
	            	rowVal = rs.getString(i);
	            	
	            	if(colName.equals("ID"))
	            		tmpCust.setId(Integer.parseInt(rowVal));
	            	else if(colName.equals("LNAME"))
	            		tmpCust.setLastName(rowVal);
	            	else if(colName.equals("FNAME"))
	            		tmpCust.setFirstName(rowVal);
	            	else if(colName.equals("EMAIL"))
	            		tmpCust.setEmail(rowVal);
	            	}
            }
			selectStmt.close();
            return tmpCust;
        } catch ( SQLException x ) {
            throw new DAException(x);
        } finally {
            JDBCTools.closeQuietly(con);
        }
    }
    
    @Override
    public Customer saveCustomer(Customer customer) 
            throws NotFoundDAException, DAException {

    	if(customer.getId() == null)
        	return null;
    	
    	Customer tmpCust = customer;
    	PreparedStatement updateStmt = null;
    	int resultCode = 0;
		String sql= "UPDATE customer SET" +
					" lname=?" +
					", fname=?" +
					", email=?" +
					" where id=?";
		
        Connection con = null;
        
        try {
            con = createConnection();
            
            // FIXME ================================================
            updateStmt = con.prepareStatement(sql);
            updateStmt.setString(1,tmpCust.getLastName());
            updateStmt.setString(2, tmpCust.getFirstName());
            updateStmt.setString(3, tmpCust.getEmail());
            updateStmt.setInt(4, tmpCust.getId());
            resultCode = updateStmt.executeUpdate();
            
            System.out.println("Successfully update record " + resultCode);
            updateStmt.close();
            return tmpCust;
        } catch ( SQLException x ) {
            throw new DAException(x);
        } finally {
            JDBCTools.closeQuietly(con);
        }
    }
}
