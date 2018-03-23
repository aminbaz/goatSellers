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
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.SaleCell;

public class HomeClubFacade {

	private ClubDAO dao;
	private ObservableList<OnSaleCell> cellData = FXCollections.observableArrayList();
	
	public HomeClubFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		
		ResultSet result = getAllUpToSales();
		try {
			while(result.next()) {
				OnSaleCell cell = new OnSaleCell(result.getString("name"),result.getString("firstname"),result.getString("lastname"),result.getDate("birthdate"),result.getInt("minprice"),result.getInt("id_uptosale"));
				cellData.add(cell);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObservableList<OnSaleCell> getCellData(){
		return cellData;
	}
	
	public ResultSet getAllUpToSales() {
		Club user = (Club) ClientUI.getMyUser();
		return dao.getAllUpToSales(user.getRole());
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void ClubDao(ClubDAO dao) {
		this.dao = dao;
	}
}
