package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.FundStores.Account;
import phase2.Operators.BankAccountUser.User;

public class DeleteBankAccountMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private ComboBox<String> userBankAccounts;
	@FXML
	private Label userBankAccountsStatus;
	@FXML
	private TextField masterAccessKeyIn;
	@FXML
	private Label masterAccessKeyInStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		this.user = user;
		for (Account account : this.user.getAccountsCreated()) {
			this.userBankAccounts.getItems().add(String.valueOf(account.getAccountNum()) +
					" " + account.getAccountType());
		}
	}

	public void deleteBankAccount(ActionEvent event) throws Exception {
		Account selectedAccount = null;
		String[] split = this.userBankAccounts.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split[0])) {
				selectedAccount = account;
			}
		}
		if (this.masterAccessKeyIn.getText().equals(GUI.getBM().getMasterAccessKey())) {
			this.masterAccessKeyInStatus.setText("correct master access key");
		} else {
			this.masterAccessKeyInStatus.setText("incorrect master access key");
		}
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			this.userBankAccountsStatus.setText(this.userBankAccounts.getValue() + " selected");
		} else {
			this.userBankAccountsStatus.setText("no bank account selected. try again");
			this.endStatus.setText("");
		}
		if (this.masterAccessKeyInStatus.getText().equals("correct master access key") &&
				(!(this.userBankAccounts.getSelectionModel().isEmpty()))) {
			GUI.getBM().deleteAccount(this.user, selectedAccount.getAccountNum());
			this.endStatus.setText("bank account deleted");
		} else {
			this.endStatus.setText("");
		}
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