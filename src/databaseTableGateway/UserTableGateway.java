package databaseTableGateway;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import mysqlConfig.MySQL;

public class UserTableGateway {
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet SQLReturn;
	
	public  HashMap<Integer, String> SelectUsername(int user_id){
		 	
			HashMap<Integer, String> Result=new HashMap<Integer, String>();
			int i=0;
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);

				pstmt = (PreparedStatement) Conn.prepareStatement("SELECT username from user WHERE user_id=?");
				pstmt.setInt(1,user_id);
				SQLReturn=pstmt.executeQuery();
				while(SQLReturn.next()){
					Result.put(i,SQLReturn.getString("username"));
					i++;
				}

			  Conn.close();
				} catch (SQLException ex) {
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
		return Result;
	}
	
	public String UpdatePassword(String password, String username, String birthday){
		//user provide their username and birthday to change their password
		//this function returns users' email 
		String result = null;
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			  //update query
				pstmt = (PreparedStatement) Conn.prepareStatement("UPDATE user SET password=? WHERE username=? AND birthday=?");
				pstmt.setString(1,password);
				pstmt.setString(2,username);
				pstmt.setString(3,birthday);
				pstmt.executeUpdate();
				//select query
				pstmt = (PreparedStatement) Conn.prepareStatement("SELECT email from user WHERE username=? AND birthday=?");
				pstmt.setString(1,username);
				pstmt.setString(2,birthday);
				SQLReturn=pstmt.executeQuery();
				while(SQLReturn.next()){
					result=SQLReturn.getString("email");
					
				}

			  Conn.close();
				} catch (SQLException ex) {
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
		
		return result;
	}
	
	public  String  GetPasswordTest(String string){
	 	String result=null;
	 try {
		  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);

			pstmt = (PreparedStatement) Conn.prepareStatement("SELECT password from user WHERE username=?");
			pstmt.setString(1,string);
			SQLReturn=pstmt.executeQuery();
			while(SQLReturn.next()){
				result= SQLReturn.getString("password");
			}

		  Conn.close();
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
	return result;
	
}
	

}
