package phase2.UserInterface;

import javafx.scene.control.ComboBox;
import phase2.FundHolders.*;
import phase2.Operators.*;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.*;

public class RequestNewBankAccountCreationMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private ComboBox<String> bankAccountTypes;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		this.user = user;
		this.bankAccountTypes.getItems().addAll("chequing", "credit", "debit", "savings");
	}

	public void requestNewBankAccountCreation(ActionEvent event) throws Exception {
		if (((String)this.bankAccountTypes.getValue()).equals("chequing")) {
			GUI.getBM().createNewAccount(0, "chequing", this.user);
			this.endStatus.setText("you have requested a new chequing bank account");
		} else if (((String)this.bankAccountTypes.getValue()).equals("credit")) {
			GUI.getBM().createNewAccount(0, "credit", this.user);
			this.endStatus.setText("you have requested a new credit bank account");
		} else if (((String)this.bankAccountTypes.getValue()).equals("debit")) {
			GUI.getBM().createNewAccount(0, "debit", this.user);
			this.endStatus.setText("you have requested a new debit bank account");
		} else if (((String)this.bankAccountTypes.getValue()).equals("savings")) {
			GUI.getBM().createNewAccount(0, "savings", this.user);
			this.endStatus.setText("you have requested a new savings bank account");
		} else {
			this.endStatus.setText("no bank account type selected. try again");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserInteractionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene userInteractionsMenuScene = new Scene(parent);
		UserInteractionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(userInteractionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}