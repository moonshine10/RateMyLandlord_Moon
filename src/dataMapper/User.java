package dataMapper;

public class User  {

	 int user_id;
	 String username;
	 String password;
	 String occupation;
	 String birthday;
	 String email;
	
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

}
