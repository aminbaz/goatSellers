package presentation;

import business_logic.models.User;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Frame {
	
	private Scene scene;
	WindowManager myWindow;
	
	public Frame(WindowManager myWindow) {
		this.myWindow=myWindow;
	}
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public abstract Scene initScene();

}
