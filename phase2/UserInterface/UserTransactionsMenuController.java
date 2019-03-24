package phase2.UserInterface;

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

public class UserTransactionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private Label withdrawStatus;
	@FXML
	private Label depositStatus;
	@FXML
	private Label transferStatus;
	@FXML
	private Label payBillStatus;

	public void initialize(User user) {
		this.user = user;
	}

	public void withdraw(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("WithdrawMenuScene.fxml"));
			Parent parent = loader.load();
			Scene withdrawMenuScene = new Scene(parent);
			WithdrawMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(withdrawMenuScene);
			mainStage.show();
		} else {
			this.withdrawStatus.setText("you do not have any accounts to withdraw from");
		}
	}

	public void deposit(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DepositMenuScene.fxml"));
			Parent parent = loader.load();
			Scene depositMenuScene = new Scene(parent);
			DepositMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(depositMenuScene);
			mainStage.show();
		} else {
			this.depositStatus.setText("you do not have any accounts to deposit to");
		}
	}

	public void transfer(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("TransferOptionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene transferMenuScene = new Scene(parent);
			TransferOptionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(transferMenuScene);
			mainStage.show();
		} else {
			this.transferStatus.setText("you do not have any accounts to transfer from");
		}
	}

	public void payBill(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PayBillMenuScene.fxml"));
			Parent parent = loader.load();
			Scene payBillMenuScene = new Scene(parent);
			PayBillMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(payBillMenuScene);
			mainStage.show();
		} else {
			this.payBillStatus.setText("you do not have any accounts to pay a bill from");
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