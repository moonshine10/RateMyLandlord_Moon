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

	
	protected T abstractFindFromID(int input_id) throws SQLException{
		//this function is a find function. First it will check if the data is in datamapper, if not go find it in DB and load in datamapper
		//still implementing ...
		
		//try to find it first 
		T result=(T) loadedMap.get(input_id);
		if (result==null) { //return result;
		PreparedStatement pstmt=null;
		try {
			//if not in hash map, go find in database, and load to hashmap 
			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			pstmt = (PreparedStatement) Conn.prepareStatement(findStatement());			
			pstmt.setInt(1,input_id);// prepare statement one function , only one para
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=load(rs);  
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Conn.close(); 
		}
		finally{
			Conn.close(); 
		}
		}
		return result;
	}
	
	protected boolean abstractInsert(Object filter) throws SQLException{
		try {
			
			if (insert((User) filter)){
				return true;
				
			}
			else {
				System.out.println("Insert Not successful");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Conn.close(); 
		}
		finally{
			Conn.close(); 
		}
		return false;
	}
	
	
	protected boolean abstractUpdateEmail(String email,int user_id) throws SQLException{
		try {

			if(updateEmail( email,  user_id))
			{
				return true;
			}
			
			else {
				System.out.println("Insert Not successful");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Conn.close(); 
		}
		finally{
			Conn.close(); 
		}
		return false;
	}

	
	

	protected abstract boolean updateEmail( String email, int user_id) throws SQLException;

	protected abstract boolean insert( User filter) throws SQLException;
	protected abstract  T load(ResultSet rs) throws SQLException;
	
	
	
	
	
}
