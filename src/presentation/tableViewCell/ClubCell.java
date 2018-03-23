package presentation.tableViewCell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
	
	
public class ClubCell {
	private final StringProperty name;
	private final StringProperty logo;
	
	public ClubCell() {
		this(null,null);
	}
	
	public ClubCell(String logo, String name) {
		this.name = new SimpleStringProperty(name);
		this.logo = new SimpleStringProperty(logo);
	}
	
	public String getLogo() {
		return logo.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public void SetLogo(String logo) {
		this.logo.set(logo);
	}
	
	public void SetName(String name) {
		this.name.set(name);
	}
	
	public StringProperty lastnameProperty() {
		return logo;
	}
	
	public StringProperty firstnameProperty() {
		return name;
	}	
}

