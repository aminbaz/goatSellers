package business_logic.models;

public class Club extends User{
	
	private int id_club;
	private String name;
	private String logo;
	
	public Club(int id_user, String name, String logo){
		super(id_user);
		this.id_club = id_user;
		this.name = name;
		this.logo = logo;
	}
	
	public Club(int id_user, String mail, String password, int role){
		super(id_user,mail,password,role);
	}
	
	public Club(int id_user, String mail, String password, int role, int id_club, String name, String logo){
		super(id_user,mail,password,role);
		this.id_club=id_club;
		this.name=name;
		this.logo=logo;
	}

	public int getId_club() {
		return id_club;
	}

	public void setId_club(int id_club) {
		this.id_club = id_club;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Boolean getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
