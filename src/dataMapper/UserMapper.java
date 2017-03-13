package dataMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataMapper.User;

public class UserMapper extends AbstractMapper<User>   implements ResultHandler{
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
	public boolean doInsert(User u1) throws SQLException{
		return  abstractInsert(u1);
		
	}
	public boolean doUpdateEmail(String e, int uid) throws SQLException{
		return  abstractUpdateEmail(e,uid);
		
	}

	public User load(ResultSet rs) throws SQLException{ //load function here 
		
		int input_id=rs.getInt(1);		
		int user_idArg=rs.getInt("user_id");
		String usernameArg=rs.getString("username");
		String passwordArg=rs.getString("password");
		String occupationArg=rs.getString("occupation");
		String birthdayArg=rs.getString("birthday");
		String emailArg=rs.getString("email");
		User u1=new User(user_idArg,usernameArg,passwordArg,occupationArg,birthdayArg,emailArg);
		if(u1.username!=null){
			loadedMap.put(input_id, u1);
			return u1;
		}
		else{
			System.out.println("Error: User object is null");
			return null;
		}
	}
	protected boolean insert(PreparedStatement p1, User u1) throws SQLException{ //load function here 
		
		int key=0;
		//insert in database
		p1.setString(1,u1.username);
		p1.setString(2,u1.password);
		p1.setString(3,u1.occupation);
		p1.setString(4,u1.birthday);
		p1.setString(5,u1.email);
		p1.executeUpdate();
		ResultSet rs=p1.getGeneratedKeys();
		if (rs!=null && rs.next()){
			 key=rs.getInt(1);//grab key
			 
			 //put in dataMapper
			 if(u1.username!=null){
					loadedMap.put(key, u1);
					return true;
				}
				else{
					System.out.println("Error: User object is null");
					return false;
				}	
		}
		return false;
	}
	
protected boolean updateEmail(PreparedStatement p1, String email, int user_id) throws SQLException{ //load function here 
		
		//insert in database
		p1.setString(1,email);
		p1.setInt(2,user_id); 
		if(p1.executeUpdate()==1){
			User u1= new User();
			u1=(User) loadedMap.get(user_id);
			u1.email=email;
			loadedMap.put(user_id, u1);
			return true;
		}
		return false;
		
					
		
	}





	
	
	
	

}
