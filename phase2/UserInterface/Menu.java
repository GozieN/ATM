package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;

public abstract class Menu {

	public void back(ActionEvent event, String previousMenu) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource(previousMenu));
		Scene previousScene = new Scene(parent);
		mainStage.setScene(previousScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
		Scene mainMenuScene = new Scene(parent);
		mainStage.setScene(mainMenuScene);
		mainStage.show();
	}
}