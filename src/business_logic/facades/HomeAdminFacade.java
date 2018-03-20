package business_logic.facades;
import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import data_access.ClubDAO;

public class HomeAdminFacade {
	
	private ClubDAO dao;
	
	public HomeAdminFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
	}

	public void addClub(String logo, String name, String city, String country, String championship){
		
	}
	
	public void updateClub(String logo, String name, String city, String country, String championship){
		
	}
	
	public Club [] getAllClub(){
		return null;
		
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void setDao(ClubDAO dao) {
		this.dao = dao;
	}
	
}
