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
		String queryDelete="DELETE FROM public.\"UpToSale\" WHERE player="+idPlayer+" AND club="+idClub;
		db.makeQueryUpdate(queryDelete);
	}

}
