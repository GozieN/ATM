package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.FundStores.ATM;
import phase2.otherfiles.DateTimeManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DateTimeMenuController extends Menu implements java.io.Serializable {
	@FXML
	private Label dateTime;

	public void initialize() throws Exception {
		String s = new String(getLastLine());
		this.dateTime.setText(s);
	}

	public String getLastLine() {
		String currLine;
		String lastLine = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("phase2/txtfiles/date.txt"));
			while ((currLine = br.readLine()) != null) {
				lastLine = currLine;
			}
		} catch (IOException e) {
		}
		return lastLine;
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "MainMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}