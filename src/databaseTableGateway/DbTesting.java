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

import mysqlConfig.MySQL;

public class DbTesting {

	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String username=MySQL.username;
	static String password=MySQL.password;
	
	Connection Conn = null;
	
	@Test
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
	
	@Test
	public void SQLUpdatePassword(){
		
		UserTableGateway users= new UserTableGateway();
		String Result= users.UpdatePassword("456","test1", "1993-10-10");
		//System.out.println(Result);
		assertEquals(Result,"qinyue.yin@colorado.edu");
		assertEquals(users.GetPasswordTest("test1"),"456");
	}
	
	
	@Test
	public void SQLShowPassword(){
		
		UserTableGateway users= new UserTableGateway();
		String Result= users.GetPasswordTest("test1");
		//System.out.println(Result);
		assertEquals(Result,"456");
	}
	
	
	@Test
	public void nothing()
	{
		
	}
}
