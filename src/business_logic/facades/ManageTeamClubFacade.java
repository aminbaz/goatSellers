package business_logic.facades;

import java.io.DataOutput;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import business_logic.models.Player;
import data_access.ClubDAO;
import data_access.PlayerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.ClientUI;
import presentation.tableViewCell.PlayerCell;

public class ManageTeamClubFacade {
	
	private ClubDAO dao;
	private PlayerDAO daoP;
	private ObservableList<PlayerCell> cellData = FXCollections.observableArrayList();
	
	public ManageTeamClubFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		daoP = fact.getPlayerDAO();
	}
	
	public ObservableList<PlayerCell> getCellData(){
		return cellData;
	}
	
	public ObservableList<PlayerCell> getAllPlayer() {
		Club user = (Club) ClientUI.getMyUser();
		ArrayList<Player> result = dao.getAllPlayer(user.getId_club());
		for(int i=0;i<result.size();i++) {
			PlayerCell cell = new PlayerCell((result.get(i).getId_player()), (result.get(i).getFirstname()),(result.get(i).getLastname()),(result.get(i).getBirthdate()),(result.get(i).getPosition()),(result.get(i).getContract()));
			cellData.add(cell);
		}
		return getCellData();
	}
	
	public void addPlayer(String firstname, String lastname, LocalDate birthdate, String position, LocalDate endContract) {
		int id=daoP.maxId();
		Club user = (Club) ClientUI.getMyUser();
		int id_club= user.getId_club();
		Date newBirthdate = (Date) Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date newContract = (Date) Date.from(endContract.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Player myPlayer = new Player(id,firstname, lastname, newBirthdate, position, newContract);
		daoP.addPlayer(myPlayer, id_club);
	}
	
	public void updatePlayer(Integer id_player, String firstName, String lastName, Date birthDate, String position, Date contrat){
		daoP.updatePlayer(id_player, firstName, lastName, birthDate, position, contrat);
	}

	public ClubDAO getDao() {
		return dao;
	}

	public void ClubDao(ClubDAO dao) {
		this.dao = dao;
	}

}
