package presentation.tableViewCell;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerOfferCell {

	private final IntegerProperty idClub;
	private final IntegerProperty idPlayer;
	private final StringProperty clubname;
	private final StringProperty price;
	private final IntegerProperty idOffer;

	public PlayerOfferCell() {
		this(0,0,null,0,0);
	}

	public PlayerOfferCell(int idClub, int idPlayer, String clubname, int price, int idOffer) {
		this.idClub = new SimpleIntegerProperty(idClub);
		this.idPlayer = new SimpleIntegerProperty(idPlayer);
		this.clubname = new SimpleStringProperty(clubname);
		float amount = (float) (price/1000000.00);
		this.price = new SimpleStringProperty(Float.toString(amount)+" M€");
		this.idOffer = new SimpleIntegerProperty(idOffer);		
	}

	public int getIdClub() {
		return idClub.get();
	}
	public int getIdPlayer() {
		return idPlayer.get();
	}
	public String getClubName() {
		return clubname.get();
	}
	public String getPrice() {
		return price.get();
	}
	public int getIdOffer() {
		return idOffer.get();
	}

	public void setIdClub(int id) {
		this.idClub.set(id);
	}
	public void setIdPlayer(int id) {
		this.idPlayer.set(id);
	}
	public void setClubName(String name) {
		this.clubname.set(name);
	}
	public void setPrice(String price) {
		this.price.set(price);
	}
	public void setIdOffer(int id) {
		this.idOffer.set(id);
	}

	public IntegerProperty idClubProperty() {
		return idClub;
	}
	public IntegerProperty idPlayerProperty() {
		return idPlayer;
	}
	public StringProperty clubNameProperty() {
		return clubname;
	}
	public StringProperty priceProperty() {
		return price;
	}
	public IntegerProperty idOfferProperty() {
		return idOffer;
	}
}
