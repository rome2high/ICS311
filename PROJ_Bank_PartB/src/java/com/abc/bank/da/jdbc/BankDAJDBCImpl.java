package com.abc.bank.da.jdbc;

import java.math.*;
import java.sql.*;
import java.util.*;

import com.abc.bank.da.*;
import com.abc.bank.dto.*;
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
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public Customer saveCustomer(Customer customer) 
            throws NotFoundDAException, DAException {
        
        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
    }

    @Override
    public CustomerSearchResponse findCustomers(CustomerSearchRequest req)
            throws DAException {
        
        // FIXME - remove this exception and write the real implementation
        throw new DAException("FIXME: not implemented yet!");
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
