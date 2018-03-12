package business_logic.facades;
import business_logic.models.User;
import data_access.UserDAOPostgres;

public class LoginFacade {

	private User connectedUser;
	private UserDAOPostgres dao;
	
	public LoginFacade() {
		dao = new UserDAOPostgres();
	}

	public boolean login(String mail, String password) {
		User user = dao.createUserById(mail, password);
		if(user==null) {
			return false;
		}else {
			connectedUser=user;
			return true;
		}
	}
	
	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public UserDAOPostgres getDao() {
		return dao;
	}

	public void setDao(UserDAOPostgres dao) {
		this.dao = dao;
	}
	
}
