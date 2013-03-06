package com.abc.jdbc.query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * project:	ICS311- HW05
 * description:	connect and retrieve data from database using JDBC
 * author:	Romeo Mai
 */
public class Select {

	public static void main(String[] args) {

		Connection con = null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;

		String colName = "";
		String outputStr = "";
		int colCount = 0;
		int rowCount = 0; 

			try {
				//create a connection to database
				con = dbConnect(con);
				rs = selectCustStartwithJ(con, rs);
				meta = rs.getMetaData();
		
				colCount = meta.getColumnCount();
		
					//for each column, print its name and type
				for(int i = 1; i <= colCount; i++){
					if(i!=1)colName += "-----";
					colName += meta.getColumnName(i) + "(" + meta.getColumnTypeName(i) + ")";
				}
				System.out.println(colName);
		
					//for each row, print data of its columns
				while (rs.next()) {
					for(int j = 1; j <= colCount; j++){
						if(j !=1) outputStr += "-----";
						outputStr += rs.getString(j);
					}
					outputStr += "\n";
					rowCount++;
				}
				System.out.println(outputStr);
				
					//print total number of rows retrieved
				System.out.println("Total number of rows retrieved: " + rowCount);
		
				dbClose(con);
			} catch ( ClassNotFoundException x) {
				x.printStackTrace();
			} catch ( SQLException x) {
				x.printStackTrace();
			}
	}

	private static ResultSet selectCustStartwithJ(Connection con, ResultSet rs) {

			//use preparedStatement to select customers
		PreparedStatement selectStmt;
		String sql= "SELECT * FROM customer where fName LIKE 'J%' ";

		try {
			selectStmt = con.prepareStatement(sql);
			rs = selectStmt.executeQuery();
			selectStmt.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Connection dbConnect(Connection con) throws ClassNotFoundException, SQLException {

		String driver = "org.hsqldb.jdbcDriver";
		String url = "jdbc:hsqldb:hsql://localhost:3500";
		String user = "sa";
		String pass = "";

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		return con;
	}

	private static void dbClose(Connection con) {
		try {
			con.close();
			System.out.println("Successfully close connections");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
