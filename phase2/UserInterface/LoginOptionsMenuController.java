package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.UserInterface.Menu;

public class LoginOptionsMenuController extends Menu implements java.io.Serializable {

	public void userLogin(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserLoginMenuScene.fxml"));
		Parent parent = loader.load();
		Scene userLoginMenuScene = new Scene(parent);
		mainStage.setScene(userLoginMenuScene);
		mainStage.show();
	}

	public void consultantLogin(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ConsultantLoginMenuScene.fxml"));
		Parent parent = loader.load();
		Scene consultantLoginMenuScene = new Scene(parent);
		mainStage.setScene(consultantLoginMenuScene);
		mainStage.show();
	}

	public void bankManagerLogin(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("BankManagerLoginMenuScene.fxml"));
		Parent parent = loader.load();
		Scene bankManagerLoginMenuScene = new Scene(parent);
		mainStage.setScene(bankManagerLoginMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "MainMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}