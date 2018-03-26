package data_access;

public class SaleDAOPostgres extends SaleDAO{
	
	PostgresJDBC db;
	
	public SaleDAOPostgres(){
		db = PostgresJDBC.getInstance();
	}
}
