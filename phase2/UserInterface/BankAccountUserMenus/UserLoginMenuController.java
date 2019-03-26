package phase2.UserInterface.BankAccountUserMenus;

import java.io.*;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.BankAccountUserMenus.UserToAccountInteractionMenus.UserInteractionsMenuController;
import phase2.UserInterface.MainMenu.Menu;

import java.util.*;

public class UserLoginMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField usernameIn;
	@FXML
	private PasswordField passwordIn;
	@FXML
	private Label loginFailed;

	public void login(ActionEvent event) throws Exception {
//		User user = null;
		ArrayList<User> userList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
//			user = (User)in.readObject();
			userList = (ArrayList<User>) in.readObject();
			in.close();
			file.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
			for (User obj: userList) {
				if (obj.getUsername().equals(this.usernameIn.getText()) &&
						obj.getPassword().equals(this.passwordIn.getText())) {
					Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("UserInteractionsMenuScene.fxml"));
					Parent parent = loader.load();
					Scene userInteractionsMenuScene = new Scene(parent);
					UserInteractionsMenuController controller = loader.getController();
					controller.initialize(obj);
					mainStage.setScene(userInteractionsMenuScene);
					mainStage.show();
				} else {
					this.loginFailed.setText("invalid credentials. try again");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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