package dataMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import mysqlConfig.MySQL;

public abstract class AbstractMapper<T> {
	
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;

	protected Map<Integer, T> loadedMap=new HashMap<Integer, T>();
	abstract protected String findStatement();
	
	
	
	protected T abstractFind(int input_id) throws SQLException{
		//this function is a find function. First it will check if the data is in datamapper, if not go find it in DB and load in datamapper
		//still implementing ...
		
		//try to find it first 
		T result=(T) loadedMap.get(input_id);
		try {
			//if not in hash map, go find in database, and load to hashmap 
			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			pstmt = (PreparedStatement) Conn.prepareStatement(findStatement());			
			pstmt.setInt(1,input_id);// prepare statement one function , only one para
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=load(rs); //result is not equal to NULL before load into map 

			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			Conn.close(); 
		}
		finally{
			Conn.close(); 
		}
		return result;
	}
	
	protected abstract PreparedStatement getFindStatement(T filter_object); //use this function will solve problem with multiple input para
	
	protected abstract  T load(ResultSet rs) throws SQLException;
	
	
	
	
}
