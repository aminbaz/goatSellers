package presentation;

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

public class PopUpAddPlayerController {
	@FXML private TextField firstname;
	@FXML private TextField lastname;
	@FXML private DatePicker birthdate;
	@FXML private ChoiceBox<String> position;
	@FXML private DatePicker endContract;
	
	private ManageTeamClubFacade myFacade;

	public PopUpAddPlayerController() {
		myFacade = new ManageTeamClubFacade();
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

}
