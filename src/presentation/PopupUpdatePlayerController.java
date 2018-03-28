package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.ManageTeamClubFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ManageTeamClubFacade myFacade;;
	
	public PopupUpdatePlayerController() {
		myFacade= new ManageTeamClubFacade();
	}
	
	@FXML public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("GoalKeeper");
        list.add("Defender");
        list.add("Midfielder");
        list.add("Striker");
        ObservableList obList = FXCollections.observableList(list);
        position.getItems().clear();
        position.setItems(obList); 
	}	
	
	/*@FXML protected void updatePlayer(ActionEvent event) {
		System.out.println("OK");
		myFacade.updatePlayer(myCell.getIdPlayer(),firstName.getText(), lastName.getText(), dateOfBirth.getValue(), position.getValue(), endOfContrat.getValue());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close(); 
	}*/

}
