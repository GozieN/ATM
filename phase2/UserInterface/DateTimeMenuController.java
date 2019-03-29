package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.FundStores.ATM;

public class DateTimeMenuController extends Menu implements java.io.Serializable {
	@FXML
	private Label dateTime;

	public void initialize() throws Exception {
		ATM atm = GUI.getAtm();
		String date = atm.getDate();
		String time = atm.getTime();
		String dateTime = atm.getDate() + " " + atm.getTime();
		// angela
		this.dateTime.setText(dateTime);
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "MainMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}