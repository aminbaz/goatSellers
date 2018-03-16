package business_logic.facades;

import business_logic.factories.DAOFactory;
import business_logic.factories.PostgresDAOFactory;
import data_access.UserDAO;

public class DAOFacade {
	
	public DAOFactory getDAOFactory() {
		return PostgresDAOFactory.getInstance();
	}
	
	public UserDAO getUserDAO() {
		return this.getDAOFactory().getUserDAO();
	}
	
}
