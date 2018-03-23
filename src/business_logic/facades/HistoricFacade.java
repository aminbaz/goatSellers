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
	private ObservableList<SaleCell> cellDataP = FXCollections.observableArrayList();
	
	public HistoricFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		//cellData = new ObservableArrayList<SaleCell>();
		System.out.println("new Historicfacade");
		
		List<Sale> list = getAllSales();
		for(int i=0;i<list.size();i++) {
			float amount = (float) (list.get(i).getAmount()/1000000.00);
			SaleCell cell = new SaleCell((list.get(i).getPlayer().getFirstname()+" "+list.get(i).getPlayer().getLastname()),(Float.toString(amount)+ " M€"),list.get(i).getSale_date().toString());
			cellData.add(cell);
		}
		
		List<Sale> listP = getAllPurchases();
		for(int i=0;i<listP.size();i++) {
			float amount = (float) (listP.get(i).getAmount()/1000000.00);
			SaleCell cell = new SaleCell((listP.get(i).getPlayer().getFirstname()+" "+listP.get(i).getPlayer().getLastname()),(Float.toString(amount)+ " M€"),listP.get(i).getSale_date().toString());
			cellDataP.add(cell);
		}
	}

	public ObservableList<SaleCell> getCellDataP(){
		return cellDataP;
	}
	
	public ObservableList<SaleCell> getCellData(){
		return cellData;
	}
	
	public ArrayList<Sale> getAllPurchases() {
		Club user = (Club) ClientUI.getMyUser();
		return dao.getAllPurchases(user.getId_club());
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
