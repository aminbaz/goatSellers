package data_access;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import business_logic.models.Club;
import business_logic.models.Player;
import business_logic.models.Sale;

public abstract class ClubDAO {
	public abstract ArrayList<Sale> getAllPurchases(int id);
	public abstract ArrayList<Sale> getAllSales(int id);
	public abstract void addClub(String logo, String name, String mail, String password);
	public abstract void updateClub(Integer idClub, String logo, String name, String mail, String password);
	public abstract ResultSet getAllUpToSales(int id);
	public abstract ArrayList<Club> getAllClub();
	public abstract Boolean changeState(int idClub);
	public abstract Boolean isBlock(int idClub);
	public abstract void updateUpToSale(int id, int price);
	public abstract void makeAnOffer(int id_club, int id_uptosale, int price);
	public abstract ResultSet getAllClubOffers(int id);
	public abstract String getNameClub(int id);
	public abstract String getMailClub(int id);
	public abstract String getPasswordClub(int id);
	public abstract ArrayList<Player> getAllPlayer(int idClub);
	public abstract int getSumPurchases(int idclub);
	public abstract int getSumSold(int idclub);
	
}
