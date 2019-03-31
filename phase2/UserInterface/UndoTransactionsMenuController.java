package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.FundStores.Account;
import phase2.Operators.BankAccountUser.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class UndoTransactionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private ComboBox<String> userBankAccounts;
	@FXML
	private Label userBankAccountsStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		ArrayList<User> userList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			userList = (ArrayList<User>) in.readObject();
			in.close();
			file.close();
			for (User obj: userList) {
				if (obj.getUsername().equals(user.getUsername())) {
					this.user = obj;
					break;
				}
			}
		} catch (Exception ex) {ex.printStackTrace();}

		for (Account account : this.user.getAccountsCreated()) {
			this.userBankAccounts.getItems().add(String.valueOf(account.getAccountNum()) +
					" " + account.getAccountType());
		}
	}

	public void undoTransaction(ActionEvent event) throws Exception {
		Account selectedAccount = null;
		String[] split = this.userBankAccounts.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split[0])) {
				selectedAccount = account;
			}
		}
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			this.userBankAccountsStatus.setText(this.userBankAccounts.getValue() + " selected");
			if (!(selectedAccount.getHistory().isEmpty())) {
				selectedAccount.undoTransaction();
			} else {
				this.endStatus.setText("this account does not have any transactions");
			}
		} else {
			this.userBankAccountsStatus.setText("no bank account selected. try again");
			this.endStatus.setText("");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("BankManagerUserTransactionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene bankManagerUserTransactionsMenuScene = new Scene(parent);
		BankManagerUserTransactionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(bankManagerUserTransactionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}