package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import javafx.stage.Stage;
import phase2.UserInterface.Menu;

public class ConsultantLoginMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField usernameIn;
	@FXML
	private Label usernameInStatus;
	@FXML
	private PasswordField passwordIn;
	@FXML
	private Label passwordInStatus;
	@FXML
	private Label loginFailed;

	public void login(ActionEvent event) throws Exception {
		if (this.usernameIn.getText().isEmpty()) {
			this.usernameInStatus.setText("this field cannot be empty. try again");
			this.loginFailed.setText("login failed. try again");
		} else {
			this.usernameInStatus.setText("");
		}
		if (this.passwordIn.getText().isEmpty()) {
			this.passwordInStatus.setText("this field cannot be empty. try again");
			this.loginFailed.setText("login failed. try again");
		} else {
			this.passwordInStatus.setText("");
		}
		if (!(this.usernameIn.getText().isEmpty()) &&
				!(this.passwordIn.getText().isEmpty())) {
			if (this.usernameIn.getText().equals(GUI.getUC().getUsername()) &&
					this.passwordIn.getText().equals(GUI.getUC().getPassword())) {
				Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ConsultantUseOptionsMenuScene.fxml"));
				Parent parent = loader.load();
				Scene consultantUseOptionsMenuScene = new Scene(parent);
				mainStage.setScene(consultantUseOptionsMenuScene);
				mainStage.show();
			} else {
				this.loginFailed.setText("invalid credentials. try again");
			}
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