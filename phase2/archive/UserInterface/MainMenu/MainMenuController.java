package phase2.UserInterface.MainMenu;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.UserInterface.*;

public class MainMenuController extends Menu implements java.io.Serializable {

	public void viewDateTime(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DateTimeMenuScene.fxml"));
		Parent parent = loader.load();
		Scene dateTimeMenuScene = new Scene(parent);
		DateTimeMenuController controller = loader.getController();
		controller.initialize();
		mainStage.setScene(dateTimeMenuScene);
		mainStage.show();
	}

	public void login(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("LoginOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene loginOptionsMenuScene = new Scene(parent);
		mainStage.setScene(loginOptionsMenuScene);
		mainStage.show();
	}

	public void requestNewUserAccountCreation(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("RequestNewUserAccountCreationMenuScene.fxml"));
		Parent parent = loader.load();
		Scene requestNewUserAccountCreationMenuScene = new Scene(parent);
		mainStage.setScene(requestNewUserAccountCreationMenuScene);
		mainStage.show();
	}
}