package data_access;

import java.sql.ResultSet;

import business_logic.models.Player;

public abstract class PlayerDAO {
	public abstract ResultSet getPlayer(int id);
	public abstract void addPlayer(Player player, int club);
	public abstract int maxId();
}
