package phase2.UserInterface;

import phase2.FundStores.*;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.event.*;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Debt.lineofcredit;
import phase2.Operators.BankAccountUser.User;

public class TransferToSelfMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	@FXML
	private ComboBox<String> userBankAccounts1;
	@FXML
	private Label userBankAccounts1Status;
	@FXML
	private ComboBox<String> userBankAccounts2;
	@FXML
	private Label userBankAccounts2Status;
	@FXML
	private TextField amountIn;
	@FXML
	private Label amountInStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user, String operatorType) {
		this.user = user;
		this.operatorType = operatorType;
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
				selectedAccount2 = account;
			}
		}
		int amount = Integer.parseInt(this.amountIn.getText());
		if (!(this.userBankAccounts1.getSelectionModel().isEmpty())) {
			this.userBankAccounts1Status.setText(this.userBankAccounts1.getValue() + " selected");
		} else {
			this.userBankAccounts1Status.setText("no bank account selected. try again");
			this.endStatus.setText("");
		}
		if (!(this.userBankAccounts2.getSelectionModel().isEmpty())) {
			this.userBankAccounts2Status.setText(this.userBankAccounts2.getValue() + " selected");
		} else {
			this.userBankAccounts2Status.setText("no bank account selected. try again");
			this.endStatus.setText("");
		}
		if (amount >= 0) {
			this.amountInStatus.setText("valid amount");
			if (amount > selectedAccount1.getBalance()) {
				this.amountInStatus.setText("this account does not have enough funds to transfer $" + amount);
				this.endStatus.setText("");
			}
		} else {
			this.amountInStatus.setText("invalid amount. try again");
			this.endStatus.setText("");
		}
		if (selectedAccount1 != null && selectedAccount2 != null &&
				this.amountInStatus.getText().equals("valid amount")) {
			if (selectedAccount1 instanceof Debit) {
				((Debit)selectedAccount1).transfer(amount, selectedAccount2);
				this.endStatus.setText("transfer successful");
			} else if (selectedAccount1 instanceof lineofcredit) {
				((lineofcredit)selectedAccount1).transfer(amount, selectedAccount2);
				this.endStatus.setText("transfer successful");
			} else {
				this.endStatus.setText("transfers cannot be made on this account");
			}
		} else {
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
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(transferOptionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}