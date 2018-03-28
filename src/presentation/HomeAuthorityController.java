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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.tableViewCell.AdminCell;
import presentation.tableViewCell.ClubCell;
import presentation.tableViewCell.OnSaleCell;


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
		
/*		TableColumn col_action = new TableColumn<>("");
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
         
        });*/
		
	      TableColumn actionCol = new TableColumn("");
	        actionCol.setCellValueFactory(new PropertyValueFactory<>("block"));

	        Callback<TableColumn<ClubCell, String>, TableCell<ClubCell, String>> cellFactory
	                = //
	                new Callback<TableColumn<ClubCell, String>, TableCell<ClubCell, String>>() {
	            @Override
	            public TableCell call(final TableColumn<ClubCell, String> param) {
	                final TableCell<ClubCell, String> cell = new TableCell<ClubCell, String>() {

	                    final Button btn = new Button("");

	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        ClubCell club = (ClubCell) getTableRow().getItem();
	                        if (empty) {
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                        	if(club.getState()) {
	                        		btn.setText("Unblock");
	                        	}else {
	                        		btn.setText("Block");
	                        	}
	                        	btn.setOnAction(event -> {
	                            	Boolean change = myFacade.changeState(club.getIdClub());
	                            	club.SetState(change);
	                            	if(club.getState()) {
	                            		btn.setText("Unblock");
	                            	}else {
	                            		btn.setText("Block");
	                            	}
	                            	setGraphic(btn);
	                            	getAuthorityTable().refresh();
	                            	System.out.println(change);
	                            });
                        		setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };

	        actionCol.setCellFactory(cellFactory);
		
        authorityTable.getColumns().add(actionCol);
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