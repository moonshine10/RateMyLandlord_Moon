package databaseTableGateway;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import org.junit.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Config.MySQL;
import dataMapper.DataMapperTest;

public class DbTesting {

	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String username=MySQL.username;
	static String password=MySQL.password;
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	Connection Conn = null;
	
	
	public void SQLConnect(){
		
		UserTableGateway users= new UserTableGateway();
		HashMap<Integer, String> Result=users.SelectUsername(2);
		int size=Result.size();
		for (int i=0; i<size; i++) {
			  String value = (String) Result.get(i);
			  System.out.println(value);
			  assertEquals(value,"test2");
			}
	}
	
	
	public void SQLUpdatePassword(){
		
		UserTableGateway users= new UserTableGateway();
		String Result= users.UpdatePassword("456","test1", "1993-10-10");
		//System.out.println(Result);
		assertEquals(Result,"qinyue.yin@colorado.edu");
		assertEquals(users.GetPasswordTest("test1"),"456");
	}
	
	
	
	public void SQLShowPassword(){
		
		UserTableGateway users= new UserTableGateway();
		String Result= users.GetPasswordTest("test1");
		//System.out.println(Result);
		assertEquals(Result,"456");
	}
	
	
	
	public void selectUsername()
	{
		UserTableGateway u1=new UserTableGateway();
		logger.info(u1.SelectUsername(1));
	}
	
	@Test
	public void updateEmail()
	{
		UserTableGateway u1=new UserTableGateway();
		assertEquals(true,u1.UpdateEmail(2, "test@gmail.com"));
	}
}
