package presentation;

import java.io.File;

import business_logic.models.Club;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.tableViewCell.OnSaleCell;

public class PopUp_UpToSaleController {
	
	OnSaleCell myCell = null;
	
	@FXML private ImageView clubImage;
	@FXML private Label clubName;
	@FXML private Label firstName;
	@FXML private Label lastName;
	@FXML private Label birth;
	@FXML private TextField minPrice;
	@FXML private Button validateButton;
	
	public PopUp_UpToSaleController(OnSaleCell item) {
		this.myCell=item;
	}
	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
        clubImage.setImage(myImage);
        clubName.setText(myUser.getName());
        firstName.setText(myCell.getFirstname());
        lastName.setText(myCell.getLastname());
        birth.setText(myCell.getBirth());
        minPrice.setText(myCell.getMinPrice());
	}
}
