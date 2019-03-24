package phase2.UserInterface;

import javafx.scene.control.ComboBox;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.*;

public class TransferToSelfMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private ComboBox<String> userBankAccounts1;
	@FXML
	private Label userBankAccounts1Status;
	@FXML
	private ComboBox<String> userBankAccounts2;
	@FXML
	private Label userBankAccounts2Status;
	@FXML
	private TextField amount;
	@FXML
	private Label amountStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		this.user = user;
		for (Account account : this.user.getAccountsCreated()) {
			this.userBankAccounts1.getItems().add(String.valueOf(account.getAccountNum()) +
					" " + account.getAccountType());
			this.userBankAccounts2.getItems().add(String.valueOf(account.getAccountNum()) +
					" " + account.getAccountType());
		}
	}

	public void transfer(ActionEvent event) throws Exception {
		Account selectedAccount1 = null;
		Account selectedAccount2 = null;
		String[] split1 = this.userBankAccounts1.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split1[0])) {
				selectedAccount1 = account;
			}
		}
		String[] split2 = this.userBankAccounts2.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split2[0])) {

			}
		}
		int amount = Integer.parseInt(this.amount.getText());
		if (!(this.userBankAccounts1.getSelectionModel().isEmpty()) &&
		!(this.userbank)) {
			this.userBankAccounts1Status.setText(this.userBankAccounts1.getValue() + " selected");
			if (amount >= 0 && amount % 5 == 0) {
				this.amountStatus.setText("valid amount");
				if (amount <= selectedAccount1.getBalance()) {
					selectedAccount1.withdrawFromATM(amount);
					this.endStatus.setText("withdrawal successful");
				} else {
					this.amountStatus.setText("this account does not have enough funds to withdraw $" + amount);
					this.endStatus.setText("");
				}
			} else {
				this.amountStatus.setText("invalid amount. try again");
				this.endStatus.setText("");
			}
		} else {
			this.userBankAccounts1Status.setText("no bank account selected. try again");
			this.amountStatus.setText("");
			this.endStatus.setText("");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferOptionsMenuScene = new Scene(parent);
		TransferOptionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(transferOptionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}