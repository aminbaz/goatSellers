package business_logic.factories;

import data_access.UserDAO;
import data_access.UserDAOPostgres;

public class PostgresDAOFactory extends DAOFactory{

	public PostgresDAOFactory() {
		super();
	}
	
	@Override
	public UserDAO getUserDAO() {
		return new UserDAOPostgres();
	}

	public static DAOFactory getInstance() {
		if(myFactory == null) {
			myFactory = new PostgresDAOFactory();
		}
		return myFactory;			
	}
}
