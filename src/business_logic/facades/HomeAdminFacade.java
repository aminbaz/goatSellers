package business_logic.facades;
import java.util.ArrayList;
import java.util.List;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Sale;
import data_access.ClubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presentation.ClientUI;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.ClubCell;
import presentation.tableViewCell.SaleCell;

public class HomeAdminFacade {
	
	private ClubDAO dao;
	private ObservableList<AdminCell> cellData = FXCollections.observableArrayList();
	
	public HomeAdminFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		
		List<Club> list = getAllClub();
		for(int i=0;i<list.size();i++) {
			System.out.println(cellData.size());
			AdminCell cell = new AdminCell((list.get(i).getId_club()),(list.get(i).getLogo()),(list.get(i).getName()));
			cellData.add(cell);
		}
	}

	public void addClub(String logo, String name, String mail, String password){
		dao.addClub(logo, name, mail, password);
	}
	
	public void updateClub(Integer idClub, String logo, String name, String mail, String password){
		dao.updateClub(idClub, logo, name, mail, password);
	}
	
	public ArrayList<Club> getAllClub() {
		return dao.getAllClub();
	}

	public ObservableList<AdminCell> getCellData(){
		return cellData;
	}
	
	public ClubDAO getDao() {
		return dao;
	}

	public void setDao(ClubDAO dao) {
		this.dao = dao;
	}
	
}
