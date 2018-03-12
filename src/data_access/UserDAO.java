package data_access;
import business_logic.models.User;

abstract class UserDAO {
	abstract User createUserById(String mail, String password);
}
