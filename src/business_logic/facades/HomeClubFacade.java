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
	}
	
	public ObservableList<OnSaleCell> getCellData(){
		return cellData;
	}
	
	public void initAllUpToSales() {
		Club user = (Club) ClientUI.getMyUser();
		ResultSet result = dao.getAllUpToSales(user.getRole());
		try {
			while(result.next()) {
				float amount = (float) (result.getInt("minprice")/1000000.00);
				OnSaleCell cell = new OnSaleCell(result.getString("name"),result.getString("firstname"),result.getString("lastname"),result.getDate("birthdate"),result.getInt("minprice"),result.getInt("id_uptosale"));
				System.out.println(result.getString("firstname")+" "+result.getString("lastname"));
				cellData.add(cell);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void ClubDao(ClubDAO dao) {
		this.dao = dao;
	}
}
