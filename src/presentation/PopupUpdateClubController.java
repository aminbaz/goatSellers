package presentation;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.HomeClubFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.tableViewCell.AdminCell;

public class PopupUpdateClubController {

	@FXML private TextField logoTf;
	@FXML private TextField nameTf;
	@FXML private TextField mailTf;
	@FXML private TextField passwordTf;

	AdminCell myCell = null;
	private HomeAdminFacade myFacade;
	private HomeAdminController view;

	public PopupUpdateClubController(AdminCell item, HomeAdminController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new HomeAdminFacade();
	}

	@FXML public void initialize() {
		logoTf.setText(myCell.getLogo());
		nameTf.setText(myCell.getName());
		mailTf.setText(myCell.getMail());
		passwordTf.setText(myCell.getPassword());
	}

	@FXML protected void updateClub(ActionEvent event) {
		myFacade.updateClub(myCell.getIdClub(),logoTf.getText(), nameTf.getText(), mailTf.getText(), passwordTf.getText());
		myCell.SetLogo(logoTf.getText());
		myCell.SetName(nameTf.getText());
		myCell.SetMail(mailTf.getText());
		myCell.SetPassword(passwordTf.getText());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();

	}
}
