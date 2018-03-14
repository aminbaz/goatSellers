package presentation;

import business_logic.models.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientUI extends Application implements WindowManager{

	private User connectedUser;
	private Frame myFrame;
	private Stage myStage;
	
	public ClientUI() {
		myFrame = new LoginFrame(this);
	}
	
	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public Frame getMyFrame() {
		return myFrame;
	}

	public void setMyFrame(Frame myFrame) {
		this.myFrame = myFrame;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.connectedUser=user;
	}
	
	@Override
	public void handleMessageFromFrame(String message) {
		// TODO Auto-generated method stub
		if(message.equals("Login Club")) {
			setMyFrame(new ClubFrame(this));
			myStage.setScene(myFrame.initScene());
			myStage.show();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		myStage=primaryStage;
		primaryStage.setTitle("Goat Sellers");
		primaryStage.setScene(myFrame.initScene());
		primaryStage.show();
	}

	@Override
	public void updateStage(Scene scene) {
		myStage.setScene(scene);
	}
}
