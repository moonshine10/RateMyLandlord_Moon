package dataMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends AbstractMapper{
	protected String findStatement(){
		return "SELECT "+COLUMNS+ " FROM user WHERE id=?";
		
	}
	public static final String COLUMNS="user_id, username, password, occupation, birthday, email";
	public User find(int user_id){
		return (User) abstractFind(user_id);
		
	}
	
	protected DomainObject doLoad(int user_id,ResultSet rs) throws SQLException{
		String usernameArg=rs.getString("username");
		String passwordArg=rs.getString("password");
		String occupationArg=rs.getString("occupation");
		String birthdayArg=rs.getString("birthday");
		String emailArg=rs.getString("email");
		
		
		return new User(user_id,usernameArg,passwordArg,occupationArg,birthdayArg,emailArg);
		
	}
	

}
