package db.tool;

import org.junit.Test;

public class testingTool {
	@Test
	public static boolean passwordCheck(String pw){
		
		//password has to be longer than 2 char
		if(pw.length()>2){
			return true;
		}
		return false; 
	}
	@Test
	public static boolean birthdayCheck(String birthday) {
		//birthday has to be in 1993
		String year = birthday.substring(0, Math.min(birthday.length(), 4));
		
		return (year.equals("1993"));
	}
	@Test
	public static boolean emailCheck(String email) {
		// TODO Auto-generated method stub
		return true;
	}
	@Test
	public static boolean usernameCheck(String username) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
