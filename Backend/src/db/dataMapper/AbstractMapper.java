package db.dataMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public abstract class AbstractMapper<T> {

	protected Map<Integer, T> loadedMap=new HashMap<Integer, T>();
	abstract protected String findStatement();
	abstract protected String insertStatement();
	abstract protected String updateEmailStatement();

	
	
	/**
	 * Cleanup data mapper
	 */
	protected void clearMap(){
		loadedMap.clear();
	}

	
	/**
	 * abstract find based on ID. Check the mapper first, then search in database
	 */
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
	}
	


	protected abstract boolean insert( T filter) throws SQLException;
	protected abstract  T load(int user_id) throws SQLException;
	
	
	
	
	
}
