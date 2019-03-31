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
import phase2.FundStores.Debt.LineOfCredit;
import phase2.Operators.BankAccountUser.User;

public class WithdrawMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

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

	public void initialize(User user, String operatorType) {
		this.user = user;
		this.operatorType = operatorType;
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
					if (selectedAccount instanceof Debit) {
						((Debit)selectedAccount).withdrawFromATM(amount);
						this.endStatus.setText("withdrawal successful");
					} else if (selectedAccount instanceof LineOfCredit) {
						((LineOfCredit)selectedAccount).withdrawFromATM(amount);
						this.endStatus.setText("withdrawal successful");
					}
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

	public void withdrawFromDefault(ActionEvent event) throws Exception {
		Account selectedAccount = null;
		String[] split = this.userBankAccounts.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (split[1].equals("chequing")) {
				selectedAccount = account;
			}
		}
	}

	public void back(ActionEvent event) throws Exception {
		if (this.operatorType.equals("BankManagerMenus")) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BankManagerUserTransactionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene bankManagerUserTransactionsMenuScene = new Scene(parent);
			BankManagerUserTransactionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(bankManagerUserTransactionsMenuScene);
			mainStage.show();
		} else if (this.operatorType.equals("User")) {
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
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}