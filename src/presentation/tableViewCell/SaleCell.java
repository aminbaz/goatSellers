package presentation.tableViewCell;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleCell {
	
	private final StringProperty firstname;
	private final StringProperty lastname;
	private final StringProperty date;
	
	public SaleCell() {
		this(null,null,null);
	}
	
	public SaleCell(String firstname, String lastname, String date) {
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.date = new SimpleStringProperty(date);
	}
	
	public String getFirstname() {
		return firstname.get();
	}
	public String getLastname() {
		return lastname.get();
	}
	public String getDate() {
		return date.get();
	}
	public void SetFirstname(String firstname) {
		this.firstname.set(firstname);
	}
	public void SetLastname(String lastname) {
		this.lastname.set(lastname);
	}
	public void SetDate(String date) {
		this.date.set(date);
	}
	public StringProperty firstnameProperty() {
		return firstname;
	}
	public StringProperty lastnameProperty() {
		return lastname;
	}
	public StringProperty dateProperty() {
		return date;
	}
}
