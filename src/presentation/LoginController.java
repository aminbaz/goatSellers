package presentation;

import java.io.IOException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	private LoginFacade myFacade;
	
	public LoginController() {
		myFacade=new LoginFacade();
	}
	
	@FXML private TextField fieldMail;
	
	@FXML private PasswordField fieldPassword;
	
	@FXML protected void handleLogin(ActionEvent event) {
		User myUser=myFacade.login(fieldMail.getText(), fieldPassword.getText());
		if(myUser !=null) {
	        FXMLLoader loader = new FXMLLoader();
			ClientUI.setMyUser(myUser);
			if(myUser instanceof Club) {
				System.out.println("true club");
		        loader.setLocation(ClientUI.class.getResource("accueilClub.fxml"));
			}else if(myUser instanceof Authority) {
				System.out.println("true authority");
		        loader.setLocation(ClientUI.class.getResource("authority_accueil.fxml"));
			}else if(myUser instanceof SuperAdmin) {
				System.out.println("true superadmin");
		        loader.setLocation(ClientUI.class.getResource("HomeAdmin.fxml"));
			}else {
				System.out.println("true user");
			}
	        Parent root=null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Scene scene = new Scene(root);
	        ClientUI.getMyStage().setScene(scene);
		}else {
			System.out.println("false");
		}
	}
}
