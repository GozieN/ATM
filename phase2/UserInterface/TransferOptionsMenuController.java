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

public class TransferOptionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	public void initialize(User user) {
		this.user = user;
	}

	public void transferToSelf(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferToSelfMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferToSelfMenuScene = new Scene(parent);
		TransferToSelfMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(transferToSelfMenuScene);
		mainStage.show();
	}

	public void transferToElse(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferToSelfMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferToElseMenuScene = new Scene(parent);
		TransferToElseMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(transferToElseMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserTransactionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene userTransactionsMenuScene = new Scene(parent);
		UserTransactionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(userTransactionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}