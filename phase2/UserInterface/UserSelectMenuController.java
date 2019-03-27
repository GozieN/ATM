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
		ArrayList<User> userList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			userList = (ArrayList<User>)in.readObject();
			in.close();
			file.close();
			for (User obj: userList) {
				if (obj.getUsername().equals(this.userUsernameIn.getText())) {
					if (this.masterAccessKeyIn.getText().equals(GUI.getBM().getMasterAccessKey())) {
						Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("BankManagerUserInteractionsMenuScene.fxml"));
						Parent parent = loader.load();
						Scene bankManagerUserInteractionsMenuScene = new Scene(parent);
						BankManagerUserInteractionsMenuController controller = loader.getController();
						controller.initialize(obj);
						mainStage.setScene(bankManagerUserInteractionsMenuScene);
						mainStage.show();
					} else {
						this.masterAccessKeyInStatus.setText("incorrect master access key. try again");
					}
				} else {
					this.userUsernameInStatus.setText("that user does not exist. try again");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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