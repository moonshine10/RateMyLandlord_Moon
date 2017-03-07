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
	
	
	
	protected T abstractFind(int input_id){
		T result=(T) loadedMap.get(input_id);
		try {
			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			pstmt = (PreparedStatement) Conn.prepareStatement(findStatement());
			pstmt.setInt(1,input_id);
			rs=pstmt.executeQuery();
			rs.next();
			result=load(rs);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	protected T load(ResultSet rs) throws SQLException{
		int input_id=rs.getInt(1);
		T result=ResultHandler.readResult(rs);
		loadedMap.put(input_id, result);
		return result;
		
	}
	
	
	abstract protected DomainObject doLoad(int user_id,ResultSet rs) throws SQLException;
	
	
}
