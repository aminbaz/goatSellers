package presentation;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import javafx.util.StringConverter;

public class PopUpAddPlayerController {
	@FXML private TextField firstname;
	@FXML private TextField lastname;
	@FXML private DatePicker birth;
	@FXML private ChoiceBox<String> position;
	@FXML private DatePicker endContract;

	private ManageTeamClubFacade myFacade;
	private ManageTeamClubController view;

	public PopUpAddPlayerController(ManageTeamClubController view) {
		myFacade = new ManageTeamClubFacade();
		this.view=view;
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

	@FXML public void AddPlayer(ActionEvent event) {
		myFacade.addPlayer(firstname.getText(), lastname.getText(),birth.getValue(), position.getSelectionModel().getSelectedItem(), endContract.getValue());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		view.getClubTable().setItems(myFacade.getAllPlayer());	
		view.getClubTable().refresh();
		stage.close();
	}

}
