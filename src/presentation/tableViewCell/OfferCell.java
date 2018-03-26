package presentation.tableViewCell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OfferCell {
	
	private final StringProperty firstname;
	private final StringProperty lastname;
	private final StringProperty price;
	private final StringProperty status;
	
	public OfferCell() {
		this(null,null,0,null);
	}
	
	public OfferCell(String firstname, String lastname, int price, String status) {
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		float amount = (float) (price/1000000.00);
		this.price = new SimpleStringProperty(Float.toString(amount)+" M€");
		this.status = new SimpleStringProperty(status);
	}
	
	public String getFirstname() {
		return firstname.get();
	}
	public String getLastname() {
		return lastname.get();
	}
	public String getPrice() {
		return price.get();
	}
	public String getStatus() {
		return status.get();
	}
	
	public void setFirstname(String firstname) {
		this.firstname.set(firstname);
	}
	public void setLastname(String lastname) {
		this.lastname.set(lastname);
	}
	public void setPrice(String price) {
		this.price.set(price);
	}
	public void setStatus(String status) {
		this.status.set(status);
	}

	public StringProperty firstnameProperty() {
		return firstname;
	}
	public StringProperty lastnameProperty() {
		return lastname;
	}
	public StringProperty priceProperty() {
		return price;
	}
	public StringProperty statusProperty() {
		return status;
	}
}
