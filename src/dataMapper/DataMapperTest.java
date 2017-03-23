package dataMapper;

import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Test;

import com.mysql.jdbc.Statement;

import Config.*;
import dataMapper.User;
import dataMapper.UserMapper;
import encription.*;
import junit.framework.Assert;

public class DataMapperTest {
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;
	static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	public void loggerTest(){
		logger.debug("DEBUG TEST");
		
	}
	
	
	
	public void createNewUser() {//User(int user_idArg, String usernameArg, String passwordArg, String occupationArg, String birthdayArg,String emailArg)
		User test1=new User(1, "test1", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
				assertEquals(test1.getUsername(),"test1");
				assertEquals(test1.getUser_id(),1);
				assertEquals(test1.getPassword(),"123");
				assertEquals(test1.getOccupation(),"student");
	}
	
	public void loadUserUserMapperTest() { // User readResult(ResultSet rs)
		User test1=new User(1, "test1", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		UserMapper u1=new UserMapper();
		int user_id=1;

		try {
			
			assertEquals(test1.getUsername(),u1.load(user_id).getUsername()); //test return object
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//this test did not pass, find function in UserMapper is not working 
	public void findUserUserMapperTest() throws Exception {//User(int user_idArg, String usernameArg, String passwordArg, String occupationArg, String birthdayArg,String emailArg)
		int userID=36;
		User u1=new User(userID, "test10", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		
		UserMapper um1=new UserMapper();
		Map<Integer,User> m2= new HashMap<Integer,User>();
		um1.clearMap();
		m2=um1.loadedMap;
		assertEquals(m2.isEmpty(),true); //check loaded map is empty
		
		
//		logger.info(um1.find(userID).getClass().getName());
		User u2=um1.find(userID); //check if find
		assertNotNull(u2);
		String c =u2.getUsername();
		assertEquals(c,u1.getUsername());
		logger.info(u1.getUsername());
		
		//check if value is in the map
		m2=um1.loadedMap;
		logger.info(m2.isEmpty());
		assertEquals(m2.isEmpty(),false); //check loaded map is not empty	
		c=m2.get(userID).getUsername(); //check if in the map 
		assertEquals(c,u1.getUsername());
		
		
//		//password
//		String keystoreLocation= Keystore.keystoreLocation;
//		String keystorePass=Keystore.keystorePass;
//		String alias=Keystore.keystorealias;
//		String keyPass=Keystore.keyPass; 
//		Key key=KeystoreUtil.getKeyFromKeystore(keystoreLocation,keystorePass,   alias,   keyPass);
//		AES256 a= new AES256(key);
//		String c2=m2.get(userID).password; 
//		String fin=a.decrypt(c2);
//		assertEquals(fin,u1.password);
	}
	
	
	
	public void updateEmailTest() throws SQLException {
		String uEmail="check1@gmail.com";
		int uid=3;
		User u1=new User();
		UserMapper um1=new UserMapper();
		um1.clearMap();
		Map<Integer,User> m2= new HashMap<Integer,User>();
		
		//check origin email in db
		assertEquals(um1.find(uid).getEmail(),"newemail@gmail.com");// old email
		
		//run function
		um1.updateEmail(uEmail, uid);
		
		//check database
		String c=um1.find(uid).getEmail();
		assertEquals(c,uEmail);
		
		//check loadmap
		m2=um1.loadedMap;
		u1=m2.get(uid);
		assertEquals(u1.getEmail(),uEmail);// new email

	}
	public void insertUserMapperTest() throws Exception {
		
		
		//encrypt password
		String pw1="123";
		String keystoreLocation= Keystore.keystoreLocation;
		String keystorePass=Keystore.keystorePass;
		String alias=Keystore.keystorealias;
		String keyPass=Keystore.keyPass;
		Key key=KeystoreUtil.getKeyFromKeystore(keystoreLocation,keystorePass,   alias,   keyPass);
		AES256 a= new AES256(key);
		String enc_pw1=a.encrypt(pw1);
		
		
		//insert
		User u1=new User(1, "test11", enc_pw1, "student", "1928-09-12",
				"12345@gmail.com") ;
		UserMapper m1=new UserMapper();
		boolean result;
		
			result=m1.insert(u1);
			assertTrue("Result",result);
			

		
	}		
	
	public void insertAbstractInsertTest() throws SQLException {
		User u1=new User(1, "test8", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		UserMapper um1=new UserMapper();
		Map<Integer,User> m2= new HashMap<Integer,User>();
		m2=um1.loadedMap;
		um1.clearMap();
		assertEquals(m2.isEmpty(),true); //check loaded map is empty
		assertTrue("New User is added",um1.insert(u1));
		//check mapper
		m2=um1.loadedMap;
		assertEquals(m2.isEmpty(),false); //check loaded map is not empty
		Set keys=m2.keySet();
		for (java.util.Iterator i= keys.iterator(); i.hasNext();)
		{
			int key= (int) i.next();
			String c=m2.get(key).getUsername();
			assertEquals(c,u1.getUsername());//check username
			System.out.println("Key number:"+key);
			
			
		}
		
	}	
	@Test
	public void userUnitofWorkUpdateTest() throws SQLException {
		UnitOfWork.newCurrent();//create new Unit Of Work 
		User u1=new User();	
		
		u1.setUser_id(3);
		u1.UOWsetEmail("newemail2@gmail.com");
		
		u1.setUser_id(4);
		u1.UOWsetEmail("newemail2@gmail.com");
		
		u1.setUser_id(5);
		u1.UOWsetEmail("newemail2@gmail.com");
		
		UnitOfWork.getCurrent().commit();
	}
	
	public void userUnitofWorkCreateTest() throws SQLException {
		UnitOfWork.newCurrent();//create new Unit Of Work 
		User.creatInDB(1, "test10", "123", "student", "1928-09-12",
				"12345@gmail.com") ;	
		User.creatInDB(1, "test10", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		User.creatInDB(1, "test10", "123", "student", "1928-09-12",
				"12345@gmail.com") ;
		
		
		UnitOfWork.getCurrent().commit();
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	/***************************************************NOT USE*****************************************************/
	
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

