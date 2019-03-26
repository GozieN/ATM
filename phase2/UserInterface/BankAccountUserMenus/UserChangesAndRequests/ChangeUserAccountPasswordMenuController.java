package phase2.UserInterface.BankAccountUserMenus.UserChangesAndRequests;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.BankAccountUserMenus.UserToAccountInteractionMenus.UserInteractionsMenuController;
import phase2.UserInterface.MainMenu.Menu;

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
	private Label newPasswordConfirmInStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		this.user = user;
	}

	public void changePassword(ActionEvent event) throws Exception {
		if (this.currentPasswordIn.getText().equals(this.user.getPassword())) {
			this.currentPasswordInStatus.setText("matches current password");
		} else if (this.currentPasswordIn.getText().equals("")) {
			this.currentPasswordInStatus.setText("this field cannot be left blank. try again");
			this.endStatus.setText("");
		} else {
			this.currentPasswordInStatus.setText("does not match current password. try again");
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
			this.newPasswordConfirmInStatus.setText("matches new password");
		} else if (this.newPasswordConfirmIn.getText().equals("")) {
			this.newPasswordConfirmInStatus.setText("this field cannot be left blank. try again");
			this.endStatus.setText("");
		} else {
			this.newPasswordConfirmInStatus.setText("does not match new password. try again");
			this.endStatus.setText("");
		}
		if (this.currentPasswordInStatus.getText().equals("matches current password") &&
		this.newPasswordInStatus.getText().equals("valid new password") &&
		this.newPasswordConfirmInStatus.getText().equals("matches new password")) {
			this.user.changePassword(this.currentPasswordIn.getText(), this.newPasswordIn.getText());
			this.endStatus.setText("your password has been changed");
		} else {
			this.endStatus.setText("");
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