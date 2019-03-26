package phase2.UserInterface.BankEmployeeUserMenus.ConsultantMenus;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import phase2.UserInterface.MainMenu.Menu;

public class ConsultantLoginMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField usernameIn;
	@FXML
	private PasswordField passwordIn;
	@FXML
	private Label loginFailed;

	public void login(ActionEvent event) throws Exception {
		// TODO: where to get consultant credentials ?
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "LoginOptionsMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}