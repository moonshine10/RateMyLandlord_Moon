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

import com.mysql.jdbc.Statement;

import dataMapper.User;
import dataMapper.UserMapper;
import junit.framework.Assert;
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
		User u1=new User(1, "test1", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		UserMapper um1=new UserMapper();
		Map<Integer,User> m2= new HashMap<Integer,User>();
		m2=um1.loadedMap;
		um1.clearMap();
		assertEquals(m2.isEmpty(),true); //check loaded map is empty
		String c=um1.find(1).username; //check if find
		assertEquals(c,u1.username);
		m2=um1.loadedMap;
		assertEquals(m2.isEmpty(),false); //check loaded map is not empty
		c=m2.get(1).username; //check if in the map 
		assertEquals(c,u1.username);
		

	}
	

	
//	@Test	
//	public void insertUserTableTest() throws SQLException {
//		User u1=new User(1, "test7", "123", "student", "1928-09-12",
//				"12345@gmail.com") ;
//		UserMapper m1=new UserMapper();
//		String insertQuery="insert into ratemylandlord.user (username, password, occupation, birthday, email) values(?,?,?,?,?)";
//		boolean result;
//		
//		PreparedStatement pstmt=null;
//		try {
//					//only insert ID here
//			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
//			
//			pstmt = (PreparedStatement) Conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
//
//			result=m1.insert(pstmt,u1);
//
//		
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Conn.close(); 
//		}
//		finally{
//			Conn.close(); 
//		}
//		
//	}		
	
	@Test	
	public void insertAbstractInsertTest() throws SQLException {
		User u1=new User(1, "test8", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		UserMapper um1=new UserMapper();
		Map<Integer,User> m2= new HashMap<Integer,User>();
		m2=um1.loadedMap;
		um1.clearMap();
		assertEquals(m2.isEmpty(),true); //check loaded map is empty
		assertTrue("New User is added",um1.doinsert(u1));
		//check mapper
		m2=um1.loadedMap;
		String c=m2.get(1).username;
		assertEquals(c,u1.username);//check username
		assertEquals(m2.isEmpty(),false); //check loaded map is not empty
		assertEquals(c,u1.username);
		
		
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
//@Test	//test SQL function (development test)
//	public void insertUserTableTest1() throws SQLException {
//		User u1=new User(1, "test7", "123", "student", "1928-09-12",
//				"12345@gmail.com") ;
//		UserMapper m1=new UserMapper();
//		String insertQuery="insert into ratemylandlord.user (username, password, occupation, birthday, email) values(?,?,?,?,?)";
//		int result=0;
//		
//		PreparedStatement pstmt=null;
//		try {
//					//only insert ID here
//			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
//			
//			pstmt = (PreparedStatement) Conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
//
//			result=m1.insert(pstmt,u1);
//
//		
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Conn.close(); 
//		}
//		finally{
//			Conn.close(); 
//		}
//		System.out.println(result);
//	}	
	
	
	
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

