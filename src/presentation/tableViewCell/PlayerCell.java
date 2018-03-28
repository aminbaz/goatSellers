package presentation.tableViewCell;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerCell {
	
	private final IntegerProperty idPlayer;
	private final StringProperty firstname;
	private final StringProperty lastname;
	private final StringProperty birth;
	private final StringProperty position;
	private final StringProperty endOfContrat;
	
	public PlayerCell() {
		this(null,null,null,null,null,null);
	}
	
	public PlayerCell(Integer idPlayer, String firstname, String lastname, Date date, String position, Date date2) {
		this.idPlayer = new SimpleIntegerProperty(idPlayer);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.birth = new SimpleStringProperty(date.toString());
		this.position = new SimpleStringProperty(position);
		this.endOfContrat = new SimpleStringProperty(date2.toString());
	}

//Function get
	public Integer getIdPlayer() {
		return idPlayer.get();
	}
	public String getFirstname() {
		return firstname.get();
	}
	public String getLastname() {
		return lastname.get();
	}
	public String getBirth() {
		return birth.get();
	}
	public String getPosition() {
		return position.get();
	}
	public String getEndOfContrat() {
		return endOfContrat.get();
	}
//Function set
	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer.set(idPlayer);
	}
	public void setFirstname(String name) {
		this.firstname.set(name);
	}
	public void setLastname(String name) {
		this.lastname.set(name);
	}
	public void setBirth(String birth) {
		this.birth.set(birth);
	}
	public void setPosition(String position) {
		this.position.set(position);
	}
	public void setEndOfContrat(String endOfContrat) {
		this.endOfContrat.set(endOfContrat);
	}

	
	public IntegerProperty idPlayerProperty() {
		return idPlayer;
	}
	public StringProperty firstnameProperty() {
		return firstname;
	}
	public StringProperty lastnameProperty() {
		return lastname;
	}
	public StringProperty birthProperty() {
		return birth;
	}
	public StringProperty PositionProperty() {
		return position;
	}
	public StringProperty endOfContratProperty() {
		return endOfContrat;
	}

}
