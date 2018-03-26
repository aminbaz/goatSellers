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
	public ArrayList<Sale> getAllPurchases(int id) {
		ArrayList<Sale> purchases;
		purchases = new ArrayList<Sale>();
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
				purchases.add(mySale);
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
		String queryRole = "INSERT INTO public.\"Role\" VALUES ("+ id_roleBd +", 'Club')";
		db.makeQueryUpdate(queryRole);
		
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
		
		String queryUser = "INSERT INTO public.\"User\" VALUES ("+ id_userBd +",'"+ mailtmp +"','"+passwordtmp+"',"+id_roleBd+")";
		db.makeQueryUpdate(queryUser);
		
		String queryClub = "INSERT INTO public.\"Club\" VALUES ("+ id_userBd +",'"+ nametmp +"','"+logotmp+"',"+id_roleBd+",'false')";
		db.makeQueryUpdate(queryClub);
		
		
		
	}
	
	@Override
	public void updateClub(Integer idClub, String logo, String name, String mail, String password) {
		String queryClub = "UPDATE public.\"Club\" SET name='"+name+"', logo='"+logo+"' WHERE id_club = "+idClub;
		db.makeQueryUpdate(queryClub);
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

	@Override
	public Boolean changeState(int idClub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isBlock(int idClub) {
		// TODO Auto-generated method stub
		String query="SELECT blocked FROM public.\"Club\" WHERE id_Club="+idClub;
		ResultSet result=db.makeQuery(query);
		
		try {
			if (result.next()) {
				return result.getBoolean("blocked");
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public ResultSet getAllUpToSales(int id) {
		String query="SELECT u.id_uptosale, u.minprice, p.firstname, p.lastname, p.birthdate, c.name, c.logo FROM public.\"UpToSale\" u, public.\"Player\" p, public.\"Club\" c WHERE u.club=c.id_club AND u.player=p.id_player AND c.role!="+id;
		ResultSet result=db.makeQuery(query);
		return result;
	}
	
	public void updateUpToSale(int id, int price) {
		String query="UPDATE public.\"UpToSale\" SET minprice="+price+" WHERE id_uptosale="+id;
		db.makeQueryUpdate(query);
	}
	
	public void makeAnOffer(int id_club, int id_uptosale, int price) {
		int id = 0;
		String queryIdUser = "select max(id_offer) + 1 from public.\"Offer\"";
		ResultSet maxIdOffer=db.makeQuery(queryIdUser);
		try {
			if(maxIdOffer.next()) {
				id = maxIdOffer.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query="INSERT INTO public.\"Offer\" VALUES ("+id+","+price+",current_date,'en cours',"+id_uptosale+","+id_club+")";
		db.makeQueryUpdate(query);
	}
	
	public ResultSet getAllClubOffers(int id) {
		System.out.println(id);
		String query="SELECT o.amount, o.status, p.firstname, p.lastname FROM public.\"Offer\" o, public.\"UpToSale\" u, public.\"Player\" p WHERE p.id_player=u.player AND u.id_uptosale=o.id_uptosale AND o.club="+id;
		ResultSet result=db.makeQuery(query);
		return result;
	}

	@Override
	public String getNameClub(int id) {
		String query="SELECT name FROM public.\"Club\" WHERE id_club="+id;
		ResultSet result=db.makeQuery(query);
		try {
			if(result.next()) {
				return result.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
