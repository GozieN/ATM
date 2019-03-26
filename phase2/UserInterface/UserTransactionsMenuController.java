package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class UserTransactionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	public void initialize(User user) {
		this.user = user;
	}

	public void withdraw(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("WithdrawMenuScene.fxml"));
		Parent parent = loader.load();
		Scene withdrawMenuScene = new Scene(parent);
		WithdrawMenuController controller = loader.getController();
		controller.initialize(this.user, "User");
		mainStage.setScene(withdrawMenuScene);
		mainStage.show();
	}

	public void deposit(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DepositOptionsMenuScene.fxml")); //depositmenuoptions -> cash, cheque
		Parent parent = loader.load();
		Scene depositOptionsMenuScene = new Scene(parent);
		DepositOptionsMenuController controller = loader.getController();
		controller.initialize(this.user, "User");
		mainStage.setScene(depositOptionsMenuScene);
		mainStage.show();
	}

	public void transfer(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferMenuScene = new Scene(parent);
		TransferOptionsMenuController controller = loader.getController();
		controller.initialize(this.user, "User");
		mainStage.setScene(transferMenuScene);
		mainStage.show();
	}

	public void payBill(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("PayBillMenuScene.fxml"));
		Parent parent = loader.load();
		Scene payBillMenuScene = new Scene(parent);
		PayBillMenuController controller = loader.getController();
		controller.initialize(this.user, "User");
		mainStage.setScene(payBillMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
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

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}