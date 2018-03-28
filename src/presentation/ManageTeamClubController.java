package presentation;

import java.io.File;

import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.ManageTeamClubFacade;
import business_logic.models.Club;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.ClubCell;
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.PlayerCell;

public class ManageTeamClubController {

	private ManageTeamClubFacade myFacade;
	
	public ManageTeamClubController() {
		myFacade = new ManageTeamClubFacade();
	}
	
	public ManageTeamClubController getManageTeamClubController() {
		return this;
	}
	
	@FXML private Button transfertButton;
	@FXML private Label nameClubLabel;
	@FXML private ImageView image;
	
	@FXML private TableView<PlayerCell> clubTable;
	@FXML private TableColumn<PlayerCell, String> firstname;
	@FXML private TableColumn<PlayerCell, String> lastname;
	@FXML private TableColumn<PlayerCell, String> birthDate;
	@FXML private TableColumn<PlayerCell, String> position;
	@FXML private TableColumn<PlayerCell, String> contract;
	
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
	
	@FXML protected void handleHome(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
		System.out.println("OK");
        loader.setLocation(ClientUI.class.getResource("HomeClub.fxml"));
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
	
	@FXML protected void AddButton(ActionEvent event) {
		Stage popupwindow=new Stage();     
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Add Player"); 
   		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ClientUI.class.getResource("PopUpAddPlayer.fxml"));
		AnchorPane page = null;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scene scene1= new Scene(page, 370, 400);	      
		popupwindow.setScene(scene1);   
		popupwindow.showAndWait(); 
	}
	
	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
		image.setImage(myImage);
		nameClubLabel.setText(myUser.getName());
		
	    firstname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
	    lastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
	    birthDate.setCellValueFactory(cellData -> cellData.getValue().birthProperty());
	    position.setCellValueFactory(cellData -> cellData.getValue().PositionProperty());
	    contract.setCellValueFactory(cellData -> cellData.getValue().endOfContratProperty());
	    
		TableColumn col_action = new TableColumn<>("");
		col_action.setSortable(false);
		col_action.setPrefWidth(100.0);
		col_action.setStyle("-fx-alignment: CENTER;");
		
		col_action.setCellValueFactory(
	            new Callback<TableColumn.CellDataFeatures<PlayerCell, Boolean>, 
	            ObservableValue<Boolean>>() {

	        @Override
	        public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PlayerCell, Boolean> p) {
	        	return new SimpleBooleanProperty(p.getValue() != null);
	        }
	    });
		
	    col_action.setCellFactory(
	            new Callback<TableColumn<PlayerCell, Boolean>, TableCell<PlayerCell, Boolean>>() {

			@Override
			public TableCell<PlayerCell, Boolean> call(TableColumn<PlayerCell, Boolean> p) {
				return new ButtonCell();
			}
	     
	    });
		
	    
		TableColumn col_upToSaleaction = new TableColumn<>("");
		col_upToSaleaction.setSortable(false);
		col_upToSaleaction.setPrefWidth(100.0);
		col_upToSaleaction.setStyle("-fx-alignment: CENTER;");
		
		col_upToSaleaction.setCellValueFactory(
	            new Callback<TableColumn.CellDataFeatures<PlayerCell, Boolean>, 
	            ObservableValue<Boolean>>() {

	        @Override
	        public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PlayerCell, Boolean> p) {
	        	return new SimpleBooleanProperty(p.getValue() != null);
	        }
	    });
		
	    col_upToSaleaction.setCellFactory(
	            new Callback<TableColumn<PlayerCell, Boolean>, TableCell<PlayerCell, Boolean>>() {

			@Override
			public TableCell<PlayerCell, Boolean> call(TableColumn<PlayerCell, Boolean> p) {
				return new ButtonUpToSaleCell();
			}
	     
	    });
	    
	    
        clubTable.getColumns().add(col_action);
        clubTable.getColumns().add(col_upToSaleaction);
		clubTable.setItems(myFacade.getAllPlayer());
	}
	
	
	  private class ButtonCell extends TableCell<PlayerCell, Boolean> {
	        final Button cellButton = new Button("Update");
	        
	        ButtonCell(){
	        	
	            cellButton.setOnAction(new EventHandler<ActionEvent>(){
	 
	                @Override
	                public void handle(ActionEvent t) {
	                   final PlayerCell item = (PlayerCell) getTableRow().getItem();
	                    System.out.println(item.getFirstname());
	            		Stage popupwindow=new Stage();     
	            		popupwindow.initModality(Modality.APPLICATION_MODAL);
	            		popupwindow.setTitle("Player"); 
	               		FXMLLoader loader = new FXMLLoader();
	               		loader.setController(new PopupUpdatePlayerController(item,getManageTeamClubController()));
	            		loader.setLocation(ClientUI.class.getResource("PopUp_UpdatePlayer.fxml"));
	            		AnchorPane page = null;
						try {
							page = (AnchorPane) loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Scene scene1= new Scene(page, 400, 400);	      
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
	  
	  private class ButtonUpToSaleCell extends TableCell<PlayerCell, Boolean> {
	        final Button cellButton = new Button("Sale");
	        
	        ButtonUpToSaleCell(){
	        	
	            cellButton.setOnAction(new EventHandler<ActionEvent>(){
	 
	                @Override
	                public void handle(ActionEvent t) {
	                	System.out.println("OK");
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
}
