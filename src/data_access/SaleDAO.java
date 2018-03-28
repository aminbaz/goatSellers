package data_access;

import java.sql.ResultSet;

public abstract class SaleDAO {
	public abstract ResultSet getAllSales();
	public abstract void addUpToSale(int minprice, int idClub, int idPlayer);
	public abstract void deleteUpToSale(int idClub, int idPlayer);
}
