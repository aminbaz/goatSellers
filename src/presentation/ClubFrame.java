package presentation;


import business_logic.facades.LoginFacade;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ClubFrame extends Frame {
	
	private LoginFacade myFacade;
	
	public ClubFrame(WindowManager window) {
		super(window);
		this.myFacade = new LoginFacade();
	}
	
	public LoginFacade getMyFacade() {
		return myFacade;
	}

	public void setMyFacade(LoginFacade myFacade) {
		this.myFacade = myFacade;
	}
	
	@Override
	public Scene initScene() {		
		
		/*Image image = new Image("");
		ImageView iv1 = new ImageView();
		iv1.setImage(image);
		iv1.setPreserveRatio(true);
		iv1.setFitHeight(200);
		iv1.setFitWidth(100);*/
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,50,50,50));
		
		//bp.setCenter(iv1);
		
		//Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));

		//DropShadow effect
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		//Adding text and DropShadow effect to it
		Text text = new Text("Club Scene");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);
		
		//Adding text to HBox
		hb.getChildren().add(text);

		//Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);	
		
		//Add ID's to Nodes
		bp.setId("bp");
		text.setId("text");

		//Adding BorderPane to the scene and loading CSS
		this.setScene(new Scene(bp));
		return getScene();

	}

}
