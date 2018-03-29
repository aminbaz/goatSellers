package presentation;

import java.io.File;
import java.io.IOException;

import business_logic.facades.ManageTeamClubFacade;
import business_logic.models.Club;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import presentation.tableViewCell.OnSaleCell;
import presentation.tableViewCell.PlayerCell;
import presentation.tableViewCell.PlayerOfferCell;

public class PopUpSaleController {
	
	@FXML private Label TeamName;
	@FXML private Label PlayerName;
	@FXML private TableView<PlayerOfferCell> OffersTable;
	@FXML private TableColumn<PlayerOfferCell, String> ClubName; 
	@FXML private TableColumn<PlayerOfferCell, String> Price; 
	@FXML private ImageView ImageClub;
	
	PlayerCell myCell = null;
	TableCell<PlayerCell, String> buttonCol;
	Button btn;
	private ManageTeamClubFacade myFacade;
	private ManageTeamClubController view;
	
	public PopUpSaleController(TableCell<PlayerCell, String> tableCell, Button btn, PlayerCell item, ManageTeamClubController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new ManageTeamClubFacade();
		this.buttonCol=tableCell;
		this.btn=btn;
	}
	
	public TableView<PlayerOfferCell> getOffersTable() {
		return OffersTable;
	}

	public void setOffersTable(TableView<PlayerOfferCell> offersTable) {
		OffersTable = offersTable;
	}

	@FXML public void initialize() {
		Image myImage = null;
		Club myUser = (Club) ClientUI.getMyUser();
		File file = new File("@../../images/"+myUser.getLogo());
        myImage = new Image(file.toURI().toString());
		ImageClub.setImage(myImage);
		TeamName.setText(myUser.getName());
		PlayerName.setText(myCell.getFirstname()+ " "+myCell.getLastname());
		
		ClubName.setCellValueFactory(cellData -> cellData.getValue().clubNameProperty());
		Price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		
		TableColumn col_action = new TableColumn<>("");
		col_action.setSortable(false);
		col_action.setPrefWidth(100.0);
		col_action.setStyle("-fx-alignment: CENTER;");
		
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PlayerOfferCell, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PlayerOfferCell, Boolean> p) {
            	return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
		
        col_action.setCellFactory(
                new Callback<TableColumn<PlayerOfferCell, Boolean>, TableCell<PlayerOfferCell, Boolean>>() {

			@Override
			public TableCell<PlayerOfferCell, Boolean> call(TableColumn<PlayerOfferCell, Boolean> p) {
				return new ButtonCellAccept();
			}
         
        });
		
        OffersTable.getColumns().add(col_action);
        
		TableColumn col_actionD = new TableColumn<>("");
		col_actionD.setSortable(false);
		col_actionD.setPrefWidth(100.0);
		col_actionD.setStyle("-fx-alignment: CENTER;");
		
        col_actionD.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PlayerOfferCell, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PlayerOfferCell, Boolean> p) {
            	return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
		
        col_actionD.setCellFactory(
                new Callback<TableColumn<PlayerOfferCell, Boolean>, TableCell<PlayerOfferCell, Boolean>>() {

			@Override
			public TableCell<PlayerOfferCell, Boolean> call(TableColumn<PlayerOfferCell, Boolean> p) {
				return new ButtonCellDecline();
			}
         
        });
		
        OffersTable.getColumns().add(col_actionD);
        OffersTable.setItems(myFacade.getAllPlayerOffers(myCell.getIdPlayer()));
		
	}
	
    private class ButtonCellAccept extends TableCell<PlayerOfferCell, Boolean> {
        final Button cellButton = new Button("Accept");
        
        ButtonCellAccept(){
        	
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    final PlayerOfferCell item = (PlayerOfferCell) getTableRow().getItem();
                    Club myClub = (Club) ClientUI.getMyUser();
                    myFacade.AcceptOffer(item.getIdOffer(), item.getIdClub(), item.getIdPlayer());
                    myCell=null;
                    view.getClubTable().setItems(myFacade.getAllPlayer());
            		Stage stage = (Stage)((Button) t.getSource()).getScene().getWindow();
            		stage.close();
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
    
    private class ButtonCellDecline extends TableCell<PlayerOfferCell, Boolean> {
        final Button cellButton = new Button("Decline");
        
        ButtonCellDecline(){
        	
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    final PlayerOfferCell item = (PlayerOfferCell) getTableRow().getItem();
                    myFacade.DeclineOffer(item.getIdOffer());
                    ObservableList<PlayerOfferCell> list = getOffersTable().getItems();
                    list.remove(item);
                    getOffersTable().setItems(list);
                    getOffersTable().refresh();
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
	
	@FXML protected void CancelSale(ActionEvent event) {
		myFacade.deleteUpToSale(myCell.getIdPlayer());
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}
}
