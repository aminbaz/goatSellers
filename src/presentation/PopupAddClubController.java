package presentation;

import business_logic.facades.HomeAdminFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.tableViewCell.AdminCell;

public class PopupAddClubController {
	
	@FXML private TextField logoTf;
	@FXML private TextField nameTf;
	@FXML private TextField mailTf;
	@FXML private TextField passwordTf;
	
	AdminCell myCell = null;
	private HomeAdminFacade myFacade;
	private HomeAdminController view;

	public PopupAddClubController(AdminCell item, HomeAdminController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new HomeAdminFacade();
	}
	
	@FXML public void initialize() {
		
	}
	
	@FXML protected void addClub(ActionEvent event) {
		myFacade.addClub(logoTf.getText(), nameTf.getText(), mailTf.getText(), passwordTf.getText());
		myCell.SetLogo(logoTf.getText());
		myCell.SetName(nameTf.getText());
		myCell.SetMail(mailTf.getText());
		myCell.SetPassword(passwordTf.getText());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
}
