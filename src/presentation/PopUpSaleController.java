package presentation;

import java.io.File;

import business_logic.facades.ManageTeamClubFacade;
import business_logic.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.PlayerCell;
import presentation.tableViewCell.PlayerOfferCell;

public class PopUpSaleController {
	
	@FXML private Label TeamName;
	@FXML private Label PlayerName;
	@FXML private TableView<PlayerOfferCell> OffersTable;
	@FXML private TableColumn<PlayerOfferCell, String> ClubName; 
	@FXML private TableColumn<PlayerOfferCell, String> Price; 
	@FXML private ImageView ImageClub;
	
	PlayerCell myCell = null;
	TableCell<PlayerCell, String> buttonCol;
	Button btn;
	private ManageTeamClubFacade myFacade;
	private ManageTeamClubController view;
	
	public PopUpSaleController(TableCell<PlayerCell, String> tableCell, Button btn, PlayerCell item, ManageTeamClubController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new ManageTeamClubFacade();
		this.buttonCol=tableCell;
		this.btn=btn;
	}
	
	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
		ImageClub.setImage(myImage);
		TeamName.setText(myUser.getName());
		PlayerName.setText(myCell.getFirstname()+ " "+myCell.getLastname());
		
		ClubName.setCellValueFactory(cellData -> cellData.getValue().clubNameProperty());
		Price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		System.out.println(" id du joueur : "+myCell.getIdPlayer());
        OffersTable.setItems(myFacade.getAllPlayerOffers(myCell.getIdPlayer()));
		
	}
	
	@FXML protected void CancelSale(ActionEvent event) {
		myFacade.deleteUpToSale(myCell.getIdPlayer());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}
}
