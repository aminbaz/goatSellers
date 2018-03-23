package presentation;

import java.io.File;
import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.HomeClubFacade;
import business_logic.facades.LoginFacade;
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
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.SaleCell;

public class HomeClubController {
	
	
	@FXML private Button transfertButton;
	@FXML private Label nameClubLabel;
	@FXML private ImageView image;
	
	@FXML private TableView<OnSaleCell> onSalesTable;
	
	@FXML private TableColumn<SaleCell, String> nameClub;
	@FXML private TableColumn<SaleCell, String> firstnameOS;
	@FXML private TableColumn<SaleCell, String> lastnameOS;
	@FXML private TableColumn<SaleCell, String> birth;
	@FXML private TableColumn<SaleCell, Integer> minPrice;
	@FXML private TableColumn<SaleCell, Integer> idOS;
	
	private HomeClubFacade myFacade;
	
	public HomeClubController() {
		myFacade= new HomeClubFacade();
	}
	
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
	
	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
		image.setImage(myImage);
		nameClubLabel.setText(myUser.getName());
		
		
	}
}
