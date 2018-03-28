package presentation;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import business_logic.facades.LoginFacade;
import business_logic.facades.MarketNewsFacade;
import business_logic.models.Authority;
import business_logic.models.Club;
import business_logic.models.SuperAdmin;
import business_logic.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.tableViewCell.NewsCell;
import presentation.tableViewCell.SaleCell;

public class TransfertMarketNewsController {
	
	@FXML private Label nameClubLabel;
	@FXML private ImageView image;
	
	@FXML private TableColumn<NewsCell, String> firstname;
	@FXML private TableColumn<NewsCell, String> lastname;
	@FXML private TableColumn<NewsCell, String> birth;
	@FXML private TableColumn<NewsCell, String> position;
	@FXML private TableColumn<NewsCell, String> oldClub;
	@FXML private TableColumn<NewsCell, String> newClub;
	@FXML private TableColumn<NewsCell, String> price;
	
	@FXML private TableView<NewsCell> salesTable;
	
	private MarketNewsFacade myFacade;
	
	public TransfertMarketNewsController() {
		myFacade=new MarketNewsFacade();
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
	
	@FXML protected void handleManageTeam(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("OK");
        loader.setLocation(ClientUI.class.getResource("ManageTeamClub.fxml"));
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
	
	@FXML protected void handleUpdateClub(ActionEvent event) {
		Club myUser = (Club) ClientUI.getMyUser();
  		Stage popupwindow=new Stage();     
  		popupwindow.initModality(Modality.APPLICATION_MODAL);
  		popupwindow.setTitle("Update club"); 
  		FXMLLoader loader = new FXMLLoader();
  		loader.setController(new PopupHomeUpdateClubController(myUser));
  		loader.setLocation(ClientUI.class.getResource("PopupHomeUpdateClub.fxml"));
  		AnchorPane page = null;
			try {
				page = (AnchorPane) loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Scene scene1= new Scene(page, 350, 300);	      
			popupwindow.setScene(scene1);   
			popupwindow.showAndWait();
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
		birth.setCellValueFactory(cellData -> cellData.getValue().birthProperty());
		position.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
		oldClub.setCellValueFactory(cellData -> cellData.getValue().oldClubProperty());
		newClub.setCellValueFactory(cellData -> cellData.getValue().newClubProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		
		try {
			salesTable.setItems(myFacade.getAllSales());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
