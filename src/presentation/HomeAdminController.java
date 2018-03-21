package presentation;

import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.LoginFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeAdminController {
	
	@FXML private Label nameAdmin;
	
	private HomeAdminFacade myFacade;
	
	public HomeAdminController() {
		myFacade = new HomeAdminFacade();
	}
	
	@FXML protected void setName(ActionEvent event) {
		nameAdmin.setText(ClientUI.getMyUser().getMail());
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
