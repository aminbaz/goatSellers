package business_logic.facades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Sale;
import data_access.ClubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.ClientUI;
import presentation.tableViewCell.OfferCell;
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.SaleCell;

public class HomeClubFacade {

	private ClubDAO dao;
	private ObservableList<OnSaleCell> cellData = FXCollections.observableArrayList();
	private ObservableList<OfferCell> offerCellData = FXCollections.observableArrayList();
	
	public HomeClubFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
	}
	
	public ObservableList<OnSaleCell> getCellData(){
		return cellData;
	}
	
	public ObservableList<OfferCell> getOfferCellData(){
		return offerCellData;
	}
	
	public ObservableList<OnSaleCell> getAllUpToSales() {
		Club user = (Club) ClientUI.getMyUser();
		ResultSet result = dao.getAllUpToSales(user.getRole());
		try {
			while(result.next()) {
				float amount = (float) (result.getInt("minprice")/1000000.00);
				OnSaleCell cell = new OnSaleCell(result.getString("logo"),result.getString("name"),result.getString("firstname"),result.getString("lastname"),result.getDate("birthdate"),result.getInt("minprice"),result.getInt("id_uptosale"));
				cellData.add(cell);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCellData();
	}
	
	public ObservableList<OfferCell> getAllClubOffers() {
		Club myClub = (Club)ClientUI.getMyUser();
		ResultSet result = dao.getAllClubOffers(myClub.getId_club());
		try {
			while(result.next()) {
				OfferCell cell = new OfferCell(result.getString("firstname"),result.getString("lastname"),result.getInt("amount"),result.getString("status"));
				offerCellData.add(cell);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getOfferCellData();
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void ClubDao(ClubDAO dao) {
		this.dao = dao;
	}
	
	public void updateUpToSale(int id, int price) {
		dao.updateUpToSale(id, price);
	}
	
	public void makeAnOffer(int id_uptosale, int id_club, int price) {
		dao.makeAnOffer(id_club,id_uptosale,price);
	}
}
