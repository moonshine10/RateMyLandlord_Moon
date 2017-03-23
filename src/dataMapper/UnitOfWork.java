package dataMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


public class UnitOfWork {
	// this unit of work function is only implemented for User object
	
	public ArrayList<User> newObjects= new ArrayList<User>();
	public ArrayList<User> dirtyObjects= new ArrayList<User>();// dirtyObjects are defined as updated user object
	UserMapper um1= new UserMapper();
	//!!Define how new object is added
	private static ThreadLocal<UnitOfWork> current=new ThreadLocal<UnitOfWork>();
	
	public void registerNew(User obj){
		if ((!dirtyObjects.contains(obj))&&(!newObjects.contains(obj))){
			//still need to make sure don't add duplicate object, handle the case if object is in the db already 
			newObjects.add(obj);
		}
		
	}
	public void registerDirty(String email,int user_id) throws SQLException{
		User obj1=new User();
		User obj2=new User();
		obj1=um1.find(user_id);//origional object
		obj2=obj1;
		obj2.setEmail(email);//object with updated email
		if (newObjects.contains(obj1)){
			newObjects.set(newObjects.indexOf(obj1), obj2);
		}
		else if (dirtyObjects.contains(obj1)){
			dirtyObjects.set(newObjects.indexOf(obj1), obj2);
		}
		else {
			dirtyObjects.add(obj2);
		}
		//!!Missing the case if object is not in database
	}
	
	
	public void commit() throws SQLException{
		insertNew();
		updateDirty();
	}
	public void insertNew() throws SQLException{
		for (Iterator<User> objects= newObjects.iterator(); objects.hasNext();){
			User obj=(User) objects.next();
			//Do insert 
			um1.insert(obj);
		}
	}
	public void updateDirty() throws SQLException{
		for (Iterator<User> objects= dirtyObjects.iterator(); objects.hasNext();){
			User obj=(User) objects.next();
			//Do insert 
			//Missing :MapperRegistery.getMapper(obj.getClass()).insert(obj);
			um1.updateEmail(obj.getEmail(), obj.getUser_id());
		}
	}
	
	public static void setCurrent(UnitOfWork uow){
		current.set(uow);
	}
	public static void newCurrent(){
		current.set(new UnitOfWork());
	}
	public static UnitOfWork getCurrent(){
		return current.get();
	}
	
	

}
