package presentation;

import java.io.File;
import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.LoginFacade;
import business_logic.models.Club;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.ClubCell;
import presentation.tableViewCell.OnSaleCell;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;

public class HomeAdminController {
	
	@FXML private Label nameAdmin;
	
	@FXML private TableView<AdminCell> clubTable;
	
	@FXML private TableColumn<AdminCell, String> logo;
	@FXML private TableColumn<AdminCell, String> name;
	@FXML private TableColumn<AdminCell, Integer> idClub;
	
	private HomeAdminFacade myFacade;
	
	public HomeAdminController() {
		myFacade = new HomeAdminFacade();
	}
	
	public HomeAdminController getHomeClubController() {
		return this;
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
	
	@FXML protected void handleAddClub(ActionEvent event) {
  		Stage popupwindow=new Stage();     
  		popupwindow.initModality(Modality.APPLICATION_MODAL);
  		popupwindow.setTitle("Add club"); 
  		FXMLLoader loader = new FXMLLoader();
  		loader.setLocation(ClientUI.class.getResource("popupAddClub.fxml"));
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
	
	@FXML public void initialize() {
        logo.setCellValueFactory(cellData -> cellData.getValue().logoProperty());
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		clubTable.setItems(myFacade.getCellData());
		
	TableColumn col_action = new TableColumn<>("Update");
	col_action.setSortable(false);
	col_action.setPrefWidth(200.0);
	col_action.setStyle("-fx-alignment: CENTER;");
	
    col_action.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<AdminCell, Boolean>, 
            ObservableValue<Boolean>>() {

        @Override
        public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<AdminCell, Boolean> p) {
        	return new SimpleBooleanProperty(p.getValue() != null);
        }
    });
	
    col_action.setCellFactory(
            new Callback<TableColumn<AdminCell, Boolean>, TableCell<AdminCell, Boolean>>() {

		@Override
		public TableCell<AdminCell, Boolean> call(TableColumn<AdminCell, Boolean> p) {
			return new ButtonCell();
		}
     
    });
	
    clubTable.getColumns().add(col_action);
    
    clubTable.setItems(myFacade.getCellData());
	

    
	}
    

	private class ButtonCell extends TableCell<AdminCell, Boolean> {
	    final Button cellButton = new Button("Update");
	    
	    ButtonCell(){
	        cellButton.setOnAction(new EventHandler<ActionEvent>(){
	            @Override
	            public void handle(ActionEvent t) {
	            	final AdminCell item = (AdminCell) getTableRow().getItem();
	            	Stage popupwindow=new Stage();     
	          		popupwindow.initModality(Modality.APPLICATION_MODAL);
	          		popupwindow.setTitle("Update cLub"); 
	          		FXMLLoader loader = new FXMLLoader();
	          		loader.setController(new PopupUpdateClubController(item,getHomeClubController()));
	          		loader.setLocation(ClientUI.class.getResource("PopupUpdateClub.fxml"));
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
	
	
}

