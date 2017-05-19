package db.dataMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import db.dataMapper.User;
import db.databaseTableGateway.UserTableGateway;

public class UserMapper extends AbstractMapper<User>   {
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	protected String findStatement(){
		return "SELECT "+SelectCOLUMNS+ " FROM user WHERE user_id=?";
		
	}
	protected String insertStatement(){
		return "INSERT INTO user ("+ InsertCOLUMNS + ") values(?,?,?,?,?)";
	}	
	protected String updateEmailStatement(){
		return "UPDATE user SET email=? WHERE user_id=?";
	}
	

	public static final String SelectCOLUMNS="user_id, username, password, occupation, birthday, email";
	public static final String InsertCOLUMNS="username, password, occupation, birthday, email";
	
	public User find(int user_id) throws SQLException{
		
		return (User) abstractFindFromID(user_id);
		
	}


	/**
	 * find user in database based on ID, load into data mapper
	 */
	public User load(int user_id) throws SQLException{ //load function here 
		
		
		UserTableGateway ug1=new UserTableGateway();
		//put in mapper
		User u1=ug1.findUser(user_id);
		loadedMap.put(u1.getUser_id(),u1);
		return u1;
	}
	
	/**
	 * insert new user into database and data mapper 
	 */
	public boolean insert( User u1) throws SQLException{ 
		
		int key=0;
		//insert in database
		UserTableGateway ug1=new UserTableGateway();
		key= ug1.insertUserTable(u1);
		boolean out=false;
		
		//put in db.dataMapper
		if(u1!=null){
				loadedMap.put(key, u1);
				out=true;
			}
			else{
				logger.error("Error: User object is null");
			}	
		
		return out;
	}
	
	/**
	 * update user's email based on user id , in database and data mapper 
	 * @param email
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	
protected boolean updateEmail( String email, int user_id) throws SQLException{ //load function here 
		
		UserTableGateway ug1=new UserTableGateway();
		boolean out=false;
		boolean success=false;
		success=ug1.UpdateEmail(user_id, email);
		if(success){
			User u1= new User();
			u1=(User) loadedMap.get(user_id);
			u1.setEmail(email);
			loadedMap.put(user_id, u1);
			out=true;
		}
		return out;
	
		
	}





	
	
	
	

}
