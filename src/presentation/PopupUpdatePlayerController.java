package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.ManageTeamClubFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.tableViewCell.PlayerCell;

public class PopupUpdatePlayerController {
	
	@FXML private TextField firstName;
	@FXML private TextField lastName;
	@FXML private DatePicker dateOfBirth;
	@FXML private ChoiceBox<String> position;
	@FXML private DatePicker endOfContrat;
	

	PlayerCell myCell = null;
	private ManageTeamClubFacade myFacade;
	private ManageTeamClubController view;
	
	public PopupUpdatePlayerController(PlayerCell item, ManageTeamClubController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new ManageTeamClubFacade();
	}
	
	@FXML public void initialize() {
		firstName.setText(myCell.getFirstname());
		lastName.setText(myCell.getLastname());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dt = LocalDate.parse(myCell.getBirth(), formatter);
		dateOfBirth.setValue(dt);
		
		position.setValue(myCell.getPosition());
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dt = LocalDate.parse(myCell.getBirth(), formatter);
		endOfContrat.setValue(dt);
	}	
	
	@FXML protected void updatePlayer(ActionEvent event) {
		
		Date dateOfBirthInDate = java.sql.Date.valueOf(dateOfBirth.getValue());
		Date endOfContratInDate = java.sql.Date.valueOf(endOfContrat.getValue());
		
		myFacade.updatePlayer(myCell.getIdPlayer(),firstName.getText(), lastName.getText(), dateOfBirthInDate, position.getValue(), endOfContratInDate);
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
