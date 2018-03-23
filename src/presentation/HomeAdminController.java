package presentation;

import java.io.File;
import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.LoginFacade;
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
import presentation.tableViewCell.SaleCell;

public class HomeAdminController {
	
	@FXML private Label nameAdmin;
	
	@FXML private TableView<ClubCell> clubTable;
	
	//@FXML private TableColumn<ClubCell, String> logo;
	@FXML private TableColumn<ClubCell, String> name;
	/*@FXML private TableColumn<SaleCell, String> city;
	@FXML private TableColumn<SaleCell, String> country;
	@FXML private TableColumn<SaleCell, String> championship;*/
	
	
	
	private HomeAdminFacade myFacade;
	
	public HomeAdminController() {
		myFacade = new HomeAdminFacade();
	}
	
	@FXML public void initialize() {
		/*Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());*/
		//image.setImage(myImage);
        //logo.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		name.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		
		clubTable.setItems(myFacade.getCellData());
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
	
}
