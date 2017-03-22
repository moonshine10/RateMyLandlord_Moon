package dataMapper;

import java.sql.SQLException;

public class User  {
	
	int user_id;
	private String username;
	private String password;
	private String occupation;
	private String birthday;
	private String email;

	 public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	 
	
	public User(){
		this.user_id=0;
		this.username=null;
		this.password=null;
		this.occupation=null;
		this.birthday=null;
		this.email=null;
		
	}
	public User(int user_idArg, String usernameArg, String passwordArg, String occupationArg, String birthdayArg,
			String emailArg) {
		// TODO Auto-generated constructor stub
		this.user_id=user_idArg;
		this.username=usernameArg;
		this.password=passwordArg;
		this.occupation=occupationArg;
		this.birthday=birthdayArg;
		this.email=emailArg;
		
	}
	
	protected void markNew(){
		UnitOfWork.getCurrent().registerNew(this);
	}

	protected void markDirty() throws SQLException{
		UnitOfWork.getCurrent().registerDirty(this.email,this.user_id);//!!duplicated work, define email on user--> grab email--> define another user and pass it on 
	}
	
	public static User creatInDB(int user_idArg, String usernameArg, String passwordArg, String occupationArg, String birthdayArg,
			String emailArg){ 
		User obj=new User( user_idArg,  usernameArg,  passwordArg,  occupationArg,  birthdayArg,
				 emailArg);
		obj.markNew();
		
		return obj;
	}
	
	public void UOWsetEmail(String emailIn) throws SQLException{ 
		this.email=emailIn;
		markDirty();
		
		
	}
	
	
}
