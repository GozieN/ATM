package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.event.*;

public class DateTimeMenuController extends Menu implements java.io.Serializable {
	@FXML
	private Label dateTime;

	public void initialize() throws Exception {
		String dateTime = "";
		// angela TODO: assign dateTime to date, time from file
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