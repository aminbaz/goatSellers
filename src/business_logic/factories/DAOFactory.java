package business_logic.factories;

import data_access.PlayerDAO;
import data_access.PostgresJDBC;
import data_access.UserDAO;
import data_access.UserDAOPostgres;

public abstract class DAOFactory {
	
	protected static DAOFactory myFactory = null;
	
	public DAOFactory() {
		
	}
	
	public abstract UserDAO getUserDAO();
	public abstract PlayerDAO getPlayerDAO();
	
	public static DAOFactory getInstance() {
		return myFactory;
	}
}