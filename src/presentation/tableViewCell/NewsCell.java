package presentation.tableViewCell;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewsCell {
	private final StringProperty firstname;
	private final StringProperty lastname;
	private final StringProperty birth;
	private final StringProperty oldClub;
	private final StringProperty newClub;
	private final StringProperty price;
	
	public NewsCell() {
		this(null,null,null,null,null,0);
	}
	
	public NewsCell(String firstname, String lastname, Date birthday, String oldClub, String newClub, int price) {
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.birth = new SimpleStringProperty(birthday.toString());
		this.oldClub = new SimpleStringProperty(oldClub);
		this.newClub = new SimpleStringProperty(newClub);
		float amount = (float) (price/1000000.00);
		this.price = new SimpleStringProperty(Float.toString(amount)+" M€");
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
	public String getOldClub() {
		return oldClub.get();
	}
	public String getNewClub() {
		return newClub.get();
	}
	public String getPrice() {
		return price.get();
	}
	
	public void setFirstname(String firstname) {
		this.firstname.set(firstname);
	}
	public void setLastname(String lastname) {
		this.lastname.set(lastname);
	}
	public void setBirth(String birth) {
		this.birth.set(birth);
	}
	public void setOldClub(String oldClub) {
		this.oldClub.set(oldClub);
	}
	public void setNewClub(String newClub) {
		this.newClub.set(newClub);
	}
	public void setPrice(String price) {
		this.price.set(price);
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
	public StringProperty oldClubProperty() {
		return oldClub;
	}
	public StringProperty newClubProperty() {
		return newClub;
	}
	public StringProperty priceProperty() {
		return price;
	}
}
