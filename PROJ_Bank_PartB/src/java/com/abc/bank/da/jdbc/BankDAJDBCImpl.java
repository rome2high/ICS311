package com.abc.bank.da.jdbc;

import java.math.*;
import java.sql.*;
import java.util.*;

import com.abc.bank.da.*;
import com.abc.bank.dto.*;
import com.programix.da2.DASource;
import com.programix.da2.exception.*;
import com.programix.sql.*;
import com.programix.value.*;

public class BankDAJDBCImpl implements BankDA {
    private ConnectionSource connectionSource;
    private UniqueIDGenerator idGenerator;

    public BankDAJDBCImpl() {
    }
    
    @Override
    public void init(ValueMap config) throws DAException {
        try {
            connectionSource = new DriverManagerConnectionSource(config);
            idGenerator = new UniqueIDGenerator(connectionSource, config);
        } catch ( SQLException x ) {
            throw new DAException(x);
        }
    }

    @Override
    public void shutdown() {
        connectionSource.shutdown();
    }
    
    private DAException convertException(Throwable t) {
        return t instanceof DAException 
            ? (DAException) t : new DAException(t);
    }

    @Override
    public Customer getCustomer(CustomerId id) 
            throws NotFoundDAException, DAException {
        
        // FIXME - remove this exception and write the real implementation
//        throw new DAException("FIXME: not implemented yet!");
    	TxCon tcon = new TxCon();
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = 
                " SELECT c.id, c.first_name, c.last_name, a.street, a.city, s.id, s.code, s.name, a.zip" +
                " FROM customer c" +
                " JOIN address a on c.mailing_address = a.id" +
                " JOIN state s on a.state_id = s.id" +
                " WHERE id = ?";
            stmt = tcon.prepareStatement(sql);
            stmt.setInt(1, id.getId());
            rs = stmt.executeQuery();
            if ( !rs.next() ) {
                throw new NotFoundDAException("customer", id);
            }
            Customer c = new Customer();
            c.setId(id);
            c.setFirstName(rs.getString(2));
            c.setLastName(rs.getString(3));
            
            Address a = new Address();			//need to define a
            a.setStreet(rs.getString(4));
            a.setCity(rs.getString(5));
            
            State s = new State();
            s.setId(rs.getInt(6));
            s.setCode(rs.getString(7));
            s.setName(rs.getString(8));
            
            a.setState(s);            
            a.setZip(rs.getString(9));
            
            
            c.setMailingAddress(a);
            return c;
        } catch (SQLException e) {
			// Need to fix this
			e.printStackTrace();
			return null;
		} finally {
            JDBCTools.closeQuietly(rs);
            JDBCTools.closeQuietly(stmt);
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) 
            throws NotFoundDAException, DAException {
        
        // FIXME - remove this exception and write the real implementation
//        throw new DAException("FIXME: not implemented yet!");
    	TxCon tcon = new TxCon();
//    	Connection con = tcon.rawConnection;
    	Customer c = customer;
        PreparedStatement stmt = null;
        Statement stmtLock = null;
        
        try {
            if ( customer.getId() == null ) {
                // new customer, generate a unique customer ID
                
                // FIXME - replace this exception with code to add a new
                // customer to the database with a newly generated id
            	
            	String tablename = "customer";
            	
            	String sqlLock = "UPDATE id_list " +
            					 "SET last_id = last_id + 1 " +
            					 "WHERE name = '" + tablename + "'";
//            	stmtLock = con.createStatement();
            	tcon.createRawConnectionIfNeeded();
            	stmtLock = tcon.rawConnection.createStatement();
            	stmtLock.executeUpdate(sqlLock);	//creates exclusive lock
            	
            	String sqlSelect = "SELECT last_id " +
            					   "FROM id_list " +
            					   "WHERE name = '" + tablename + "'";
            	ResultSet rs = stmtLock.executeQuery(sqlSelect);
            	
            	if ( rs.next() == false ) {
                    // trouble...throw exception
            		throw new NotFoundDAException("Unable creating new id");
                }
                int id = rs.getInt(1); 
                // complete transaction
                String sql = 
                        "INSERT INTO customer (id, lname, fname, email)" +
                            " VALUES (?, ?, ?, ?);";
                    stmt = tcon.prepareStatement(sql);
                    stmt.setInt(1, id);
                    stmt.setString(2, customer.getLastName());
                    stmt.setString(3, customer.getFirstName());
//                    stmt.setString(4, customer.getEmail());
                    
                    int rowCount = stmt.executeUpdate();
                    if ( rowCount == 0 ) {
                        throw new NotFoundDAException("Insert Failed");
                    }
                return c;
                
            } else {
                // existing customer, update their information
//                int id = customer.getId();
                String sql = 
                    " UPDATE customer " +
                        " SET lname = ?, fname = ?, email = ? " +
                        " WHERE id = ? ";
                stmt = tcon.prepareStatement(sql);
                stmt.setString(1, customer.getLastName());
                stmt.setString(2, customer.getFirstName());
//                stmt.setString(3, customer.getEmail());
                stmt.setInt(4, c.getId().getId());			//something fishy here
                int rowCount = stmt.executeUpdate();
                if ( rowCount == 0 ) {
                    throw new NotFoundDAException("customer", c.getId());
                }
                return c;
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
        	JDBCTools.closeQuietly(stmtLock);
            JDBCTools.closeQuietly(stmt);
        }        
    }

    @Override
    public CustomerSearchResponse findCustomers(CustomerSearchRequest req)
            throws DAException {
        
        // FIXME - remove this exception and write the real implementation
        //throw new DAException("FIXME: not implemented yet!");
    	CustomerSearchResponse csResponse = new CustomerSearchResponse();
    	TxCon tcon = new TxCon();
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = 
                " SELECT lname, fname, email " +
                " FROM customer " +
                " WHERE id = ?";
            stmt = tcon.prepareStatement(sql);
            stmt.setString(1, req.getPartialFirstName());
            rs = stmt.executeQuery();
            if ( !rs.next() ) {
                throw new NotFoundDAException("customer", req.getPartialLastName());
            }
            Customer c = new Customer();
            Address a = new Address();			//need to define a
            CustomerId cId = new CustomerId(rs.getInt(1));
            c.setId(cId);
            c.setLastName(rs.getString(2));
            c.setFirstName(rs.getString(3));
            c.setMailingAddress(a);
            return csResponse;		//array of customers
        } catch (SQLException e) {
			// Need to fix this
			e.printStackTrace();
			return null;
		} finally {
            JDBCTools.closeQuietly(rs);
            JDBCTools.closeQuietly(stmt);
        }
    }

