package business_logic.facades;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Player;
import business_logic.models.Sale;
import business_logic.models.User;
import data_access.ClubDAO;
import data_access.PlayerDAO;
import data_access.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.ClientUI;
import presentation.tableViewCell.SaleCell;

public class HistoricFacade {

	private ClubDAO dao;
	private ObservableList<SaleCell> cellData = FXCollections.observableArrayList();
	
	public HistoricFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		//cellData = new ObservableArrayList<SaleCell>();
		System.out.println("new Historicfacade");
		List<Sale> list = getAllSales();
		for(int i=0;i<list.size();i++) {
			System.out.println(cellData.size());
			SaleCell cell = new SaleCell(list.get(i).getPlayer().getFirstname(),list.get(i).getPlayer().getLastname(),list.get(i).getSale_date().toString());
			cellData.add(cell);
		}
	}
	
	public ObservableList<SaleCell> getCellData(){
		return cellData;
	}
	
	public ArrayList<Sale> getAllSales() {
		Club user = (Club) ClientUI.getMyUser();
		return dao.getAllSales(user.getId_club());
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void ClubDao(ClubDAO dao) {
		this.dao = dao;
	}
}
