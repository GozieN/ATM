package phase2.UserInterface;

import phase2.FundStores.*;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.event.*;
import phase2.FundStores.Asset.ChequingAccount;
import phase2.FundStores.Asset.Debit;
import phase2.Operators.BankAccountUser.User;

public class DepositCashMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	@FXML
	private TextField amountIn;
	@FXML
	private Label amountInStatus;
	@FXML
	private Label primaryStatus;
	@FXML
	private ComboBox<String> userBankAccounts;
	@FXML
	private Label userBankAccountsStatus;
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

	public void depositToPrimary(ActionEvent event) throws Exception {
		this.userBankAccountsStatus.setText("");
		this.endStatus.setText("");
		Account selectedAccount = null;
		int numOfAccounts = this.user.getAccountsCreated().size();
		int counter = 0;
		for (Account account : this.user.getAccountsCreated()) {
			counter += 1;
			if (account instanceof ChequingAccount && counter != numOfAccounts) {
				if (((ChequingAccount)account).isPrimary) {
					selectedAccount = account;
					primaryStatus.setText("");
				}
			} else {
				primaryStatus.setText("you do not have a primary account");
			}
		}
		int amount = -1;
		if (!(this.amountIn.getText().isEmpty())) {
			amount = Integer.parseInt(this.amountIn.getText());
			this.amountInStatus.setText("");
		} else {
			this.amountInStatus.setText("this field cannot be empty. try again");
		}
		if (!(selectedAccount == null)) {
			if (amount >= 0 && amount % 5 == 0) {
				this.amountInStatus.setText("valid amount");
				this.primaryStatus.setText("deposit successful");
				selectedAccount.depositIntoATM(amount);
            } else {
				this.amountInStatus.setText("invalid amount. try again");
				this.primaryStatus.setText("");
			}
		}
	}

	public void deposit(ActionEvent event) throws Exception {
		this.primaryStatus.setText("");
		Account selectedAccount = null;
		try {
			String[] split = this.userBankAccounts.getValue().split("\\s");
			for (Account account : this.user.getAccountsCreated()) {
				if (account.getAccountNum() == Integer.parseInt(split[0])) {
					selectedAccount = account;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int amount = -1;
		if (!(this.amountIn.getText().isEmpty())) {
			amount = Integer.parseInt(this.amountIn.getText());
			this.amountInStatus.setText("");
		} else {
			this.amountInStatus.setText("this field cannot be empty. try again");
		}
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			this.userBankAccountsStatus.setText(this.userBankAccounts.getValue() + " selected");
			if (amount >= 0 && amount % 5 == 0) {
				this.amountInStatus.setText("valid amount");
				this.endStatus.setText("deposit successful");
				selectedAccount.depositIntoATM(amount);
            } else {
				this.amountInStatus.setText("invalid amount. try again");
				this.endStatus.setText("");
			}
		} else {
			this.userBankAccountsStatus.setText("no bank account selected. try again");
			this.endStatus.setText("");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DepositOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene depositOptionsMenuScene = new Scene(parent);
		DepositOptionsMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(depositOptionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}