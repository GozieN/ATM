package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.BankManagerUserInteractionsMenuController;
import phase2.UserInterface.GUI;
import phase2.UserInterface.Menu;

public class NewUserMenuController extends Menu implements java.io.Serializable {

	public void consultation(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ConsultationMenuScene.fxml"));
		Parent parent = loader.load();
		Scene consultationMenuScene = new Scene(parent);
		mainStage.setScene(consultationMenuScene);
		mainStage.show();
	}

	public void skipConsultation(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("RequestNewUserAccountCreationMenuScene.fxml"));
		Parent parent = loader.load();
		Scene requestNewUserAccountCreationMenuScene = new Scene(parent);
		mainStage.setScene(requestNewUserAccountCreationMenuScene);
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