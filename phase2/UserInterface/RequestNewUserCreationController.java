package phase2.UserInterface;

import phase2.FundHolders.*;
import phase2.Operators.*;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.*;

public class RequestNewUserCreationController extends Menu implements java.io.Serializable {
	@FXML
	private TextField newUsernameIn;
	@FXML
	private Label newUsernameInStatus;
	@FXML
	private PasswordField newPasswordIn;
	@FXML
	private Label newPasswordInStatus;
	@FXML
	private Label endStatus;

	public void requestNewUserCreation(ActionEvent event) throws Exception {
		try {
			User user = null;
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			user = (User) in.readObject();
			in.close();
			file.close();
			if (!(user.getUsername().equals(this.newUsernameIn.getText())) &&
					!(this.newUsernameIn.getText().equals(""))) {
				this.newUsernameInStatus.setText("valid username");
			} else {
				this.newUsernameInStatus.setText("this username is not available. try again");
			}
			if (!(this.newPasswordIn.getText()).equals("")) {
				this.newPasswordInStatus.setText("valid password");
			} else {
				this.newPasswordInStatus.setText("this field cannot be left blank. try again");
			}
			if (this.newUsernameInStatus.getText().equals("valid username") &&
					this.newPasswordInStatus.getText().equals("valid password")) {
				GUI.getBM().createUser(this.newUsernameIn.getText(), this.newPasswordIn.getText());
				this.endStatus.setText("your user creation request is being processed \n" +
						"click exit to return to the main menu");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "MainMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}