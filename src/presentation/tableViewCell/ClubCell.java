package presentation.tableViewCell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
	
	
public class ClubCell {
	private final StringProperty name;
	private final StringProperty logo;
	private final BooleanProperty state;
	
	public ClubCell() {
		this(null,null,null);
	}
	
	public ClubCell(String logo, String name, Boolean state) {
		this.name = new SimpleStringProperty(name);
		this.logo = new SimpleStringProperty(logo);
		this.state = new SimpleBooleanProperty(state);
	}
	
	public String getLogo() {
		return logo.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public Boolean getState() {
		return state.get();
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

	public BooleanProperty stateProperty() {
		// TODO Auto-generated method stub
		return state;
	}
}

