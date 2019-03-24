package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;

public abstract class Menu {

	public void back(ActionEvent event, String previousMenu) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(previousMenu));
		Parent parent = loader.load();
		Scene previousMenuScene = new Scene(parent);
		mainStage.setScene(previousMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuScene.fxml"));
		Parent parent = loader.load();
		Scene mainMenuScene = new Scene(parent);
		mainStage.setScene(mainMenuScene);
		mainStage.show();
	}
}