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

public class UserLoginMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField usernameIn;
	@FXML
	private PasswordField passwordIn;
	@FXML
	private Label loginFailed;

	public void login(ActionEvent event) throws Exception {
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
		if (user.getUsername().equals(this.usernameIn.getText()) &&
				user.getPassword().equals(this.passwordIn.getText())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UserInteractionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene userInteractionsMenuScene = new Scene(parent);
			UserInteractionsMenuController controller = loader.getController();
			controller.initialize(user);
			mainStage.setScene(userInteractionsMenuScene);
			mainStage.show();
		} else {
			this.loginFailed.setText("invalid credentials. try again");
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "LoginOptionsMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}