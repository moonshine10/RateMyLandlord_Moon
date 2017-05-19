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
	
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	

	/**
	 * select username based on user ID
	 * @param user_id
	 * @return
	 */
	public  String  SelectUsername(int user_id){
		String SQLquery="SELECT username from user WHERE user_id=?";
		String Result=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		
		 try {
			 statement=DB.prepare(SQLquery);
			 statement.setInt(1,user_id);
			 rs=statement.executeQuery();
				while(rs.next()){
					Result=rs.getString("username");
				}
				DB.cleanUP(statement);
				} catch (SQLException ex) {
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
				}
		return Result;
	}
	
	/**
	 * find user based on user id
	 * @param user_id
	 * @return
	 */
	public  User findUser(int user_id){
	String SQLquery="SELECT user_id, username, password, occupation, birthday, email from user WHERE user_id=?";
	User u1= new User();
	PreparedStatement statement=null;
	ResultSet rs=null;
	 try {
		 statement=DB.prepare(SQLquery);
		 statement.setInt(1,user_id);
		 rs=statement.executeQuery();
			while(rs!=null&&rs.next()){
				u1.setUser_id(rs.getInt("user_id"));
				u1.setUsername(rs.getString("username"));
				u1.setPassword(rs.getString("password"));
				u1.setOccupation(rs.getString("occupation"));
				u1.setBirthday(rs.getString("birthday"));
				u1.setEmail(rs.getString("email"));
			}
			DB.cleanUP(statement);
			} catch (SQLException ex) {
			    // handle any errors
				logger.error("SQLException: " + ex.getMessage());
				logger.error("SQLState: " + ex.getSQLState());
				logger.error("VendorError: " + ex.getErrorCode());
				DB.cleanUP(statement);

			}
	
	return u1;
}
	
	/**
	 * update user's password , based on username and birthday (verification) 
	 * @param password
	 * @param username
	 * @param birthday
	 * @return
	 */
	
	public String UpdatePassword(String password, String username, String birthday){
		//user provide their username and birthday to change their password
		//this function returns users' email 
		String result = null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		String SQLqueryUpdate="UPDATE user SET password=? WHERE username=? AND birthday=?";
		String SQLquerySelect="SELECT email from user WHERE username=? AND birthday=?";
		 try {
			 statement=DB.prepare(SQLqueryUpdate);
			 statement.setString(1,password);
			 statement.setString(2,username);
			 statement.setString(3,birthday);
			 statement.executeUpdate();
				//select query
			 statement=DB.prepare(SQLquerySelect);
			 statement.setString(1,username);
			 statement.setString(2,birthday);
			 rs=statement.executeQuery();
				while(rs.next()){
					result=rs.getString("email");
				}

				DB.cleanUP(statement);
				} catch (SQLException ex) {
					  // handle any errors
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
					DB.cleanUP(statement);
				}
		
		return result;
	}
	
	/**
	 * update user's email 
	 * @param user_id_in
	 * @param NewEmail
	 * @return
	 * @throws SQLException
	 */
	public boolean UpdateEmail(int user_id_in, String NewEmail) throws SQLException{
		
		String SQLquery="UPDATE user SET email=? WHERE user_id=?";
		boolean out = false;
		PreparedStatement statement=null;
		try {
			 statement=DB.prepare(SQLquery);
			 statement.setString(1,NewEmail);
			 statement.setInt(2,user_id_in);				
				int result=statement.executeUpdate();
				
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
					DB.cleanUP(statement);
				}
			 finally{
					DB.cleanUP(statement);
			 }
		return out;
	}
	
	
	/**
	 * insert new user object into user table 
	 * @param u1
	 * @return
	 * @throws SQLException
	 */
	public int insertUserTable(User u1) throws SQLException{
		String SQLquery="INSERT INTO user ( username, password, occupation, birthday, email) values(?,?,?,?,?)";
		int user_id=0;
		PreparedStatement statement=null;
		ResultSet rs=null;
		 try {
			 statement=DB.prepare(SQLquery);
			 statement.setString(1,u1.getUsername());
			 statement.setString(2,u1.getPassword());
			 statement.setString(3,u1.getOccupation());
			 statement.setString(4,u1.getBirthday());
			 statement.setString(5,u1.getEmail());					
			 statement.executeUpdate();
				rs=statement.getGeneratedKeys();
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
					DB.cleanUP(statement);
				}
				 finally {
						DB.cleanUP(statement);
				 }
		return user_id;
	}
	
	/**
	 * password encryption test 
	 * @param string
	 * @return
	 */
	public  String  GetPasswordTest(String string){
	 	String result=null;
	 	PreparedStatement statement=null;
		ResultSet rs=null;
		String SQLquery="SELECT password from user WHERE username=?";
		 try {
			 statement=DB.prepare(SQLquery);
			 statement.setString(1,string);
			 rs=statement.executeQuery();
			while(rs.next()){
				result= rs.getString("password");
			}
			DB.cleanUP(statement);
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
				DB.cleanUP(statement);

			}
	return result;
	
}
	

}
