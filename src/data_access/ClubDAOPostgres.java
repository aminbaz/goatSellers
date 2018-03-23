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
	public void addClub(String logotmp, String nametmp, String mailtmp, String passwordtmp){
		Integer id_roleBd = 0;
		String queryIdRole = "select max(id_role) + 1 from public.\"Role\"";
		ResultSet numberRole=db.makeQuery(queryIdRole);
		try {
			if(numberRole.next()) {
				id_roleBd = numberRole.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String queryRole = "INSERT INTO public.\"Role\"(id_role, rolename)VALUES ("+ id_roleBd +", 'Club')";
		ResultSet resultRole=db.makeQuery(queryRole);
		
		Integer id_userBd = 0;
		String queryIdUser = "select max(id_user) + 1 from public.\"User\"";
		ResultSet numberUser=db.makeQuery(queryIdUser);
		try {
			if(numberUser.next()) {
				id_userBd = numberUser.getInt(1);
				System.out.println(id_userBd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryUser = "INSERT INTO public.\"User\"(id_user, mail, password, role)VALUES ("+ id_userBd +","+ mailtmp +","+passwordtmp+","+id_roleBd+")";
		ResultSet resultUser=db.makeQuery(queryUser);
		
		String queryClub = "INSERT INTO public.\"Club\"(id_club, name, logo, role, blocked)VALUES ("+ id_userBd +","+ nametmp +","+logotmp+","+id_roleBd+",'false')";
		ResultSet resultClub=db.makeQuery(queryClub);
		
		
		
	}
	
	@Override
	public void updateClub(String logo, String name, String mail, String password) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ArrayList<Club> getAllClub() {
		// TODO Auto-generated method stub
		ArrayList<Club> clubs;
		clubs = new ArrayList<Club>();
		
		String query="SELECT * FROM public.\"Club\"";
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Club myClub = new Club(result.getInt("id_club"),result.getString("name"),result.getString("logo"));
				clubs.add(myClub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clubs;
	}
	
}
