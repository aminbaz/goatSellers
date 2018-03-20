package data_access;

import business_logic.models.Club;
import business_logic.models.Sale;

public abstract class ClubDAO {
	public abstract Sale[] getAllPurchases(int id);
	public abstract Sale[] getAllSales(int id);
	public abstract void addClub(String logo, String name, String city, String country, String championship);
	public abstract void updateClub(String logo, String name, String city, String country, String championship);
	public abstract Club[] getAllClub();
}
