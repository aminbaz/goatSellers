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
		String queryId = "SELECT u.id_user FROM public.\"User\" u, public.\"Role\" r, public.\"Club\" c WHERE c.role=r.id_role AND r.id_role=u.role AND c.id_club="+idClub;
		ResultSet result = db.makeQuery(queryId);
		try {
			result.next();
			String queryUser = "UPDATE public.\"User\" SET mail='"+mail+"', password='"+password+"' WHERE id_user = "+result.getInt("id_user");
			db.makeQueryUpdate(queryUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (isBlock(idClub) == false) {
			String queryClub = "UPDATE public.\"Club\" SET blocked='true' WHERE id_club = "+idClub;
			db.makeQueryUpdate(queryClub);
			return true;
		}
		else {
			String queryClub = "UPDATE public.\"Club\" SET blocked='false' WHERE id_club = "+idClub;
			db.makeQueryUpdate(queryClub);
			return false;
		}

		
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
		String query="INSERT INTO public.\"Offer\" VALUES ("+id+","+price+",current_date,'in progress',"+id_uptosale+","+id_club+")";
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
	
	@Override
	public String getMailClub(int id) {
		String query="SELECT u.mail FROM public.\"User\" u, public.\"Role\" r, public.\"Club\" c WHERE c.id_club="+id +"and c.role = r.id_role and u.role = r.id_role";
		ResultSet result=db.makeQuery(query);
		try {
			if(result.next()) {
				return result.getString("mail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getPasswordClub(int id) {
		String query="SELECT u.password FROM public.\"User\" u, public.\"Role\" r, public.\"Club\" c WHERE c.id_club="+id +"and c.role = r.id_role and u.role = r.id_role";
		ResultSet result=db.makeQuery(query);
		try {
			if(result.next()) {
				return result.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<Player> getAllPlayer(int idClub) {
		// TODO Auto-generated method stub
		ArrayList<Player> players;
		players = new ArrayList<Player>();
		
		String query="SELECT * FROM public.\"Player\" WHERE club="+idClub;
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Player myPlayer = new Player(result.getInt("id_player"),result.getString("firstname"),result.getString("lastname"),result.getDate("birthdate"),result.getString("position"),result.getDate("contract"));
				players.add(myPlayer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return players;
	}
	
	@Override
	public int getSumPurchases(int idclub) {
		int Sum = 0;
		String query="SELECT amount_sale FROM public.\"Sale\"  WHERE buyer="+idclub +"";
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Sum = Sum + result.getInt("amount_sale");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Sum;
	}	
	
	@Override
	public int getSumSold(int idclub) {
		int Sum = 0;
		String query="SELECT amount_sale FROM public.\"Sale\"  WHERE seller="+idclub +"";
		ResultSet result=db.makeQuery(query);
		try {
			while(result.next()) {
				Sum = Sum + result.getInt("amount_sale");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Sum;
	}
}
