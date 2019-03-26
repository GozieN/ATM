package phase2.UserInterface.BankAccountUserMenus.UserToAccountInteractionMenus;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.AccountMenus.SummarizeAccountsMenus.AccountsSummaryOptionsMenuController;
import phase2.UserInterface.BankAccountUserMenus.UserChangesAndRequests.ChangeUserAccountPasswordMenuController;
import phase2.UserInterface.BankAccountUserMenus.UserChangesAndRequests.RequestNewBankAccountCreationMenuController;
import phase2.UserInterface.MainMenu.Menu;

public class UserInteractionsMenuController extends Menu implements java.io.Serializable {
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
			controller.initialize(this.user, "User");
			mainStage.setScene(accountsSummaryOptionsMenuScene);
			mainStage.show();
		} else {
			this.noAccounts1.setText("you have no accounts");
		}
	}

	public void performTransaction(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UserTransactionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene userTransactionsMenuScene = new Scene(parent);
			UserTransactionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(userTransactionsMenuScene);
			mainStage.show();
		} else {
			this.noAccounts2.setText("you have no accounts");
		}
	}

	public void requestNewBankAccountCreation(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("RequestNewBankAccountCreationMenuScene.fxml"));
		Parent parent = loader.load();
		Scene requestNewBankAccountCreationMenuScene = new Scene(parent);
		RequestNewBankAccountCreationMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(requestNewBankAccountCreationMenuScene);
		mainStage.show();
	}

	public void changeUserAccountPassword(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ChangeUserAccountPasswordMenuScene.fxml"));
		Parent parent = loader.load();
		Scene changeUserAccountPasswordMenuScene = new Scene(parent);
		ChangeUserAccountPasswordMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(changeUserAccountPasswordMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "UserLoginMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}