    @Override
    public Account getAccount(AccountId id) 
            throws NotFoundDAException, DAException {

        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public Account saveAccount(Account account) 
            throws NotFoundDAException, DAException {
        
        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public Account[] getAccounts(CustomerId id) 
            throws NotFoundDAException, DAException {

        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public AccountTransferResponse transfer(BigDecimal amount,
                                            AccountId sourceId,
                                            AccountId destinationId)
            throws InsufficientFundsException, 
                   NotFoundDAException, 
                   DAException {
        
        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public AccountType[] getSortedAccountTypes() throws DAException {
        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public AccountType saveAccountType(AccountType type)
            throws NotFoundDAException, DAException {

        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public State[] getSortedStates() throws DAException {
        TxCon tcon = new TxCon();
        try {
            State[] sortedStates = getSortedStates(tcon);
            tcon.commit();
            return sortedStates;
        } catch ( Exception x ) {
            throw convertException(x);
        } finally {
            tcon.rollbackIfNeededAndClose();
        }
    }
    
    private State[] getSortedStates(TxCon tcon) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = tcon.prepareStatement(
                " SELECT id, code, name " +
                " FROM state " +
                " ORDER BY code ");
            
            return getStates(stmt.executeQuery());
        } finally {
            JDBCTools.closeQuietly(stmt);
        }
    }
    
    // closes rs, expects: id, code, name column order
    private State[] getStates(ResultSet rs) throws SQLException {
        try {
            List<State> stateList = new ArrayList<State>();
            while ( rs.next() ) {
                State state = new State();
                state.setId(rs.getInt(1));
                state.setCode(rs.getString(2));
                state.setName(rs.getString(3));
                stateList.add(state);
            }
            return State.toArray(stateList);
        } finally {
            JDBCTools.closeQuietly(rs);
        }
    }
    
    private State getState(int id, TxCon tcon) 
            throws NotFoundDAException, SQLException {
        
        PreparedStatement stmt = null;
        try {
            stmt = tcon.prepareStatement(
                " SELECT id, code, name " +
                " FROM state " +
                " WHERE id = ? ");
            
            stmt.setInt(1, id);
            State[] states = getStates(stmt.executeQuery());
            if ( states.length == 0 ) {
                throw new NotFoundDAException("state", id);
            }
            return states[0];
        } finally {
            JDBCTools.closeQuietly(stmt);
        }
    }

    @Override
    public State saveState(State state) 
            throws NotFoundDAException, DAException {

        TxCon tcon = new TxCon();
        try {
            int id = saveState(state, tcon);
            State savedState = getState(id, tcon);
            tcon.commit();
            return savedState;
        } catch ( Exception x ) {
            throw convertException(x);
        } finally {
            tcon.rollbackIfNeededAndClose();
        }
    }
    
    private int saveState(State state, TxCon tcon)
            throws NotFoundDAException, SQLException {
        
        PreparedStatement stmt = null;
        try {
            boolean update = state.getId() != null;
            int id = update ? state.getId() : 
                idGenerator.generateUniqueID("state");
            
            String sql = null;
            if ( update ) {
                sql = " UPDATE state " +
                      " SET code = ?, name = ? " +
                      " WHERE id = ? ";
            } else {
                sql = 
                    " INSERT INTO state (code, name, id) " +
                    " VALUES (?, ?, ?) ";
            }
            
            stmt = tcon.prepareStatement(sql);
            stmt.setString(1, state.getCode());
            stmt.setString(2, state.getName());
            stmt.setInt(3, id);
            int rowCount = stmt.executeUpdate();
            if ( update && rowCount != 1 ) {
                throw new NotFoundDAException("state", id);
            }
            return id;
        } finally {
            JDBCTools.closeQuietly(stmt);
        }
    }
    
    private class TxCon {
        private Connection rawConnection;
        private boolean hasConnection = false;
        private boolean success = false;
        private boolean closed = false;
        
        private void createRawConnectionIfNeeded() throws SQLException {
            if ( !hasConnection ) {
                rawConnection = connectionSource.getConnection();
                rawConnection.setAutoCommit(false);
                hasConnection = true;
            }
        }
        
        public void commit() throws SQLException {
            if ( !closed && hasConnection ) {
                rawConnection.commit();
                success = true; // if we get here without exception
            }
        }
        
        // does NOT throw an exception
        public void rollbackIfNeededAndClose() {
            if ( !closed && hasConnection ) {
                if ( !success ) {
                    try {
                        rawConnection.rollback();
                    } catch ( Exception x ) {
                        // ignore, we did our best
                    }
                }
                
                try {
                    rawConnection.close();
                } catch ( Exception x ) {
                    // ignore, we did our best
                }
                
                closed = true;
            }
        }
        
        // this prepared statement needs closing when done with it
        public PreparedStatement prepareStatement(String sql) 
                throws SQLException {
            
            createRawConnectionIfNeeded();
            return rawConnection.prepareStatement(sql);
        }
    } // class TransCon
}
