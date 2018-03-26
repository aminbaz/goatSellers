package presentation;

import business_logic.facades.HomeAdminFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupAddClubController {
	
	@FXML private TextField logoTf;
	@FXML private TextField nameTf;
	@FXML private TextField mailTf;
	@FXML private TextField passwordTf;
	
	private HomeAdminFacade myFacade;

	public PopupAddClubController() {
		myFacade = new HomeAdminFacade();
	}
	
	@FXML public void initialize() {

	}
	
	@FXML protected void addClub(ActionEvent event) {
		myFacade.addClub(logoTf.getText(), nameTf.getText(), mailTf.getText(), passwordTf.getText());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
}
