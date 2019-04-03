package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import javafx.stage.Stage;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.GUI;
import phase2.UserInterface.Menu;

import java.util.*;

public class RequestNewUserAccountCreationMenuController extends Menu implements java.io.Serializable, Cloneable {
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


	@SuppressWarnings("unchecked")
	public void requestNewUserAccountCreation (ActionEvent event) throws Exception {
		for (User user : GUI.getBM().getUsers()) {
			if (!(user.getUsername().equals(this.newUsernameIn.getText())) &&
					!(this.newUsernameIn.getText().equals(""))) {
				this.newUsernameInStatus.setText("valid new username");
			} else if (this.newUsernameIn.getText().equals("")) {
				this.newUsernameInStatus.setText("this field cannot be left blank. try again");
			} else {
				this.newUsernameInStatus.setText("this username is not available. try again");
			}
			if (!(this.newPasswordIn.getText()).equals("")) {
				this.newPasswordInStatus.setText("valid new password");
			} else {
				this.newPasswordInStatus.setText("this field cannot be left blank. try again");
			}
			if (!(this.newPasswordConfirmIn.getText().equals("")) &&
					this.newPasswordConfirmIn.getText().equals(this.newPasswordIn.getText())) {
				this.newPasswordConfirmStatus.setText("matches new password");
			} else if (this.newPasswordConfirmIn.getText().equals("")) {
				this.newPasswordConfirmStatus.setText("this field cannot be left blank. try again");
			} else {
				this.newPasswordConfirmStatus.setText("does not match new password. try again");
			}
			if (this.newUsernameInStatus.getText().equals("valid new username") &&
					this.newPasswordInStatus.getText().equals("valid new password") &&
					this.newPasswordConfirmStatus.getText().equals("matches new password")) {
				Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("UserContractMenuScene.fxml"));
				Parent parent = loader.load();
				Scene userContractMenuScene = new Scene(parent);
				UserContractMenuController controller = loader.getController();
				controller.initialize(this.newUsernameIn.getText(), this.newPasswordIn.getText());
				mainStage.setScene(userContractMenuScene);
				mainStage.show();
			}
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "NewUserMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}