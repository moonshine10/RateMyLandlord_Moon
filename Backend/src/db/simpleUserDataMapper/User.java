package db.simpleUserDataMapper;

public class User {

	private int user_id;
	private String username;
	private String password;
	private String occupation;
	private String birthday;
	private String email;
	
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
