package business_logic.models;

public class User {
	private int id_user;
	private String mail;
	private String password;
	private int role;

	public User(int id_user) {
		this.id_user = id_user;
	}
	
	public User(int id_user, String mail, String password, int role) {
		this.id_user=id_user;
		this.mail=mail;
		this.password=password;
		this.role=role;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getId() {
		return id_user;
	}

	public void setId(int id_user) {
		this.id_user = id_user;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
