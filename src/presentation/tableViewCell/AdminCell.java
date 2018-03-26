package presentation.tableViewCell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
	
	
public class AdminCell {
	private final SimpleIntegerProperty idClub;
	private final StringProperty name;
	private final StringProperty logo;
	
	public AdminCell() {
		this(null,null,null);
	}
	
	public AdminCell(Integer idClub, String logo, String name) {
		this.idClub = new SimpleIntegerProperty(idClub);
		this.name = new SimpleStringProperty(name);
		this.logo = new SimpleStringProperty(logo);
	}
	
	public int getIdClub() {
		return idClub.get();
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
	
	public StringProperty logoProperty() {
		return logo;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
}

