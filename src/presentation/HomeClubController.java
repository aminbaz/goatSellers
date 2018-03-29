
package presentation;

import java.io.File;
import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.HomeClubFacade;
import business_logic.facades.LoginFacade;
import business_logic.models.Authority;
import business_logic.models.Club;
import business_logic.models.SuperAdmin;
import business_logic.models.User;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.OfferCell;
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.SaleCell;

public class HomeClubController {
	
	@FXML private Button transfertButton;
	@FXML private Label nameClubLabel;
	@FXML private ImageView image;
	
	@FXML private TableView<OnSaleCell> onSalesTable;
	@FXML private TableView<OfferCell> offersTable;
	
	@FXML private TableColumn<OnSaleCell, String> nameClub;
	@FXML private TableColumn<OnSaleCell, String> firstnameOS;
	@FXML private TableColumn<OnSaleCell, String> lastnameOS;
	@FXML private TableColumn<OnSaleCell, String> birth;
	@FXML private TableColumn<OnSaleCell, String> minPrice;
	@FXML private TableColumn<OnSaleCell, Integer> idOS;
	
	@FXML private TableColumn<OfferCell, String> firstnameOF;
	@FXML private TableColumn<OfferCell, String> lastnameOF;
	@FXML private TableColumn<OfferCell, String> priceOF;
	@FXML private TableColumn<OfferCell, String> statusOF;	
	
	private HomeClubFacade myFacade;
	
	public HomeClubController() {
		myFacade= new HomeClubFacade();
	}
	
	public HomeClubController getHomeClubController() {
		return this;
	}
	
	@FXML protected void handleTransfert(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("OK");
        loader.setLocation(ClientUI.class.getResource("transfertMarketNews.fxml"));
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
	
	@FXML protected void handleTransactionHistory(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientUI.class.getResource("transactionHistoryClub.fxml"));
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
	
	@FXML protected void handleManageTeam(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("OK");
        loader.setLocation(ClientUI.class.getResource("ManageTeamClub.fxml"));
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
	
	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
		image.setImage(myImage);
		nameClubLabel.setText(myUser.getName());
		
		nameClub.setCellValueFactory(cellData -> cellData.getValue().clubNameProperty());
		firstnameOS.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		lastnameOS.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
		birth.setCellValueFactory(cellData -> cellData.getValue().birthProperty());
		minPrice.setCellValueFactory(cellData -> cellData.getValue().minStringPriceProperty());
		
		TableColumn col_action = new TableColumn<>("");
		col_action.setSortable(false);
		col_action.setPrefWidth(75.0);
		col_action.setStyle("-fx-alignment: CENTER;");
		
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<OnSaleCell, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<OnSaleCell, Boolean> p) {
            	return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
		
        col_action.setCellFactory(
                new Callback<TableColumn<OnSaleCell, Boolean>, TableCell<OnSaleCell, Boolean>>() {

			@Override
			public TableCell<OnSaleCell, Boolean> call(TableColumn<OnSaleCell, Boolean> p) {
				return new ButtonCell();
			}
         
        });
		
        onSalesTable.getColumns().add(col_action);
        onSalesTable.setItems(myFacade.getAllUpToSales());
        
        
		firstnameOF.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		lastnameOF.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
		priceOF.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		statusOF.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		
        offersTable.setItems(myFacade.getAllClubOffers());		
	}
	
    private class ButtonCell extends TableCell<OnSaleCell, Boolean> {
        final Button cellButton = new Button("See");
        
        ButtonCell(){
        	
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    final OnSaleCell item = (OnSaleCell) getTableRow().getItem();
                    System.out.println(item.getId());
            		Stage popupwindow=new Stage();     
            		popupwindow.initModality(Modality.APPLICATION_MODAL);
            		popupwindow.setTitle("Up To Sale"); 
               		FXMLLoader loader = new FXMLLoader();
               		loader.setController(new PopUp_UpToSaleController(item,getHomeClubController()));
            		loader.setLocation(ClientUI.class.getResource("PopUp_UpToSale.fxml"));
            		AnchorPane page = null;
					try {
						page = (AnchorPane) loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Scene scene1= new Scene(page, 600, 300);	      
					popupwindow.setScene(scene1);   
					popupwindow.showAndWait();   
					
                }
            });
        }
        
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }

    }

	public TableView<OnSaleCell> getOnSalesTable() {
		return onSalesTable;
	}

	public void setOnSalesTable(TableView<OnSaleCell> onSalesTable) {
		this.onSalesTable = onSalesTable;
	}
	
	public TableView<OfferCell> getOffersTable() {
		return offersTable;
	}

	public void setOffersTable(TableView<OfferCell> offersTable) {
		this.offersTable = offersTable;
	}
	
	@FXML protected void handleUpdateClub(ActionEvent event) {
		Club myUser = (Club) ClientUI.getMyUser();
  		Stage popupwindow=new Stage();     
  		popupwindow.initModality(Modality.APPLICATION_MODAL);
  		popupwindow.setTitle("Update club"); 
  		FXMLLoader loader = new FXMLLoader();
  		loader.setController(new PopupHomeUpdateClubController(myUser));
  		loader.setLocation(ClientUI.class.getResource("PopupHomeUpdateClub.fxml"));
  		AnchorPane page = null;
			try {
				page = (AnchorPane) loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Scene scene1= new Scene(page, 350, 300);	      
			popupwindow.setScene(scene1);   
			popupwindow.showAndWait();
	}
    
}
