package dataMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dataMapper.User;

public class UserMapper extends AbstractMapper  implements ResultHandler{
	protected String findStatement(){
		return "SELECT "+COLUMNS+ " FROM user WHERE user_id=?";
		
	}
	public static final String COLUMNS="user_id, username, password, occupation, birthday, email";
	public User find(int user_id) throws SQLException{
		return (User) abstractFindFromID(user_id);
		
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
	


	@Override
	protected PreparedStatement getFindStatement(Object filter_object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
