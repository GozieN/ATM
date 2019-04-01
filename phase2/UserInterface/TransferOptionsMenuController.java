package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class TransferOptionsMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	public void initialize(User user, String operatorType) {
		this.user = user;
		this.operatorType = operatorType;
	}

	public void transferToSelf(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferToSelfMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferToSelfMenuScene = new Scene(parent);
		TransferToSelfMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(transferToSelfMenuScene);
		mainStage.show();
	}

	public void transferToElse(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TransferToSelfMenuScene.fxml"));
		Parent parent = loader.load();
		Scene transferToElseMenuScene = new Scene(parent);
		TransferToElseMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(transferToElseMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		if (this.operatorType.equals("Bank Manager")) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BankManagerUserTransactionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene bankManagerUserTransactionsMenuScene = new Scene(parent);
			BankManagerUserTransactionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(bankManagerUserTransactionsMenuScene);
			mainStage.show();
		} else if (this.operatorType.equals("User")) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UserTransactionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene userTransactionsMenuScene = new Scene(parent);
			UserTransactionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(userTransactionsMenuScene);
			mainStage.show();
		}
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}