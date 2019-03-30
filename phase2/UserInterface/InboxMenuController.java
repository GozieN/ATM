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
		this.inbox.setText(); // angela TODO: set to bank manager inbox txt
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "BankManagerInteractionsMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}