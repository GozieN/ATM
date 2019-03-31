package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BankManagerUserTransactionsMenuController extends Menu implements java.io.Serializable {
	private User user;

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

//		this.user = user;
	}

	public void withdraw(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("WithdrawMenuScene.fxml"));
		Parent parent = loader.load();
		Scene withdrawMenuScene = new Scene(parent);
		WithdrawMenuController controller = loader.getController();
		controller.initialize(this.user, "Bank Manager");
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
		controller.initialize(this.user, "Bank Manager");
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
		controller.initialize(this.user, "BankManagerMenus");
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
		controller.initialize(this.user, "Bank Manager");
		mainStage.setScene(payBillMenuScene);
		mainStage.show();
	}

	public void undoTransactions(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UndoTransactionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene undoTransactionsMenuScene = new Scene(parent);
		UndoTransactionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(undoTransactionsMenuScene);
		mainStage.show();
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