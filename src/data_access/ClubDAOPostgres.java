package data_access;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import business_logic.models.Club;
import business_logic.models.Sale;
import business_logic.models.User;

public class ClubDAOPostgres extends ClubDAO{

	PostgresJDBC db;
	
	public ClubDAOPostgres(){
		db = PostgresJDBC.getInstance();
	}
	
	@Override
	public Sale[] getAllPurchases(int id) {
		Sale purchases[] = null;
		int cpt=0;
		
		String query="SELECT * FROM public.\"Sale\" WHERE buyer="+id;
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Sale mySale = new Sale(result.getInt("id_sale"),result.getInt("amount_sale"),result.getDate("sale_date"),result.getInt("seller"),result.getInt("buyer"),result.getInt("player"));
				purchases[cpt] = mySale;
				cpt=cpt+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchases;
	}

	@Override
	public Sale[] getAllSales(int id) {
		Sale sales[] = null;
		int cpt=0;
		
		String query="SELECT * FROM public.\"Sale\" WHERE seller="+id;
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Sale mySale = new Sale(result.getInt("id_sale"),result.getInt("amount_sale"),result.getDate("sale_date"),result.getInt("seller"),result.getInt("buyer"),result.getInt("player"));
				sales[cpt] = mySale;
				cpt=cpt+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sales;
	}
	
	@Override
	public void addClub(String logo, String name, String city, String country, String championship){
		// TODO Auto-generated method stub
		//String query = "INSERT INTO public.\"Club\" VALUES(" + logo+"," + name + "," + city + "," + country + "," + championship + ",";
	}
	
	@Override
	public void updateClub(String logo, String name, String city, String country, String championship) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Club[] getAllClub() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
