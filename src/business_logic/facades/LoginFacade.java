package business_logic.facades;
import business_logic.factories.UserDAOFactory;
import business_logic.models.User;
import data_access.UserDAO;

public class LoginFacade {

	private UserDAO dao;
	
	public LoginFacade() {
		UserDAOFactory fact = new UserDAOFactory();
		dao = fact.getUserDAO();
	}

	public User login(String mail, String password) {
		return dao.createUserById(mail, password);
	}

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
	
}
