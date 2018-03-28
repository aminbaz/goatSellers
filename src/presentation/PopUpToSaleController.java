package presentation;

import java.io.File;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.ManageTeamClubFacade;
import business_logic.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.PlayerCell;

public class PopUpToSaleController {
	@FXML private TextField minPrice;
	@FXML private Label playerName;
	
	PlayerCell myCell = null;
	TableCell<PlayerCell, String> buttonCol;
	Button btn;
	private ManageTeamClubFacade myFacade;
	private ManageTeamClubController view;
	
	public PopUpToSaleController(TableCell<PlayerCell, String> tableCell, Button btn, PlayerCell item, ManageTeamClubController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new ManageTeamClubFacade();
		this.buttonCol=tableCell;
		this.btn=btn;
	}
	
	@FXML public void initialize() {
		playerName.setText(myCell.getFirstname()+" "+myCell.getLastname());
	}
	
	@FXML protected void ActionValidate(ActionEvent event) {
		btn.setText("On Sale");
		buttonCol.setGraphic(btn);
		myFacade.addUpToSale(Integer.parseInt(minPrice.getText()), myCell.getIdPlayer());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}
}
