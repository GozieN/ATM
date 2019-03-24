package phase2.UserInterface;

import javafx.scene.control.ComboBox;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.*;

public class ChangeUserAccountPasswordMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private PasswordField currentPasswordIn;
	@FXML
	private Label currentPasswordInStatus;
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

	public void initialize(User user) {
		this.user = user;
	}

	public void changePassword(ActionEvent event) throws Exception {
		if (this.currentPasswordIn.getText().equals(this.user.getPassword())) {
			this.currentPasswordInStatus.setText("matches current password");
		} else {
			this.currentPasswordInStatus.setText("does not match current password. try again");
		}
		if (!(this.newPasswordIn.getText()).equals("")) {
			this.newPasswordInStatus.setText("valid new password");
		} else {
			this.newPasswordInStatus.setText("this field cannot be left blank. try again");
		}
		if (!(this.newPasswordConfirmIn.getText().equals("")) &&
				this.newPasswordConfirmIn.getText().equals(this.newPasswordIn.getText())) {
			this.newPasswordConfirmStatus.setText("matches new password");
		} else {
			this.newPasswordConfirmStatus.setText("does not match new password. try again");
		}
		if (this.currentPasswordInStatus.getText().equals("matches current password") &&
		this.newPasswordInStatus.getText().equals("valid new password") &&
		this.newPasswordConfirmStatus.getText().equals("matches new password")) {
			this.user.changePassword(this.currentPasswordIn.getText(), this.newPasswordIn.getText());
			this.endStatus.setText("your password has been changed");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserInteractionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene userInteractionsMenuScene = new Scene(parent);
		UserInteractionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(userInteractionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}