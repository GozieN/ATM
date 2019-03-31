package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class DeleteUserAccountMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	TextField masterAccessKeyIn;
	@FXML
	Label masterAccessKeyInStatus;

	@SuppressWarnings("unchecked")
	public void initialize(User user) {
		ArrayList<User> userList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			userList = (ArrayList<User>) in.readObject();
			in.close();
			file.close();
			for (User obj: userList) {
				if (obj.getUsername().equals(user.getUsername())) {
					this.user = obj;
					break;
				}
			}
		} catch (Exception ex) {ex.printStackTrace();}

		this.user = user;
	}

	public void deleteUserAccount(ActionEvent event) throws Exception {
		ArrayList<User> userList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			userList = (ArrayList<User>) in.readObject();
			in.close();
			file.close();
			for (User obj: userList) {
				if (this.masterAccessKeyIn.getText().equals(GUI.getBM().getMasterAccessKey())
						&& obj.getUsername().equals(this.user.getUsername())) {
					GUI.getBM().deleteUser(obj);
					exit(event);
				} else {
					this.masterAccessKeyInStatus.setText("incorrect master access key. try again");
				}
			}
		} catch (Exception ex) {ex.printStackTrace();}

	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("BankManagerUserInteractionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene bankManagerUserInteractionsMenuScene = new Scene(parent);
		BankManagerUserInteractionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(bankManagerUserInteractionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}