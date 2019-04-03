package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import java.util.*;

public class InboxMenuController extends Menu implements java.io.Serializable {
	@FXML
	private Label inbox;

	public void initialize() {
		StringBuilder msg = new StringBuilder("");
		try {
			BufferedReader read = new BufferedReader(new FileReader("phase2/txtfiles/BankManagerInbox.txt"));
			String line = read.readLine();
			while (line != null) {
				msg.append(line);
				line = read.readLine();
			}
			this.inbox.setText(msg.toString());
		} catch (Exception ex) {ex.printStackTrace();}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "BankManagerInteractionsMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}