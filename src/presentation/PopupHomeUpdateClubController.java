package presentation;

import java.io.File;
import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.HomeClubFacade;
import business_logic.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PopupHomeUpdateClubController {
	
	@FXML private TextField logoTf;
	@FXML private TextField nameTf;
	@FXML private TextField mailTf;
	@FXML private TextField passwordTf;
	
	private Label nameClubLabel;
	private ImageView image;
	private Club club;
	private HomeAdminFacade myFacade;
	
	public PopupHomeUpdateClubController(Club club, Label name, ImageView image) {
		this.club = club;
		this.nameClubLabel = name;
		this.image = image;
		myFacade= new HomeAdminFacade();
	}

	@FXML public void initialize() {
		logoTf.setText(club.getLogo());
		nameTf.setText(club.getName());
		mailTf.setText(club.getMail());
		passwordTf.setText(club.getPassword());
	}
	
	@FXML protected void updateClub(ActionEvent event) {
		myFacade.updateClub(club.getId_club(),logoTf.getText(), nameTf.getText(), mailTf.getText(), passwordTf.getText());
		nameClubLabel.setText(nameTf.getText());
		Image myImage = null;
		File file = new File("@../../images/"+logoTf.getText());
        myImage = new Image(file.toURI().toString());
		image.setImage(myImage);
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();

	}
}
