package db.databaseTableGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

import Config.MySQL;


/**
 * This class directly interact with database by opening and closing connection. 
 * @author Moon Yin
 *
 */
public class DB {
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	
	/**
	 * This function prepares interaction with database by setting up connection and prepared statement. 
	 * @param query_in
	 * @return
	 */
	public static PreparedStatement prepare(String query_in){
		PreparedStatement pstmt=null;
		
		try {
			Class.forName(Driver);
			Connection Conn=DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			pstmt = (PreparedStatement) Conn.prepareStatement(query_in);
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * This function close up connection. 
	 * @param pstmt
	 */
	public static void cleanUP(PreparedStatement pstmt) {
		// TODO Auto-generated method stub
		try {
			pstmt.getConnection().close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * prepare function , return newly generated ID, interaction with database by setting up connection and prepared statement. 
	 * @param query_in
	 * @return
	 */
	public static PreparedStatement prepareReturnKey(String query_in){
		PreparedStatement pstmt=null;
		
		try {
			Class.forName(Driver);
			Connection Conn=DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			pstmt = (PreparedStatement) Conn.prepareStatement(query_in,Statement.RETURN_GENERATED_KEYS);
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return pstmt;
	}
		/**
		 * prepare function for CallableStatement 
		 * @param query_in
		 * @return
		 */
	public static CallableStatement prepareCall(String query_in){
		CallableStatement cs=null;
		
		try {
			Class.forName(Driver);
			Connection Conn=DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			cs=(CallableStatement) Conn.prepareCall(query_in);
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}
	
	/**
	 * clean up function for CallableStatement
	 * @param pstmt
	 */
	public static void cleanUpPrepareCall(CallableStatement pstmt) {
		// TODO Auto-generated method stub
		try {
			pstmt.getConnection().close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
