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
      Statement Stmt = null;
      Statement deleteStmt = null;
      PreparedStatement insertStmt = null;
      String sql  = "";
      int resultCode; 
      
      try {
          Class.forName(driver);
          con = DriverManager.getConnection(url, user, pass);
          
          sql = "UPDATE customer SET EMAIL = ? WHERE ID  = ? ";
          
          insertStmt = con.prepareStatement(sql);
          insertStmt.setString(1,"Jane.Johnson@abc.com");
          insertStmt.setInt(2,2001);
              //update email for customer with id=2001
          resultCode = insertStmt.executeUpdate();
          System.out.println("Result code: " + resultCode);
          
              //add new customer_1
          sql = "INSERT INTO address " +
              "(id, addr1, city, state, zip) " +
              "VALUES (?, ?, ?, ?, ?)";
          insertStmt = con.prepareStatement(sql);
          insertStmt.setInt(1, 1005);
          insertStmt.setString(2, "4342 minnesota rd");
          insertStmt.setString(3, "Shakopee");
          insertStmt.setString(4, "MN");
          insertStmt.setInt(5, 55379);
          resultCode = insertStmt.executeUpdate();
          System.out.println("Result code: " + resultCode);
          
          sql = "INSERT INTO customer " +
                  "(id, lname, fname, bill_addr) " +
                  "VALUES (?, ?, ?, ?)";
          insertStmt = con.prepareStatement(sql);
          insertStmt.setInt(1, 2006);
          insertStmt.setString(2, "Romeo");
          insertStmt.setString(3, "Mai");
          insertStmt.setInt(4, 1005);
          resultCode = insertStmt.executeUpdate();
          System.out.println("Result code: " + resultCode);
          
              //add new customer_2
          Stmt = con.createStatement();
          resultCode = Stmt.executeUpdate(
              "INSERT INTO address " +
              "(id, addr1, city, state, zip) " +
              "VALUES ('1006', '2323 shakopee cir', 'Shakopee', 'MN', '55379')"
          );
          System.out.println("Result code: " + resultCode);

          resultCode = Stmt.executeUpdate(
              "INSERT INTO customer " +
              "(id, lname, fname, bill_addr) " +
              "VALUES ('2007', 'Fake' , 'Romeo', '1006')"
          );
          System.out.println("Result code: " + resultCode);

              //delete new customer_2
          String deleteSQL = "DELETE FROM customer " +
                             "WHERE id = 2007";
          deleteStmt = con.createStatement();
          int deleteCount = deleteStmt.executeUpdate(deleteSQL);
          System.out.println(deleteCount + " rows deleted from customer");

          
          insertStmt.close();
          Stmt.close();
          deleteStmt.close();
          con.close();
      } catch ( ClassNotFoundException x) {
          x.printStackTrace();
      } catch ( SQLException x) {
          x.printStackTrace();
      }

    }

}