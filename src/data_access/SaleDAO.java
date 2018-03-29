package data_access;

import java.sql.ResultSet;

public abstract class SaleDAO {
	public abstract ResultSet getAllSales();
	public abstract void addUpToSale(int minprice, int idClub, int idPlayer);
	public abstract void deleteUpToSale(int idClub, int idPlayer);
	public abstract ResultSet getAllOffersPlayer(int idClub, int idPlayer);
	public abstract void declineOffer(int id);
	public abstract void AcceptOffer(int id, int buyer, int player);
}
