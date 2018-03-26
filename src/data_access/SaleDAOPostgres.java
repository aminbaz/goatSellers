package data_access;

import java.sql.ResultSet;

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

}
