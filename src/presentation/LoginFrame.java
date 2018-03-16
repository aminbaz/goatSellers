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


public class LoginFrame extends Frame {
	
	private LoginFacade myFacade;
	
	public LoginFrame(WindowManager window) {
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
		
		BorderPane bp2 = new BorderPane();
		bp2.setPadding(new Insets(10,50,50,50));
		//bp.setCenter(iv1);
		
		//Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));
		
		//Adding GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		
		//Implementing Nodes for GridPane
		Label lblMail = new Label("Mail");
		final TextField txtMail = new TextField();
		Label lblPassword = new Label("Password");
		final PasswordField pf = new PasswordField();
		Button btnLogin = new Button("Login");
		final Label lblMessage = new Label();

        //Adding Nodes to GridPane layout
		gridPane.add(lblMail, 0, 0);
		gridPane.add(txtMail, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(pf, 1, 1);
		gridPane.add(btnLogin, 2, 1);
		gridPane.add(lblMessage, 1, 2);
		
		//Reflection for gridPane
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		gridPane.setEffect(r);

		//DropShadow effect
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		//Adding text and DropShadow effect to it
		Text text = new Text("Login");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);
		
		//Adding text to HBox
		hb.getChildren().add(text);

		//Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
		bp.setCenter(gridPane); 
		
		//Add ID's to Nodes
		bp.setId("bp");
		gridPane.setId("root");
		btnLogin.setId("btnLogin");
		text.setId("text");

		//Action for btnLogin
		btnLogin.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				myWindow.setUser(myFacade.login(txtMail.getText(), pf.getText()));
				myWindow.handleMessageFromFrame("Login Club");
			}
		});

		//Adding BorderPane to the scene and loading CSS
		this.setScene(new Scene(bp));
		return this.getScene();

	}

}
