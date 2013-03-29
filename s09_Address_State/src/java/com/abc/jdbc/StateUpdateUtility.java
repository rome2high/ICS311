package com.abc.jdbc;

import java.sql.*;

import com.programix.sql.*;

public class StateUpdateUtility {
    // sloppy with exceptions!
    private static Connection createConnection() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        return DriverManager.getConnection(
            "jdbc:hsqldb:hsql://localhost:3500", 
            "sa", 
            "");
    }

    public static void main(String[] args) throws Exception {
        int id = 9999;
        String cityName = "Saint Paul";
        Connection con = createConnection();
        
        PreparedStatement stmt = null;
        try {
            String sql = 
                " UPDATE address " +
                " SET city = ? " +
                " WHERE id = ? ";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cityName);
            stmt.setInt(2, id);
            
            int rowCount = stmt.executeUpdate();
            if ( rowCount != 1 ) {
                throw new SQLException("Yikes - no row!");
            }
        } finally {
            JDBCTools.closeQuietly(stmt);
            JDBCTools.closeQuietly(con);
        }
        
    }
}
