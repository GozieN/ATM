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

public class UserLoginController extends Menu implements java.io.Serializable {
	@FXML
	private TextField usernameIn;
	@FXML
	private PasswordField passwordIn;
	@FXML
	private Label loginFailed;

	public void login(ActionEvent event) throws Exception {
		try {
			User user = null;
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			user = (User) in.readObject();
			in.close();
			file.close();
			if (user.getUsername().equals(this.loginFailed.getText()) &&
					user.getPassword().equals(this.passwordIn.getText())) {
				// TODO: next scene - pass on user instance
			} else {
				this.loginFailed.setText("invalid credentials. try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "LoginOptionsScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}