package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class DeleteUserAccountMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	TextField masterAccessKeyIn;
	@FXML
	Label masterAccessKeyInStatus;

	public void initialize(User user) {
		this.user = user;
	}

	public void deleteUserAccount(ActionEvent event) throws Exception {
		for (User user : GUI.getBM().getUsers()) {
			if (this.masterAccessKeyIn.getText().equals(GUI.getBM().getMasterAccessKey())
					&& user.getUsername().equals(this.user.getUsername())) {
				GUI.getBM().deleteUser(user);
				exit(event);
			} else {
				this.masterAccessKeyInStatus.setText("incorrect master access key. try again");
			}
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