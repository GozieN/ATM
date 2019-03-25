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
		User user = null;
		try {
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			user = (User)in.readObject();
			in.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user.getUsername().equals(this.userUsernameIn.getText())) {
			if (this.masterAccessKeyIn.getText().equals(GUI.getBM().getMasterAccessKey())) {
				Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "BankManagerLoginMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}