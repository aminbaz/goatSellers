package business_logic.factories;

import data_access.PlayerDAO;
import data_access.PlayerDAOPostgres;
import data_access.UserDAO;
import data_access.UserDAOPostgres;

public class PostgresDAOFactory extends DAOFactory{

	public PostgresDAOFactory() {
		super();
	}

	public static DAOFactory getInstance() {
		if(myFactory == null) {
			myFactory = new PostgresDAOFactory();
		}
		return myFactory;			
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOPostgres();
	}	
	
	@Override
	public PlayerDAO getPlayerDAO() {
		// TODO Auto-generated method stub
		return new PlayerDAOPostgres();
	}
}
