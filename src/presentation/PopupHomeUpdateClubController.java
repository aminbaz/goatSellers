package presentation;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.HomeClubFacade;
import business_logic.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.tableViewCell.AdminCell;

public class PopupHomeUpdateClubController {
	
	@FXML private TextField logoTf;
	@FXML private TextField nameTf;
	@FXML private TextField mailTf;
	@FXML private TextField passwordTf;
	
	private Club club;
	private HomeAdminFacade myFacade;
	
	public PopupHomeUpdateClubController(Club club) {
		this.club = club;
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
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
}
