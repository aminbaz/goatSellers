package business_logic.facades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Sale;
import data_access.ClubDAO;
import data_access.PlayerDAO;
import data_access.SaleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.tableViewCell.NewsCell;

public class MarketNewsFacade {
	private SaleDAO daoS;
	private ClubDAO daoC;
	private PlayerDAO daoP;
	private ObservableList<NewsCell> cellData = FXCollections.observableArrayList();

	public MarketNewsFacade(){
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		daoS = fact.getSaleDAO();
		daoC = fact.getClubDAO();
		daoP = fact.getPlayerDAO();

	}

	public ObservableList<NewsCell> getAllSales() throws SQLException{
		ResultSet result = daoS.getAllSales();
		while(result.next()){
			ResultSet player = daoP.getPlayer(result.getInt("player"));
			player.next();
			String oldClub = daoC.getNameClub(result.getInt("seller"));
			String newClub = daoC.getNameClub(result.getInt("buyer"));
			NewsCell cell = new NewsCell(player.getString("firstname"),player.getString("lastname"),player.getDate("birthdate"),player.getString("position"),oldClub,newClub,result.getInt("amount_sale"));
			cellData.add(cell);
		}
		return cellData;
	}

	public SaleDAO getDaoS() {
		return daoS;
	}

	public void setDaoS(SaleDAO daoS) {
		this.daoS = daoS;
	}

	public ClubDAO getDaoC() {
		return daoC;
	}

	public void setDaoC(ClubDAO daoC) {
		this.daoC = daoC;
	}

	public PlayerDAO getDaoP() {
		return daoP;
	}

	public void setDaoP(PlayerDAO daoP) {
		this.daoP = daoP;
	}

	public ObservableList<NewsCell> getCellData() {
		return cellData;
	}

	public void setCellData(ObservableList<NewsCell> cellData) {
		this.cellData = cellData;
	}


}
