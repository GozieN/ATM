package phase2.UserInterface;

import java.io.*;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.GUI;
import phase2.UserInterface.Menu;

import java.util.*;

public class RequestNewUserAccountCreationMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField newUsernameIn;
	@FXML
	private Label newUsernameInStatus;
	@FXML
	private PasswordField newPasswordIn;
	@FXML
	private Label newPasswordInStatus;
	@FXML
	private PasswordField newPasswordConfirmIn;
	@FXML
	private Label newPasswordConfirmStatus;
	@FXML
	private Label endStatus;

	public void requestNewUserAccountCreation (ActionEvent event) throws Exception {
		ArrayList<User> userList = new ArrayList<>();
		try {
//			User user = null;
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
//			user = (User) in.readObject();
			userList = (ArrayList<User>) in.readObject();
			in.close();
			file.close();
			for (User obj: userList) {
				if (!(obj.getUsername().equals(this.newUsernameIn.getText())) &&
						!(this.newUsernameIn.getText().equals(""))) {
					this.newUsernameInStatus.setText("valid new username");
				} else if (this.newUsernameIn.getText().equals("")) {
					this.newUsernameInStatus.setText("this field cannot be left blank. try again");
					this.endStatus.setText("");
				} else {
					this.newUsernameInStatus.setText("this username is not available. try again");
					this.endStatus.setText("");
				}
				if (!(this.newPasswordIn.getText()).equals("")) {
					this.newPasswordInStatus.setText("valid new password");
				} else {
					this.newPasswordInStatus.setText("this field cannot be left blank. try again");
					this.endStatus.setText("");
				}
				if (!(this.newPasswordConfirmIn.getText().equals("")) &&
						this.newPasswordConfirmIn.getText().equals(this.newPasswordIn.getText())) {
					this.newPasswordConfirmStatus.setText("matches new password");
				} else if (this.newPasswordConfirmIn.getText().equals("")) {
					this.newPasswordConfirmStatus.setText("this field cannot be left blank. try again");
					this.endStatus.setText("");
				} else {
					this.newPasswordConfirmStatus.setText("does not match new password. try again");
					this.endStatus.setText("");
				}
				if (this.newUsernameInStatus.getText().equals("valid new username") &&
						this.newPasswordInStatus.getText().equals("valid new password") &&
						this.newPasswordConfirmStatus.getText().equals("matches new password")) {
					GUI.getBM().createUser(this.newUsernameIn.getText(), this.newPasswordIn.getText());
					this.endStatus.setText("your user account creation request is being processed");
				} else {
					this.endStatus.setText("");
				}
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