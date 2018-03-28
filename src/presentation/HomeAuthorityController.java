package presentation;

import java.io.IOException;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.HomeAuthorityFacade;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.ClubCell;


public class HomeAuthorityController {
	
	@FXML private Label nameAuthority;
	
	@FXML private TableView<ClubCell> authorityTable;
	
	//@FXML private TableColumn<ClubCell, String> logo;
	@FXML private TableColumn<ClubCell, String> name;
	@FXML private TableColumn<ClubCell, Boolean> state;
	/*@FXML private TableColumn<SaleCell, String> city;
	@FXML private TableColumn<SaleCell, String> country;
	@FXML private TableColumn<SaleCell, String> championship;*/
	@FXML private TableColumn<ClubCell, Integer> idClub;
	@FXML private TableColumn<ClubCell, Integer> sumPurchases;
	@FXML private TableColumn<ClubCell, Integer> sumSold;
	
	
	
	private HomeAuthorityFacade myFacade;
	
	public HomeAuthorityController() {
		myFacade = new HomeAuthorityFacade();
	}
	
	@FXML public void initialize() {

		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		state.setCellValueFactory(cellData ->cellData.getValue().stateProperty());
		sumPurchases.setCellValueFactory(cellData ->cellData.getValue().sumPurchasesProperty());
		sumSold.setCellValueFactory(cellData ->cellData.getValue().sumSoldProperty());
		
		TableColumn col_action = new TableColumn<>("");
		col_action.setSortable(false);
		col_action.setPrefWidth(133.0);
		col_action.setStyle("-fx-alignment: CENTER;");
		
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ClubCell, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ClubCell, Boolean> p) {
            	return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
		
        col_action.setCellFactory(
                new Callback<TableColumn<ClubCell, Boolean>, TableCell<ClubCell, Boolean>>() {

			@Override
			public TableCell<ClubCell, Boolean> call(TableColumn<ClubCell, Boolean> p) {
				return new ButtonCell();
			}
         
        });
		
        authorityTable.getColumns().add(col_action);
		authorityTable.setItems(myFacade.getCellData());
	}
	
	private class ButtonCell extends TableCell<ClubCell, Boolean> {
        Button cellButton = new Button("Unblock");
        ButtonCell(){
        	
        	cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                	final ClubCell item = (ClubCell) getTableRow().getItem();
                	Boolean change = myFacade.changeState(item.getIdClub());
                	item.SetState(change);
                	cellButton = new Button("Block");
                	System.out.println(cellButton.getText());
                	setGraphic(cellButton);
                	getAuthorityTable().refresh();
                	System.out.println(change);
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
	
	 protected TableView<ClubCell> getAuthorityTable() {	 
		 return authorityTable;
	 }
}