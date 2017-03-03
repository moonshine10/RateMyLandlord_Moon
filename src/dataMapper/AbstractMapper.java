package dataMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import mysqlConfig.MySQL;

public abstract class AbstractMapper {
	
	static String Driver=MySQL.Driver;
	static String MySQLurl=MySQL.url;
	static String SQLusername=MySQL.username;
	static String SQLpassword=MySQL.password;
	
	static Connection Conn = null;
	static PreparedStatement pstmt;
	static ResultSet rs;

	protected Map<Integer, DomainObject> loadedMap=new HashMap<Integer, DomainObject>();
	abstract protected String findStatement();
	
	
	
	protected DomainObject abstractFind(int user_id){
		DomainObject result=(DomainObject) loadedMap.get(user_id);
		try {
			Conn =DriverManager.getConnection(MySQLurl,SQLusername, SQLpassword);
			pstmt = (PreparedStatement) Conn.prepareStatement(findStatement());
			pstmt.setInt(1,user_id);
			rs=pstmt.executeQuery();
			rs.next();
			result=load(rs);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	protected DomainObject load(ResultSet rs) throws SQLException{
		int user_id=rs.getInt(1);
		DomainObject result=doLoad(user_id,rs);
		loadedMap.put(user_id, result);
		return result;
		
	}
	abstract protected DomainObject doLoad(int user_id,ResultSet rs) throws SQLException;
	
	
}
