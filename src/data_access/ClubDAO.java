package data_access;

import business_logic.models.Club;
import business_logic.models.Sale;

public abstract class ClubDAO {
	public abstract Sale[] getAllPurchases(int id);
	public abstract Sale[] getAllSales(int id);
}
