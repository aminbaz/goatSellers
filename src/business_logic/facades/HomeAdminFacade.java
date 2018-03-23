package business_logic.facades;
import java.util.ArrayList;
import java.util.List;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Sale;
import data_access.ClubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.ClientUI;
import presentation.tableViewCell.ClubCell;
import presentation.tableViewCell.SaleCell;

public class HomeAdminFacade {
	
	private ClubDAO dao;
	private ObservableList<ClubCell> cellData = FXCollections.observableArrayList();
	
	public HomeAdminFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		
		List<Club> list = getAllClub();
		for(int i=0;i<list.size();i++) {
			System.out.println(cellData.size());
			ClubCell cell = new ClubCell((list.get(i).getLogo()),(list.get(i).getName()),(list.get(i).getState()));
			cellData.add(cell);
		}
	}

	public void addClub(String logo, String name, String city, String country, String championship){
		dao.addClub(logo, name, city, country, championship);
	}
	
	public void updateClub(String logo, String name, String city, String country, String championship){
		dao.updateClub(logo, name, city, country, championship);
	}
	
	public ArrayList<Club> getAllClub() {
		return dao.getAllClub();
	}

	public ObservableList<ClubCell> getCellData(){
		return cellData;
	}
	
	public ClubDAO getDao() {
		return dao;
	}

	public void setDao(ClubDAO dao) {
		this.dao = dao;
	}
	
}
