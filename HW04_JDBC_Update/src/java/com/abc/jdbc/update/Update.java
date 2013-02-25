package com.abc.jdbc.update;
import java.sql.*;

public class Update {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
      String driver = "org.hsqldb.jdbcDriver";
      String url = "jdbc:hsqldb:hsql://localhost:3500";
      String user = "sa";
      String pass = "";
      
      Connection con = null;
      Statement deleteStmt = null;
      PreparedStatement insertStmt = null;
      String sql  = "";
      
      try {
          Class.forName(driver);
          con = DriverManager.getConnection(url, user, pass);
          
          sql = "UPDATE customer SET EMAIL = ? WHERE ID  = ? ";
          
          insertStmt = con.prepareStatement(sql);
          insertStmt.setString(1,"Jane.Johnson@abc.com");
          insertStmt.setInt(2,2001);
          
          System.out.println("About to execute: " + sql);
          int resultCode = insertStmt.executeUpdate();
          System.out.println("Result code: " + resultCode);


//          int result = stmt.executeUpdate(
//              "INSERT INTO customer " +
//              "(id, lname, fname) " +
//              "VALUES ('2323', 'Romeo' , 'Mai')"
//          );
//          System.out.println(result);

          insertStmt.close();
          deleteStmt.close();
          con.close();
      } catch ( ClassNotFoundException x) {
          x.printStackTrace();
      } catch ( SQLException x) {
          x.printStackTrace();
      }

    }

}