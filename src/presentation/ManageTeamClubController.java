package presentation;

import java.io.File;

import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.ManageTeamClubFacade;
import business_logic.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.tableViewCell.ClubCell;
import presentation.tableViewCell.PlayerCell;

public class ManageTeamClubController {

	private ManageTeamClubFacade myFacade;
	
	public ManageTeamClubController() {
		myFacade = new ManageTeamClubFacade();
	}
	
	@FXML private Button transfertButton;
	@FXML private Label nameClubLabel;
	@FXML private ImageView image;
	
	@FXML private TableView<PlayerCell> clubTable;
	@FXML private TableColumn<PlayerCell, String> firstname;
	@FXML private TableColumn<PlayerCell, String> lastname;
	@FXML private TableColumn<PlayerCell, String> birthDate;
	@FXML private TableColumn<PlayerCell, String> position;
	@FXML private TableColumn<PlayerCell, String> contract;
	
	@FXML protected void handleTransfert(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("OK");
        loader.setLocation(ClientUI.class.getResource("transfertMarketNews.fxml"));
        Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene = new Scene(root);
        ClientUI.getMyStage().setScene(scene);
	}
	
	@FXML protected void handleTransactionHistory(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientUI.class.getResource("transactionHistoryClub.fxml"));
        Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene = new Scene(root);
        ClientUI.getMyStage().setScene(scene);
	}
	
	@FXML protected void handleLogOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientUI.class.getResource("login.fxml"));
        Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene = new Scene(root);
        ClientUI.getMyStage().setScene(scene);
	}
	
	@FXML protected void handleHome(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("OK");
        loader.setLocation(ClientUI.class.getResource("HomeClub.fxml"));
        Parent root=null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene = new Scene(root);
        ClientUI.getMyStage().setScene(scene);
	}
	
	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
		image.setImage(myImage);
		nameClubLabel.setText(myUser.getName());
		
	    firstname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
	    lastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
	    birthDate.setCellValueFactory(cellData -> cellData.getValue().birthProperty());
	    position.setCellValueFactory(cellData -> cellData.getValue().PositionProperty());
	    contract.setCellValueFactory(cellData -> cellData.getValue().endOfContratProperty());
		clubTable.setItems(myFacade.getCellData());
	}
}
