package db.databaseTableGateway;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Statement;

import Config.MySQL;
import db.dataMapper.DataMapperTest;
import db.dataMapper.User;

public class UserTableGateway {
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet SQLReturn;
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	

	public  String  SelectUsername(int user_id){
		String SQLquery="SELECT username from user WHERE user_id=?";
		String Result=null;
		
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);

				pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
				pstmt.setInt(1,user_id);
				SQLReturn=pstmt.executeQuery();
				while(SQLReturn.next()){
					Result=SQLReturn.getString("username");
				
				}

			  Conn.close();
				} catch (SQLException ex) {
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
				}
		return Result;
	}
	
	
	public  User findUser(int user_id){
	String SQLquery="SELECT user_id, username, password, occupation, birthday, email from user WHERE user_id=?";
	User u1= new User();
	 try {
		  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
		  
			pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
			pstmt.setInt(1,user_id);
			SQLReturn=pstmt.executeQuery();
			while(SQLReturn!=null&&SQLReturn.next()){
				u1.setUser_id(SQLReturn.getInt("user_id"));
				u1.setUsername(SQLReturn.getString("username"));
				u1.setPassword(SQLReturn.getString("password"));
				u1.setOccupation(SQLReturn.getString("occupation"));
				u1.setBirthday(SQLReturn.getString("birthday"));
				u1.setEmail(SQLReturn.getString("email"));
			
			}

		  Conn.close();
			} catch (SQLException ex) {
			    // handle any errors
				logger.error("SQLException: " + ex.getMessage());
				logger.error("SQLState: " + ex.getSQLState());
				logger.error("VendorError: " + ex.getErrorCode());
			}
	
	return u1;
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
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
				}
		
		return result;
	}
	
	
	public boolean UpdateEmail(int user_id_in, String NewEmail) throws SQLException{
		
		String SQLquery="UPDATE user SET email=? WHERE user_id=?";
		boolean out = false;
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			  //update query
				pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
				pstmt.setString(1,NewEmail);
				pstmt.setInt(2,user_id_in);				
				int result=pstmt.executeUpdate();
				
				if(result==1){
					out= true;
				}
				else{
					out=false;
					logger.error("Email update not success");
				}
		

			  
				} catch (SQLException ex) {
					  // handle any errors
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
					Conn.close();
				}
			 finally{
				 Conn.close();
			 }
		return out;
	}
	
	
	public int insertUserTable(User u1) throws SQLException{
		String SQLquery="INSERT INTO user ( username, password, occupation, birthday, email) values(?,?,?,?,?)";
		ResultSet rs=null;
		int user_id=0;
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			  //update query
			  	pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery,Statement.RETURN_GENERATED_KEYS);		
			  	pstmt.setString(1,u1.getUsername());
				pstmt.setString(2,u1.getPassword());
				pstmt.setString(3,u1.getOccupation());
				pstmt.setString(4,u1.getBirthday());
				pstmt.setString(5,u1.getEmail());					
				pstmt.executeUpdate();
				rs=pstmt.getGeneratedKeys();
				if (rs!=null && rs.next()){
					 
					user_id=rs.getInt(1);
				}
				if (user_id==0){
					logger.error("Error: fail to get proper user ID");
				}
				
				} catch (SQLException ex) {
					  // handle any errors
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
					Conn.close();
				}
				 finally {
					 Conn.close();
				 }
		return user_id;
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
