package data_access;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business_logic.models.Club;
import business_logic.models.Player;
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
				Player myPlayer;
				query="SELECT * FROM public.\"Player\" WHERE id_player="+result.getInt("player");
				ResultSet player=db.makeQuery(query);
				if(player.next()) {
					myPlayer = new Player(player.getInt("id_player"),player.getString("firstname"),player.getString("lastname"),player.getDate("birthdate"),player.getString("position"),player.getDate("contract"));
				}else {
					myPlayer=null;
				}
				Club mySeller;
				query="SELECT * FROM public.\"Club\" WHERE id_club="+result.getInt("seller");
				ResultSet seller=db.makeQuery(query);
				if(seller.next()) {
					mySeller = new Club(seller.getInt("id_club"),seller.getString("name"),seller.getString("logo"),seller.getInt("role"));
				}else {
					mySeller=null;
				}				
				Club myBuyer;
				query="SELECT * FROM public.\"Club\" WHERE id_club="+result.getInt("buyer");
				ResultSet buyer=db.makeQuery(query);
				if(buyer.next()) {
					myBuyer = new Club(buyer.getInt("id_club"),buyer.getString("name"),buyer.getString("logo"),buyer.getInt("role"));
				}else {
					myBuyer=null;
				}					
				Sale mySale = new Sale(result.getInt("id_sale"),result.getInt("amount_sale"),result.getDate("sale_date"),mySeller,myBuyer,myPlayer);
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
	public ArrayList<Sale> getAllSales(int id) {
		ArrayList<Sale> sales;
		sales = new ArrayList<Sale>();
		int cpt=0;
		
		String query="SELECT * FROM public.\"Sale\" WHERE seller="+id;
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Player myPlayer;
				query="SELECT * FROM public.\"Player\" WHERE id_player="+result.getInt("player");
				ResultSet player=db.makeQuery(query);
				if(player.next()) {
					myPlayer = new Player(player.getInt("id_player"),player.getString("firstname"),player.getString("lastname"),player.getDate("birthdate"),player.getString("position"),player.getDate("contract"));
				}else {
					myPlayer=null;
				}
				Club mySeller;
				query="SELECT * FROM public.\"Club\" WHERE id_club="+result.getInt("seller");
				ResultSet seller=db.makeQuery(query);
				if(seller.next()) {
					mySeller = new Club(seller.getInt("id_club"),seller.getString("name"),seller.getString("logo"),seller.getInt("role"));
				}else {
					mySeller=null;
				}				
				Club myBuyer;
				query="SELECT * FROM public.\"Club\" WHERE id_club="+result.getInt("buyer");
				ResultSet buyer=db.makeQuery(query);
				if(buyer.next()) {
					myBuyer = new Club(buyer.getInt("id_club"),buyer.getString("name"),buyer.getString("logo"),buyer.getInt("role"));
				}else {
					myBuyer=null;
				}					
				Sale mySale = new Sale(result.getInt("id_sale"),result.getInt("amount_sale"),result.getDate("sale_date"),mySeller,myBuyer,myPlayer);
				sales.add(mySale);
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
