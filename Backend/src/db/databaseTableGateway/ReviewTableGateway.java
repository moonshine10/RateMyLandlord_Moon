package db.databaseTableGateway;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

import Config.MySQL;
import db.dataMapper.DataMapperTest;
import db.dataMapper.Review;
import db.dataMapper.User;


public class ReviewTableGateway {
	
	
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	
	/**
	 * find review based on review_id
	 * @param review_id
	 * @return
	 * @throws SQLException
	 */

	public static Review  SelectByReviewID(int review_id) throws SQLException{
		String SQLquery="SELECT * from review WHERE review_id=?";
		int score=999;
		String description=null;
		int property_id=0;
		int user_id=0;
		PreparedStatement statement=null;
		ResultSet rs=null;
		 try {
				statement=DB.prepare(SQLquery);
				statement.setInt(1,review_id);
				rs=statement.executeQuery();
				
				while(rs.next()){
					score=rs.getInt("score");
					description=rs.getString("description");
					property_id=rs.getInt("property_id");
					user_id=rs.getInt("user_id");
				}
				} catch (SQLException ex) {
					DB.cleanUP(statement);
				}finally{
					DB.cleanUP(statement);

				}
		Review r1=new Review( review_id,  score,  description,  property_id,  user_id);
		return r1;
	}
	
	
	
	/**
	 * find review based on property_id
	 * @param property_id
	 * @return
	 * @throws SQLException
	 */
	public static Review  SelectByPropertyID(int property_id) throws SQLException{
		String SQLquery="SELECT * from review WHERE property_id=?";
		int score=999;
		String description=null;
		int review_id=0;
		int user_id=0;
		PreparedStatement statement=null;
		ResultSet rs=null;
		 try {
			 statement=DB.prepare(SQLquery);
				statement.setInt(1,property_id);
				rs=statement.executeQuery();
				while(rs.next()){
					score=rs.getInt("score");
					description=rs.getString("description");
					review_id=rs.getInt("review_id");
					user_id=rs.getInt("user_id");
				}
				
				
				
				} catch (SQLException ex) {
					DB.cleanUP(statement);
				}finally{
					DB.cleanUP(statement);

				}
		Review r1=new Review( review_id,  score,  description,  property_id,  user_id);
		return r1;
	}
	

	/**
	 * find review based on property_id
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public static Review  SelectByUserID(int user_id) throws SQLException{
		String SQLquery="SELECT * from review WHERE user_id=?";
		int score=999;
		String description=null;
		int review_id=0;
		int property_id=0;
		PreparedStatement statement=null;
		ResultSet rs=null;
		 try {
			 statement=DB.prepare(SQLquery);
				statement.setInt(1,user_id);
				rs=statement.executeQuery();
				while(rs.next()){
					score=rs.getInt("score");
					description=rs.getString("description");
					review_id=rs.getInt("review_id");
					property_id=rs.getInt("property_id");
				}
				} catch (SQLException ex) {
					DB.cleanUP(statement);

				}finally{
					DB.cleanUP(statement);
				}
		Review r1=new Review( review_id,  score,  description,  property_id,  user_id);
		return r1;
	}
	
	/**
	 * insert review into database
	 * @param r1
	 * @return
	 * @throws SQLException
	 */
	public static int insertReviewTable(Review r1) throws SQLException{
		String SQLquery="INSERT INTO review ( score, description, property_id, user_id) values(?,?,?,?)";
		int review_id=0;
		PreparedStatement statement=null;
		ResultSet rs=null;
		 try {
			 statement=DB.prepareReturnKey(SQLquery);
			 statement.setInt(1,r1.getScore());
			 statement.setString(2,r1.getDescription());
			 statement.setInt(3,r1.getProperty_id());
			 statement.setInt(4,r1.getUser_id());
			 statement.executeUpdate();
				rs=statement.getGeneratedKeys();
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
					DB.cleanUP(statement);
				}
				 finally {
						DB.cleanUP(statement);
				 }
		return review_id;
	}
	
	

	
	/**
	 * update review score in database 
	 * @param review_id_in
	 * @param newScore
	 * @return
	 * @throws SQLException
	 */
		
		public static boolean updateScore(int review_id_in, int newScore) throws SQLException{
			
			String SQLquery="UPDATE review SET score=? WHERE review_id=?";
			boolean out = false;
			PreparedStatement statement=null;
			 try {
				 statement=DB.prepare(SQLquery);
				 statement.setInt(1,newScore);
				 statement.setInt(2,review_id_in);				
					int result=statement.executeUpdate();
					
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
						DB.cleanUP(statement);
					}
				 finally{
						DB.cleanUP(statement);
				 }
			return out;
		}
	
		/**
		 * update review description in database 
		 * @param review_id_in
		 * @param newDes
		 * @return
		 * @throws SQLException
		 */
		public static boolean updateDescription(int review_id_in, String newDes) throws SQLException{
			PreparedStatement statement=null;
			String SQLquery="UPDATE review SET description=? WHERE review_id=?";
			boolean out = false;
			 try {
				 statement=DB.prepare(SQLquery);

				 statement.setString(1,newDes);
				 statement.setInt(2,review_id_in);				
					int result=statement.executeUpdate();
					
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
						DB.cleanUP(statement);
					}
				 finally{
						DB.cleanUP(statement);
				 }
			return out;
		}
		
		/**
		 * set review score to zero , based on landlord ID
		 * @param landlord_id
		 * @throws SQLException
		 */
			public static void setScoreToZero( int landlord_id) throws SQLException{
			
			String SQLquery="{call setZeroScore(?)}";
			CallableStatement cs=null;
			 try {
				 cs=DB.prepareCall(SQLquery);
					cs.setInt(1, landlord_id);
					cs.executeUpdate();
				  
					} catch (SQLException ex) {
						DB.cleanUpPrepareCall(cs);
					}
				 finally{
					 DB.cleanUpPrepareCall(cs);
				 }
			
		}
		
	
	

}
