package business_logic.facades;

import business_logic.factories.DAOFactory;
import business_logic.factories.PostgresDAOFactory;
import data_access.ClubDAO;
import data_access.PlayerDAO;
import data_access.SaleDAO;
import data_access.UserDAO;

public class DAOFacade {
	
	public DAOFactory getDAOFactory() {
		return PostgresDAOFactory.getInstance();
	}
	
	public UserDAO getUserDAO() {
		return this.getDAOFactory().getUserDAO();
	}
	
	public PlayerDAO getPlayerDAO() {
		return this.getDAOFactory().getPlayerDAO();
	}	

	public ClubDAO getClubDAO() {
		return this.getDAOFactory().getClubDAO();
	}		
	
	public SaleDAO getSaleDAO() {
		return this.getDAOFactory().getSaleDAO();
	}			
}
