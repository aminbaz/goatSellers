package business_logic.models;

import java.sql.Date;

public class Player {
	
	private int id_player;
	private String firstname;
	private String lastname;
	private Date birthdate;
	private String position;
	private Date contract;
	
	public Player(int id_player, String firstname, String lastname, Date birthdate, String position, Date contract) {
		this.id_player = id_player;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.position = position;
		this.contract = contract;
	}
	
	public int getId_player() {
		return id_player;
	}
	public void setId_player(int id_player) {
		this.id_player = id_player;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getContract() {
		return contract;
	}
	public void setContract(Date contract) {
		this.contract = contract;
	}
	
}
