package phase2.UserInterface;

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
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.event.*;

public class TransferToElseMenuController extends Menu implements java.io.Serializable {
	private User user;

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

	public void initialize(User user) {
		this.user = user;
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
		if (!(toAccountIn.getText().equals(""))) {
			try {
				FileInputStream input = new FileInputStream("phase2/accountDatabase.txt");
				ObjectInputStream in = new ObjectInputStream(input);
				selectedAccount2 = (Account)in.readObject();
				in.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!(selectedAccount2.getAccountNum() == Integer.parseInt(toAccountIn.getText()))) {
				toAccountInStatus.setText("that bank account does not exist. try again");
				endStatus.setText("");
			}
		} else {
			toAccountInStatus.setText("no bank account entered. try again");
			endStatus.setText("");
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
			selectedAccount1.transfer(amount, selectedAccount2);
			this.endStatus.setText("transfer successful");
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
		controller.initialize(this.user);
		mainStage.setScene(transferOptionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}