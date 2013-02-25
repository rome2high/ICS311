package com.abc.jdbc.update;
import java.sql.*;

public class Update {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
      System.out.println("damn blue angle");
      String driver = "org.hsqldb.jdbcDriver";
      String url = "jdbc:hsqldb:hsql://localhost:3500";
      String user = "sa";
      String pass = "";
      
      try {
          Class.forName(driver);
          System.out.println("load the driver!");
          Connection con = DriverManager.getConnection(url, user, pass);
          Statement stmt = con.createStatement();
          int result = stmt.executeUpdate(
              "INSERT INTO customer " +
              "(id, lname, fname) " +
              "VALUES ('2323', 'jr' , 'Mai')"
          );
          System.out.println(result);
          stmt.close();
          con.close();
      } catch ( ClassNotFoundException x) {
          x.printStackTrace();
      } catch ( SQLException x) {
          x.printStackTrace();
      }

    }

}