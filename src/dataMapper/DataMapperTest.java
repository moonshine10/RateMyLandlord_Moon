package dataMapper;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dataMapper.User;
import dataMapper.UserMapper;
import mysqlConfig.MySQL;

public class DataMapperTest {
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	
	
//	@Test
//	public void createNewUser() {//User(int user_idArg, String usernameArg, String passwordArg, String occupationArg, String birthdayArg,String emailArg)
//		User test1=new User(1, "test1", "123", "student", "1928-09-12",
//				"12345@gmail.com") ;
//				assertEquals(test1.username,"test1");
//				assertEquals(test1.user_id,1);
//				assertEquals(test1.password,"123");
//				assertEquals(test1.occupation,"student");
//	}
//	
//	@Test
//	public void readUserResult() { // User readResult(ResultSet rs)
//		User test1=new User(1, "test1", "123", "student", "1928-09-12",
//				"12345@gmail.com") ;
//		UserMapper u1=new UserMapper();
//
//		try {
//			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
//			pstmt = (PreparedStatement) Conn.prepareStatement("SELECT user_id, username, password, occupation, birthday, email FROM user WHERE user_id=?");
//			pstmt.setInt(1,1);
//			rs=pstmt.executeQuery();
//			rs.next();
//			assertEquals(test1.username,u1.load(rs).username); //test return object
//			Map<Integer,User> m1= new HashMap<Integer,User>();
//			m1=u1.loadedMap;
//			assertEquals(test1.username,m1.get(rs.getInt(1)).username);
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void FindUserFromID() throws SQLException {//User(int user_idArg, String usernameArg, String passwordArg, String occupationArg, String birthdayArg,String emailArg)
		User test1=new User(1, "test1", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		UserMapper m1=new UserMapper();
		Map<Integer,User> m2= new HashMap<Integer,User>();
		m2=m1.loadedMap;
		assertEquals(m2.isEmpty(),true); //check loaded map is empty
		String c=m1.find(1).username; //check if find
		assertEquals(c,test1.username);
		m2=m1.loadedMap;
		assertEquals(m2.isEmpty(),false); //check loaded map is not empty
		c=m2.get(1).username; //check if in the map 
		assertEquals(c,test1.username);
		

	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test
//	public void loadMapper() {// T load(ResultSet rs)
//
//		try {
//			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
//			pstmt = (PreparedStatement) Conn.prepareStatement("SELECT user_id, username, password, occupation, birthday, email FROM user WHERE user_id=?");
//			pstmt.setInt(1,1);
//			rs=pstmt.executeQuery();
//			rs.next();
//			
//			AbstractMapper u1=new UserMapper();
//			User r1=new User();
//			u1.load(rs);
//			r1=(User) u1.loadedMap.get(rs.getInt(1));
//			//how to write test case?
//			//is the structure correct in abstract mapper class? 
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//	
	
	
	


}

