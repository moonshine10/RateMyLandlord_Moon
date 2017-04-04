package dataMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Statement;

import Config.MySQL;

public abstract class AbstractMapper<T> {
	
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static ResultSet rs;

	protected Map<Integer, T> loadedMap=new HashMap<Integer, T>();
	abstract protected String findStatement();
	abstract protected String insertStatement();
	abstract protected String updateEmailStatement();

	
	protected void clearMap(){
		loadedMap.clear();
	}

	
	protected T abstractFindFromID(int input_id) throws SQLException {
		//this function is a find function. First it will check if the data is in datamapper, if not go find it in DB and load in datamapper
		//still implementing ...
		
		//try to find it first 
		T result=null;
		result=(T) loadedMap.get(input_id);
		if (result==null) { //return result;
			result=load(input_id);  

		}
		return result;   
		//
	}
	

	
	


	
	


	protected abstract boolean insert( T filter) throws SQLException;
	protected abstract  T load(int user_id) throws SQLException;
	
	
	
	
	
}
