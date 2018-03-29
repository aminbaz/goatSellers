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
import presentation.tableViewCell.OnSaleCell;
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

		firstName.setText(myCell.getFirstname());
		lastName.setText(myCell.getLastname());

		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate localDate = LocalDate.parse(myCell.getBirth(), formatter);
		//dateOfBirth.setValue(localDate);

		position.setValue(myCell.getPosition());

		//DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate localDate1 = LocalDate.parse(myCell.getEndOfContrat(), formatter1);
		//endOfContrat.setValue(localDate1);

	}	

	@FXML protected void updatePlayer(ActionEvent event) {
		System.out.println(myCell.getIdPlayer());
		myFacade.updatePlayer(myCell.getIdPlayer(),firstName.getText(), lastName.getText(), dateOfBirth.getValue(), position.getValue(), endOfContrat.getValue());

		myCell.setFirstname(firstName.getText());
		myCell.setLastname(lastName.getText());
		myCell.setBirth(dateOfBirth.getValue().toString());
		myCell.setPosition(position.getValue());
		myCell.setEndOfContrat(endOfContrat.getValue().toString());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
