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
		dao.addClub(logo, name, city, country, championship);
	}
	
	public void updateClub(String logo, String name, String city, String country, String championship){
		dao.updateClub(logo, name, city, country, championship);
	}
	
	public Club [] getAllClub(){
		return dao.getAllClub();
		
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void setDao(ClubDAO dao) {
		this.dao = dao;
	}
	
}
