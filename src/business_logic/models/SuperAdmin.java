package business_logic.models;

public class SuperAdmin extends User{

	public SuperAdmin(int id_user, String mail, String password, int role) {
		super(id_user,mail,password, role);
	}
}
