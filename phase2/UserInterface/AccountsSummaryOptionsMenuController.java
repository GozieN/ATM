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

public class AccountsSummaryOptionsMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	@FXML
	private ComboBox<String> userBankAccounts;
	@FXML
	private Label userBankAccountsStatus;

	public void initialize(User user, String operatorType) {
		this.user = user;
		this.operatorType = operatorType;
	}

	public void viewAllAccountsSummary(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AllAccountsSummaryMenuScene.fxml"));
		Parent parent = loader.load();
		Scene allAccountsSummaryMenuScene = new Scene(parent);
		AllAccountsSummaryMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(allAccountsSummaryMenuScene);
		mainStage.show();
	}

	public void viewSingleAccountSummary(ActionEvent event) throws Exception {
		Account selectedAccount = null;
		String[] split = this.userBankAccounts.getValue().split("\\s");
		for (Account account : this.user.getAccountsCreated()) {
			if (account.getAccountNum() == Integer.parseInt(split[0])) {
				selectedAccount = account;
			}
		}
		if (!(this.userBankAccounts.getSelectionModel().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SingleAccountSummaryMenuScene.fxml"));
			Parent parent = loader.load();
			Scene singleAccountSummaryMenuScene = new Scene(parent);
			SingleAccountSummaryMenuController controller = loader.getController();
			controller.initialize(this.user, selectedAccount, this.operatorType);
			mainStage.setScene(singleAccountSummaryMenuScene);
			mainStage.show();
		} else {
			this.userBankAccountsStatus.setText("no bank account selected. try again");
		}
	}

	public void back(ActionEvent event) throws Exception {
		if (this.operatorType.equals("BankManager")) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BankManagerUserInteractionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene bankManagerUserInteractionsMenuScene = new Scene(parent);
			BankManagerUserInteractionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(bankManagerUserInteractionsMenuScene);
			mainStage.show();
		} else if (this.operatorType.equals("User")) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UserInteractionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene userInteractionsMenuScene = new Scene(parent);
			UserInteractionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(userInteractionsMenuScene);
			mainStage.show();
		}
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}