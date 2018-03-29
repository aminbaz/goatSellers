package business_logic.facades;

import java.util.ArrayList;
import java.util.List;

import business_logic.factories.DAOFactory;
import business_logic.models.Club;
import data_access.ClubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.tableViewCell.ClubCell;

public class HomeAuthorityFacade {
	
	private ClubDAO dao;
	private ObservableList<ClubCell> cellData = FXCollections.observableArrayList();
	
	public HomeAuthorityFacade() {
		DAOFacade fac = new DAOFacade();
		DAOFactory fact = fac.getDAOFactory();
		dao = fact.getClubDAO();
		
		List<Club> list = getAllClub();
		for(int i=0;i<list.size();i++) {
			//System.out.println(cellData.size());
			Boolean isblock = isBlock(list.get(i).getId_club());
			int SumPurchases = getSumPurchases(list.get(i).getId_club());
			int SumSold = getSumSold(list.get(i).getId_club());
			int diff = SumSold - SumPurchases;
			ClubCell cell = new ClubCell((list.get(i).getLogo()),(list.get(i).getName()),isblock, diff, list.get(i).getId_club(), SumPurchases, SumSold);
			
			System.out.println(list.get(i).getId_club());
			
			System.out.println(isblock);
			cellData.add(cell);
		}
		
		
	
	}
	
	private int getSumSold(int id_club) {
		// TODO Auto-generated method stub
		return dao.getSumSold(id_club);
	}

	public int getSumPurchases(int idClub){
		return dao.getSumPurchases(idClub);
	}

	public Boolean changeState(int idClub){
		return dao.changeState(idClub);
	}
	
	public Boolean isBlock(int idClub){
		return dao.isBlock(idClub);
	}
	
	public void updateClub(String logo, String name, String city, String country, String championship){
		dao.updateClub(null, logo, name, city, country);
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
