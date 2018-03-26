package presentation.tableViewCell;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OnSaleCell {

	private final StringProperty nameClub;
	private final StringProperty firstnameOS;
	private final StringProperty lastnameOS;
	private final StringProperty birth;
	private final IntegerProperty minPrice;
	private final IntegerProperty idOS;
	
	public OnSaleCell() {
		this(null,null,null,null,0,0);
	}
	
	public OnSaleCell(String name, String firstname, String lastname, Date birthdate, int minPrice, int id) {
		this.nameClub = new SimpleStringProperty(name);
		this.firstnameOS = new SimpleStringProperty(firstname);
		this.lastnameOS = new SimpleStringProperty(lastname);
		this.birth = new SimpleStringProperty(birthdate.toString());
		this.minPrice = new SimpleIntegerProperty(minPrice);
		this.idOS = new SimpleIntegerProperty(id);
	}
	
	public String getNameClub() {
		return nameClub.get();
	}
	public String getFirstname() {
		return firstnameOS.get();
	}
	public String getLastname() {
		return lastnameOS.get();
	}
	public String getBirth() {
		return birth.get();
	}
	public String getMinStringPrice() {
		float amount = (float) (this.minPrice.getValue()/1000000.00);
		return amount+" M€";
	}
	public int getMinPrice() {
		return minPrice.get();
	}
	public Integer getId() {
		return idOS.get();
	}
	
	public void setClubName(String name) {
		this.nameClub.set(name);
	}
	public void setFirstname(String name) {
		this.firstnameOS.set(name);
	}
	public void setLastname(String name) {
		this.lastnameOS.set(name);
	}
	public void setBirth(String birth) {
		this.birth.set(birth);
	}
	public void setMinPrice(int minPrice) {
		this.minPrice.set(minPrice);
	}
	public void setId(int id) {
		this.idOS.set(id);
	}
	
	public StringProperty clubNameProperty() {
		return nameClub;
	}
	public StringProperty firstnameProperty() {
		return firstnameOS;
	}
	public StringProperty lastnameProperty() {
		return lastnameOS;
	}
	public StringProperty birthProperty() {
		return birth;
	}
	public IntegerProperty minPriceProperty() {
		return minPrice;
	}
	public StringProperty minStringPriceProperty() {
		float amount = (float) (this.minPrice.getValue()/1000000.00);
		return new SimpleStringProperty(amount+" M€");
	}
	public IntegerProperty idOSProperty() {
		return idOS;
	}
}
