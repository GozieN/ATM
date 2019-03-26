package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class BankManagerUserInteractionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private Label noAccounts1;
	@FXML
	private Label noAccounts2;

	public void initialize(User user) {
		this.user = user;
	}

	public void viewAccountsSummary(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AccountsSummaryOptionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene accountsSummaryOptionsMenuScene = new Scene(parent);
			AccountsSummaryOptionsMenuController controller = loader.getController();
			controller.initialize(this.user, "BankManager");
			mainStage.setScene(accountsSummaryOptionsMenuScene);
			mainStage.show();
		} else {
			this.noAccounts1.setText("this user has no accounts");
		}
	}

	public void performTransaction(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BankManagerUserTransactionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene bankManagerUserTransactionsMenuScene = new Scene(parent);
			BankManagerUserTransactionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(bankManagerUserTransactionsMenuScene);
			mainStage.show();
		} else {
			this.noAccounts2.setText("this user has no accounts");
		}
	}

	public void createNewBankAccount(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CreateNewBankAccountMenuScene.fxml"));
		Parent parent = loader.load();
		Scene createNewBankAccountMenuScene = new Scene(parent);
		CreateNewBankAccountMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(createNewBankAccountMenuScene);
		mainStage.show();
	}

	public void deleteUserAccount(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DeleteUserAccountMenuScene.fxml"));
		Parent parent = loader.load();
		Scene deleteUserAccountMenuScene = new Scene(parent);
		DeleteUserAccountMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(deleteUserAccountMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "UserSelectMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}