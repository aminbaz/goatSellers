package business_logic.facades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Player;
import data_access.ClubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.ClientUI;
import presentation.tableViewCell.PlayerCell;

public class ManageTeamClubFacade {
	
	private ClubDAO dao;
	private ObservableList<PlayerCell> cellData = FXCollections.observableArrayList();
	
	public ManageTeamClubFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();

	}
	
	public ObservableList<PlayerCell> getCellData(){
		return cellData;
	}
	
	public ObservableList<PlayerCell> getAllPlayer() {
		Club user = (Club) ClientUI.getMyUser();
		ArrayList<Player> result = dao.getAllPlayer(user.getId_club());
		for(int i=0;i<result.size();i++) {
			PlayerCell cell = new PlayerCell((result.get(i).getFirstname()),(result.get(i).getLastname()),(result.get(i).getBirthdate()),(result.get(i).getPosition()),(result.get(i).getContract()));
			cellData.add(cell);
		}
		return getCellData();
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void ClubDao(ClubDAO dao) {
		this.dao = dao;
	}

}
