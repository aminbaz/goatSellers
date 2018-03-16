package business_logic.factories;

import data_access.UserDAO;
import data_access.UserDAOPostgres;

public class UserDAOFactory {
	
	public UserDAOFactory() {
		
	}
	
	public UserDAO getUserDAO() {
		return new UserDAOPostgres();
	}
}
