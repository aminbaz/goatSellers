package data_access;

import java.sql.ResultSet;
import java.sql.SQLException;

import business_logic.models.Player;

public class PlayerDAOPostgres extends PlayerDAO{

	PostgresJDBC db;
	
	public PlayerDAOPostgres(){
		db = PostgresJDBC.getInstance();
	}
	
	@Override
	public ResultSet getPlayer(int id) {
		String query="SELECT * FROM public.\"Player\" WHERE id_player="+id;
		ResultSet result=db.makeQuery(query);
		return result;
	}


}
