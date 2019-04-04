package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import java.util.*;

public class BankManagerInteractionsMenuController extends Menu implements Serializable {
	private User user = null;

	public void selectUser(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserSelectMenuScene.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public void setDate(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SetAtmDateMenuScene.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public void viewInbox(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("InboxMenuScene.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public void viewSignedContract(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewSignedContractMenuScene.fxml"));
		Parent parent = loader.load();
		Scene viewSignedContractMenuScene = new Scene(parent);
		ViewSignedContractMenuController controller = loader.getController();
		controller.initialize(this.user, "Bank Manager");
		mainStage.setScene(viewSignedContractMenuScene);
		mainStage.show();
	}

	public void viewCapabilities(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewCapabilitiesMenuScene.fxml"));
		Parent parent = loader.load();
		Scene viewCapabilitiesMenuScene = new Scene(parent);
		ViewCapabilitiesMenuController controller = loader.getController();
		controller.initialize(this.user,"Bank Manager");
		mainStage.setScene(viewCapabilitiesMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("BankManagerLoginMenuScene.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}