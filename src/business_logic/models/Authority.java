package business_logic.models;

public class Authority extends User{
	
	int id_authority;
	String name;
	
	public Authority(int id_user, String mail, String password) {
		super(id_user,mail,password);
	}
	
	public Authority(int id_user, String mail, String password, int id_authority, String name) {
		super(id_user,mail,password);
		this.id_authority=id_authority;
		this.name=name;
	}

	public int getId_authority() {
		return id_authority;
	}

	public void setId_authority(int id_authority) {
		this.id_authority = id_authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
