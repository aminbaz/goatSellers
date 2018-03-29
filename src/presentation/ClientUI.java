package presentation;

import business_logic.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientUI extends Application{

	private static User myUser=null;
	private static Stage myStage=null;

	public ClientUI() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		myStage=primaryStage;
		primaryStage.setTitle("Goat Sellers");
		//primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../../images/logo_goatSellers.png")));

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ClientUI.class.getResource("login.fxml"));
		Parent root=loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static User getMyUser() {
		return myUser;
	}

	public static void setMyUser(User myUser) {
		ClientUI.myUser = myUser;
	}

	public static Stage getMyStage() {
		return myStage;
	}

	public static void setMyStage(Stage myStage) {
		ClientUI.myStage = myStage;
	}

	public static void main (String[] args){
		launch(args);
	}
}
