package data_access;

import java.util.Date;
import java.sql.ResultSet;

import business_logic.models.Player;

public abstract class PlayerDAO {
	public abstract ResultSet getPlayer(int id);
	public abstract void addPlayer(Player player, int club);
	public abstract void updatePlayer(Integer id_player, String firstName, String lastName, Date birthDate, String position, Date contrat);
	public abstract int maxId();
}
