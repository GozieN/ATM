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

public class BankManagerUserTransactionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	public void initialize(User user) {
		this.user = user;
	}

	public void withdraw(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("WithdrawMenuScene.fxml"));
		Parent parent = loader.load();
		Scene withdrawMenuScene = new Scene(parent);
		WithdrawMenuController controller = loader.getController();
		controller.initialize(this.user, "BankManager");
		mainStage.setScene(withdrawMenuScene);
		mainStage.show();
	}

	public void deposit(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DepositMenuScene.fxml")); //depositmenuoptions -> cash, cheque
		Parent parent = loader.load();
		Scene depositMenuScene = new Scene(parent);
		DepositMenuController controller = loader.getController();
		controller.initialize(this.user, "BankManager");
		mainStage.setScene(depositMenuScene);
		mainStage.show();
	}

	public void transfer(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferMenuScene = new Scene(parent);
		TransferOptionsMenuController controller = loader.getController();
		controller.initialize(this.user, "BankManager");
		mainStage.setScene(transferMenuScene);
		mainStage.show();
	}

	public void payBill(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("PayBillMenuScene.fxml"));
		Parent parent = loader.load();
		Scene payBillMenuScene = new Scene(parent);
		PayBillMenuController controller = loader.getController();
		controller.initialize(this.user, "BankManager");
		mainStage.setScene(payBillMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("BankManagerUserInteractionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene bankManagerUserInteractionsMenuScene = new Scene(parent);
		BankManagerUserInteractionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(bankManagerUserInteractionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}