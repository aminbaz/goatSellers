package business_logic.facades;
import business_logic.factories.DAOFactory;
import business_logic.models.User;
import data_access.UserDAO;

public class LoginFacade {

	private UserDAO dao;

	public LoginFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
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
