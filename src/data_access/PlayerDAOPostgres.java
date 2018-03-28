package data_access;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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

	@Override
	public void addPlayer(Player player, int club) {
		
		final String NEW_FORMAT = "yyyy-MM-dd";
		String newBirthdate;
		String newContract;

		SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
		newBirthdate = sdf.format(player.getBirthdate());
		newContract = sdf.format(player.getContract());
		
		String query="INSERT INTO public.\"Player\" VALUES ("+player.getId_player()+",'"+player.getFirstname()+"','"+player.getFirstname()+"',"+newBirthdate+",'"+player.getPosition()+"',"+newContract+","+club+")";                                      
		db.makeQueryUpdate(query);
	}
	
	@Override
	public void updatePlayer(Integer id_player, String firstName, String lastName, Date birthDate, String position, Date contrat) {
		
		final String NEW_FORMAT = "yyyy-MM-dd";
		String newBirthdate;
		String newContract;

		SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
		newBirthdate = sdf.format(birthDate);
		newContract = sdf.format(contrat);
		
		String queryPlayer = "UPDATE public.\"Player\" SET firstname='"+firstName+"', lastname='"+lastName+"', birthdate='"+newBirthdate+"', position='"+position+"', contract='"+newContract+"' WHERE id_player = "+id_player;
		db.makeQueryUpdate(queryPlayer);
	}

	@Override
	public int maxId() {
		String query="SELECT Max(id_player) as nb FROM public.\"Player\"";
		ResultSet result=db.makeQuery(query);
		try {
			result.next();
			return result.getInt("nb");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
