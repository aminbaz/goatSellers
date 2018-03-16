package data_access;
import business_logic.models.User;

public abstract class UserDAO {
	public abstract User createUserById(String mail, String password);
}
