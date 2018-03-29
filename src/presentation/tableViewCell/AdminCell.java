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
	private final StringProperty mail;
	private final StringProperty password;

	public AdminCell() {
		this(null,null,null,null,null);
	}

	public AdminCell(Integer idClub, String logo, String name, String mail, String password) {
		this.idClub = new SimpleIntegerProperty(idClub);
		this.name = new SimpleStringProperty(name);
		this.logo = new SimpleStringProperty(logo);
		this.mail = new SimpleStringProperty(mail);
		this.password = new SimpleStringProperty(password);
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

	public String getMail() {
		return mail.get();
	}

	public String getPassword() {
		return password.get();
	}

	public void SetLogo(String logo) {
		this.logo.set(logo);
	}

	public void SetName(String name) {
		this.name.set(name);
	}

	public void SetMail(String mail) {
		this.mail.set(mail);
	}

	public void SetPassword(String password) {
		this.password.set(password);
	}

	public StringProperty logoProperty() {
		return logo;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty mailProperty() {
		return mail;
	}

	public StringProperty passwordProperty() {
		return password;
	}
}

