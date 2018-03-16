package business_logic.facades;
import business_logic.models.User;
import data_access.UserDAOPostgres;

public class LoginFacade {

	private UserDAOPostgres dao;
	
	public LoginFacade() {
		dao = new UserDAOPostgres();
	}

	public User login(String mail, String password) {
		return dao.createUserById(mail, password);
	}
	

	public UserDAOPostgres getDao() {
		return dao;
	}

	public void setDao(UserDAOPostgres dao) {
		this.dao = dao;
	}
	
}
