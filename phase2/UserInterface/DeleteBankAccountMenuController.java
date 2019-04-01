package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.FundStores.Account;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Debt.lineofcredit;
import phase2.Operators.BankAccountUser.User;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			// angela TODO: method (check delete user account menu controller for similarity)
			GUI.getBM().deleteAccount(this.user, selectedAccount.getAccountNum());
		} else {
			this.userBankAccountsStatus.setText("no bank account selected. try again");
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