package presentation;

import business_logic.facades.HomeAdminFacade;
import business_logic.facades.ManageTeamClubFacade;
import presentation.tableViewCell.PlayerCell;

public class PopupUpdatePlayerController {

	PlayerCell myCell = null;
	private ManageTeamClubFacade myFacade;
	private ManageTeamClubController view;
	
	public PopupUpdatePlayerController(PlayerCell item, ManageTeamClubController view) {
		this.myCell=item;
		this.view=view;
		myFacade= new ManageTeamClubFacade();
	}

}
