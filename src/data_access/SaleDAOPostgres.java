package data_access;

import java.sql.ResultSet;
import java.sql.SQLException;

import business_logic.models.Club;
import presentation.ClientUI;

public class SaleDAOPostgres extends SaleDAO{

	PostgresJDBC db;

	public SaleDAOPostgres(){
		db = PostgresJDBC.getInstance();
	}

	@Override
	public ResultSet getAllSales() {
		String query="SELECT * FROM public.\"Sale\" ORDER BY sale_date DESC";
		ResultSet result=db.makeQuery(query);
		return result;
	}

	@Override
	public void addUpToSale(int minprice, int idClub, int idPlayer) {
		String query="SELECT MAX(id_uptosale) as nb FROM public.\"UpToSale\"";
		ResultSet result=db.makeQuery(query);
		int nb=0;
		try {
			if(result.next()) {
				nb=result.getInt("nb");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String queryInsert="INSERT INTO public.\"UpToSale\" VALUES("+(nb+1)+","+minprice+",current_date,"+idClub+","+idPlayer+")";
		db.makeQueryUpdate(queryInsert);
	}

	@Override
	public void deleteUpToSale(int idClub, int idPlayer) {
		String query="SELECT id_uptosale FROM public.\"UpToSale\" WHERE player="+idPlayer+" AND club="+idClub;
		ResultSet result = db.makeQuery(query);
		int id=0;
		try {
			if(result.next()) {
				id=result.getInt("id_uptosale");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query="UPDATE public.\"Offer\" SET status='declined' WHERE id_uptosale="+id;
		db.makeQueryUpdate(query);
		query="UPDATE public.\"Offer\" SET id_uptosale=null WHERE id_uptosale="+id;
		db.makeQueryUpdate(query);
		String queryDelete="DELETE FROM public.\"UpToSale\" WHERE player="+idPlayer+" AND club="+idClub;
		db.makeQueryUpdate(queryDelete);
	}

	@Override
	public ResultSet getAllOffersPlayer(int idClub, int idPlayer) {
		String query="SELECT o.id_offer, o.amount, c.name, o.club FROM public.\"Offer\" o, public.\"Club\" c, public.\"UpToSale\" u WHERE o.club=c.id_club AND o.id_uptosale=u.id_uptosale AND o.status LIKE 'in progress' AND u.club="+idClub+" AND u.player="+idPlayer;
		ResultSet result = db.makeQuery(query);
		return result;
	}

	@Override
	public void declineOffer(int id) {
		String query="UPDATE public.\"Offer\" SET status='declined' WHERE id_offer="+id;
		db.makeQueryUpdate(query);
	}

	@Override
	public void AcceptOffer(int id, int buyer, int player) {
		String query="SELECT id_uptosale, amount FROM public.\"Offer\" WHERE id_offer="+id;
		ResultSet result=db.makeQuery(query);	
		int uptosale=0;
		int amount=0;
		try {
			result.next();
			uptosale = result.getInt("id_uptosale");
			amount = result.getInt("amount");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query="UPDATE public.\"Offer\" SET status='declined' WHERE id_offer!="+id+" AND id_uptosale="+uptosale;
		db.makeQueryUpdate(query);
		query="UPDATE public.\"Offer\" SET status='accepted' WHERE id_offer="+id+" AND id_uptosale="+uptosale;
		db.makeQueryUpdate(query);
		query="UPDATE public.\"Offer\" SET id_uptosale=null WHERE id_offer!="+id+" AND id_uptosale="+uptosale;
		db.makeQueryUpdate(query);
		query="UPDATE public.\"Offer\" SET id_uptosale=null WHERE id_offer="+id+" AND id_uptosale="+uptosale;
		db.makeQueryUpdate(query);
		query="SELECT club FROM public.\"UpToSale\" WHERE id_uptosale="+uptosale;
		result=db.makeQuery(query);	
		int seller=0;
		try {
			result.next();
			seller = result.getInt("club");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query="DELETE FROM public.\"UpToSale\" WHERE id_uptosale="+uptosale;
		db.makeQueryUpdate(query);
		query="SELECT Max(id_sale) as nb FROM public.\"Sale\"";
		result=db.makeQuery(query);	
		int nb=0;
		try {
			result.next();
			nb = result.getInt("nb");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query="INSERT INTO public.\"Sale\" VALUES("+(nb+1)+","+amount+",current_date,"+seller+","+buyer+","+player+")";
		db.makeQueryUpdate(query);
		query="UPDATE public.\"Player\" SET club="+buyer+" WHERE id_player="+player;
		db.makeQueryUpdate(query);
	}

}
