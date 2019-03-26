package phase2.UserInterface.MoveMoneyMenus;

import phase2.FundStores.*;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.MainMenu.Menu;

public class DepositChequeMenuController extends Menu implements java.io.Serializable {
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

	public void deposit(ActionEvent event) throws Exception {
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
			if (amount >= 0) {
				this.amountStatus.setText("valid amount");
				selectedAccount.depositChequeToAccount(amount);
				this.endStatus.setText("deposit successful");
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