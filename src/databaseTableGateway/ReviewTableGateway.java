package databaseTableGateway;





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
import dataMapper.DataMapperTest;
import dataMapper.Review;
import dataMapper.User;


public class ReviewTableGateway {
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet SQLReturn;
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	
	

	public static Review  SelectByReviewID(int review_id) throws SQLException{
		String SQLquery="SELECT * from review WHERE review_id=?";
		int score=999;
		String description=null;
		int property_id=0;
		int user_id=0;
		
		
		
		 try {
			 
			 
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);

				pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
				pstmt.setInt(1,review_id);
				SQLReturn=pstmt.executeQuery();
				while(SQLReturn.next()){
					score=SQLReturn.getInt("score");
					description=SQLReturn.getString("description");
					property_id=SQLReturn.getInt("property_id");
					user_id=SQLReturn.getInt("user_id");
				}
				
				
				
				
				} catch (SQLException ex) {
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
					  Conn.close();
				}finally{
					  Conn.close();

				}
		Review r1=new Review( review_id,  score,  description,  property_id,  user_id);
		return r1;
	}
	
	public static Review  SelectByPropertyID(int property_id) throws SQLException{
		String SQLquery="SELECT * from review WHERE property_id=?";
		int score=999;
		String description=null;
		int review_id=0;
		int user_id=0;
		
		
		
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);

				pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
				pstmt.setInt(1,property_id);
				SQLReturn=pstmt.executeQuery();
				while(SQLReturn.next()){
					score=SQLReturn.getInt("score");
					description=SQLReturn.getString("description");
					review_id=SQLReturn.getInt("review_id");
					user_id=SQLReturn.getInt("user_id");
				}
				
				
				
				} catch (SQLException ex) {
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
					  Conn.close();
				}finally{
					  Conn.close();

				}
		Review r1=new Review( review_id,  score,  description,  property_id,  user_id);
		return r1;
	}
	
	
	public static Review  SelectByUserID(int user_id) throws SQLException{
		String SQLquery="SELECT * from review WHERE user_id=?";
		int score=999;
		String description=null;
		int review_id=0;
		int property_id=0;
		
		
		
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);

				pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
				pstmt.setInt(1,user_id);
				SQLReturn=pstmt.executeQuery();
				while(SQLReturn.next()){
					score=SQLReturn.getInt("score");
					description=SQLReturn.getString("description");
					review_id=SQLReturn.getInt("review_id");
					property_id=SQLReturn.getInt("property_id");
				}
				
				
				
				} catch (SQLException ex) {
					logger.error("SQLException: " + ex.getMessage());
					logger.error("SQLState: " + ex.getSQLState());
					logger.error("VendorError: " + ex.getErrorCode());
					  Conn.close();
				}finally{
					  Conn.close();

				}
		Review r1=new Review( review_id,  score,  description,  property_id,  user_id);
		return r1;
	}
	
	
	public static int insertReviewTable(Review r1) throws SQLException{
		String SQLquery="INSERT INTO review ( score, description, property_id, user_id) values(?,?,?,?)";
		ResultSet rs=null;
		int review_id=0;
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			  //update query
			  	pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery,Statement.RETURN_GENERATED_KEYS);		
			  	pstmt.setInt(1,r1.getScore());
				pstmt.setString(2,r1.getDescription());
				pstmt.setInt(3,r1.getProperty_id());
				pstmt.setInt(4,r1.getUser_id());
				pstmt.executeUpdate();
				rs=pstmt.getGeneratedKeys();
				if (rs!=null && rs.next()){
					 
					review_id=rs.getInt(1);
				}
				if (review_id==0){
					logger.error("Error: fail to get proper review ID");
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
		return review_id;
	}
	
	

	

	
	public static boolean updateScore(int review_id_in, int newScore) throws SQLException{
		
		String SQLquery="UPDATE review SET score=? WHERE review_id=?";
		boolean out = false;
		 try {
			  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			  //update query
				pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
				pstmt.setInt(1,newScore);
				pstmt.setInt(2,review_id_in);				
				int result=pstmt.executeUpdate();
				
				if(result==1){
					out= true;
				}
				else{
					out=false;
					logger.error("Score update not success");
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
	
		public static boolean updateDescription(int review_id_in, String newDes) throws SQLException{
			
			String SQLquery="UPDATE review SET description=? WHERE review_id=?";
			boolean out = false;
			 try {
				  Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
				  //update query
					pstmt = (PreparedStatement) Conn.prepareStatement(SQLquery);
					pstmt.setString(1,newDes);
					pstmt.setInt(2,review_id_in);				
					int result=pstmt.executeUpdate();
					
					if(result==1){
						out= true;
					}
					else{
						out=false;
						logger.error("Score description not success");
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
		
	
	

}
