package presentation.tableViewCell;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleCell {

	private final StringProperty name;
	private final StringProperty amount;
	private final StringProperty date;

	public SaleCell() {
		this(null,null,null);
	}

	public SaleCell(String name, String amount, String date) {
		this.name = new SimpleStringProperty(name);
		this.amount = new SimpleStringProperty(amount);
		this.date = new SimpleStringProperty(date);
	}

	public String getName() {
		return name.get();
	}
	public String getAmount() {
		return amount.get();
	}
	public String getDate() {
		return date.get();
	}
	public void SetName(String name) {
		this.name.set(name);
	}
	public void SetAmount(String amount) {
		this.amount.set(amount);
	}
	public void SetDate(String date) {
		this.date.set(date);
	}
	public StringProperty firstnameProperty() {
		return name;
	}
	public StringProperty lastnameProperty() {
		return amount;
	}
	public StringProperty dateProperty() {
		return date;
	}
}
