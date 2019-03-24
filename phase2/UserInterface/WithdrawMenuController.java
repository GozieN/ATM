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

public class WithdrawMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private ComboBox<String> userBankAccounts;
	@FXML
	private Label userBankAccountsStatus;
	@FXML
	private TextField amount;
	@FXML
	private Label amountStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		this.user = user;
		for (Account account : this.user.getAccountsCreated()) {
			this.userBankAccounts.getItems().add(String.valueOf(account.getAccountNum()) +
					" " + account.getAccountType());
		}
	}

	public void withdraw(ActionEvent event) throws Exception {
		Account selectedAccount = null;
		String[] split = this.userBankAccounts.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split[0])) {
				selectedAccount = account;
			}
		}
		int amount = Integer.parseInt(this.amount.getText());
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			this.userBankAccountsStatus.setText(this.userBankAccounts.getValue() + " selected");
			if (amount >= 0 && amount % 5 == 0) {
				this.amountStatus.setText("valid amount");
				if (amount <= selectedAccount.getBalance()) {
					selectedAccount.withdrawFromATM(amount);
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
			this.userBankAccountsStatus.setText("no bank account selected. try again");
			this.amountStatus.setText("");
			this.endStatus.setText("");
		}
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