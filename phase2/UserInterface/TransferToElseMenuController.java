package phase2.UserInterface;

import phase2.FundStores.*;

import java.io.*;

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

public class TransferToElseMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	@FXML
	private ComboBox<String> userBankAccounts;
	@FXML
	private Label userBankAccountsStatus;
	@FXML
	private TextField toAccountIn;
	@FXML
	private Label toAccountInStatus;
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
			this.userBankAccounts.getItems().add(String.valueOf(account.getAccountNum()) +
					" " + account.getAccountType());
		}
	}

	public void transfer(ActionEvent event) throws Exception {
		Account selectedAccount1 = null;
		Account selectedAccount2 = null;
		String[] split1 = this.userBankAccounts.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split1[0])) {
				selectedAccount1 = account;
			}
		}
		if (!(this.toAccountIn.getText().equals(""))) {
			try {
				FileInputStream input = new FileInputStream("phase2/txtfiles/accountDatabase.txt");
				ObjectInputStream in = new ObjectInputStream(input);
				selectedAccount2 = (Account)in.readObject();
				in.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!(selectedAccount2.getAccountNum() == Integer.parseInt(this.toAccountIn.getText()))) {
				this.toAccountInStatus.setText("that bank account does not exist. try again");
				this.endStatus.setText("");
			}
		} else {
			this.toAccountInStatus.setText("no bank account entered. try again");
			this.endStatus.setText("");
		}
		int amount = Integer.parseInt(this.amountIn.getText());
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			this.userBankAccountsStatus.setText(this.userBankAccounts.getValue() + " selected");
		} else {
			this.userBankAccountsStatus.setText("no bank account selected. try again");
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