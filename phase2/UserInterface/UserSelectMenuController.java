package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.BankManagerUserInteractionsMenuController;
import phase2.UserInterface.GUI;
import phase2.UserInterface.Menu;
import java.util.*;

public class UserSelectMenuController extends Menu implements java.io.Serializable {
	@FXML
	TextField userUsernameIn;
	@FXML
	Label userUsernameInStatus;
	@FXML
	TextField masterAccessKeyIn;
	@FXML
	Label masterAccessKeyInStatus;

	public void selectUser(ActionEvent event) throws Exception {
		for (User user : getBM().getUsers()) {
			if (user.getUsername().equals(this.userUsernameIn.getText())) {
				this.userUsernameInStatus.setText("existing user");
				if (this.masterAccessKeyIn.getText().equals(getBM().getMasterAccessKey())) {
					Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("BankManagerUserInteractionsMenuScene.fxml"));
					Parent parent = loader.load();
					Scene bankManagerUserInteractionsMenuScene = new Scene(parent);
					BankManagerUserInteractionsMenuController controller = loader.getController();
					controller.initialize(user);
					mainStage.setScene(bankManagerUserInteractionsMenuScene);
					mainStage.show();
				} else {
					this.masterAccessKeyInStatus.setText("incorrect master access key. try again");
				}
			} else {
				this.userUsernameInStatus.setText("that user does not exist. try again");
			}
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "BankManagerInteractionsMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}