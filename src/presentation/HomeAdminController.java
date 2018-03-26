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
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.ClubCell;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class HomeAdminController {
	
	@FXML private Label nameAdmin;
	
	@FXML private TableView<AdminCell> clubTable;
	
	@FXML private TableColumn<AdminCell, String> logo;
	@FXML private TableColumn<AdminCell, String> name;
	
	
	
	private HomeAdminFacade myFacade;
	
	public HomeAdminController() {
		myFacade = new HomeAdminFacade();
	}
	
	@FXML public void initialize() {
        logo.setCellValueFactory(cellData -> cellData.getValue().logoProperty());
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
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
	
	@FXML protected void handleAddClub(ActionEvent event) {
		display();
	}
	
	public void display(){
		Stage popupwindow=new Stage();     
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("ADD A CLUB");     
		Label logoLabel = new Label("LOGO : "); 
		TextField logoTf = new TextField();
		Label nameLabel = new Label("NAME : ");
		TextField nameTf = new TextField();
		Label mailLabel = new Label("MAIL : ");
		TextField mailTf = new TextField();
		Label passwordLabel = new Label("PASSWORD : ");
		TextField passwordTf = new TextField();
		Button addBtn= new Button("ADD THE CLUB");  
		addBtn.setOnAction(e -> myFacade.addClub(logoTf.getText(), nameTf.getText(), mailTf.getText(), passwordTf.getText()));
		VBox layout= new VBox(10);	      
		layout.getChildren().addAll(logoLabel, logoTf, nameLabel, nameTf, mailLabel, mailTf, passwordLabel, passwordTf, addBtn);	      
		layout.setAlignment(Pos.CENTER);	      
		Scene scene1= new Scene(layout, 300, 350);	      
		popupwindow.setScene(scene1);   
		popupwindow.showAndWait();      
	}
	
	
}
