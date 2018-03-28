package presentation.tableViewCell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
	
	
public class ClubCell {
	private final StringProperty name;
	private final StringProperty logo;
	private final BooleanProperty state;
	private final IntegerProperty idclub;
	private final ReadOnlyObjectWrapper<Integer> sumPurchases;
	private final ReadOnlyObjectWrapper<Integer> sumSold;
	
	public ClubCell() {
		this(null,null,null, 0,0,0);
	}
	
	public ClubCell(String logo, String name, Boolean state,int idclub, int sumPurchases, int sumSold) {
		this.name = new SimpleStringProperty(name);
		this.logo = new SimpleStringProperty(logo);
		this.state = new SimpleBooleanProperty(state);
		this.idclub = new SimpleIntegerProperty(idclub);
		this.sumPurchases = new ReadOnlyObjectWrapper<Integer>(sumPurchases);
		this.sumSold = new ReadOnlyObjectWrapper<Integer>(sumSold);
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
	
	public int getIdClub() {
		return idclub.get();
	}
	public int getSumPurchases() {
		return sumPurchases.get();
	}
	public int getSumSold() {
		return sumSold.get();
	}
	
	public void SetLogo(String logo) {
		this.logo.set(logo);
	}
	
	public void SetSumPurchases(int sum) {
		this.sumPurchases.set(sum);
	}
	
	public void SetSumSold(int sum) {
		this.sumSold.set(sum);
	}
	
	public void SetName(String name) {
		this.name.set(name);
	}
	
	public void SetState(Boolean state) {
		this.state.set(state);
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
	public IntegerProperty idclubProperty() {
		// TODO Auto-generated method stub
		return idclub;
	}
	public ReadOnlyObjectWrapper<Integer> sumPurchasesProperty() {
		// TODO Auto-generated method stub
		return sumPurchases;
	}
	
	public ReadOnlyObjectWrapper<Integer> sumSoldProperty() {
		// TODO Auto-generated method stub
		return sumSold;
	}
}

