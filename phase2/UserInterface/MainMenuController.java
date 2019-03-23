package phase2.UserInterface;

import phase2.FundHolders.*;
import phase2.Operators.*;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.*;

public class MainMenuController extends Menu implements java.io.Serializable {

	public void login(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("LoginOptionsScene.fxml"));
        Scene loginOptionsScene = new Scene(parent);
		mainStage.setScene(loginOptionsScene);
		mainStage.show();
    }

    public void requestNewUserCreation(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource("RequestNewUserCreationScene.fxml"));
		Scene requestNewUserCreationScene = new Scene(parent);
		mainStage.setScene(requestNewUserCreationScene);
		mainStage.show();
	}
}