package phase2.UserInterface;

import phase2.FundStores.*;
import phase2.Operators.*;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.event.*;

public class DepositOptionsMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	public void initialize(User user, String operatorType) {
		this.user = user;
		this.operatorType = operatorType;
	}

	public void depositCash(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DepositCashMenuScene.fxml"));
		Parent parent = loader.load();
		Scene depositCashMenuScene = new Scene(parent);
		DepositCashMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(depositCashMenuScene);
		mainStage.show();
	}

	public void depositCheque(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DepositChequeMenuScene.fxml"));
		Parent parent = loader.load();
		Scene depositChequeMenuScene = new Scene(parent);
		DepositChequeMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(depositChequeMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		if (this.operatorType.equals("BankManager")) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BankManagerUserInteractionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene bankManagerUserInteractionsMenuScene = new Scene(parent);
			BankManagerUserInteractionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(bankManagerUserInteractionsMenuScene);
			mainStage.show();
		} else if (this.operatorType.equals("User")) {
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
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}