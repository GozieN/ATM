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

public class LoginOptionsController extends Menu implements java.io.Serializable {

	public void userLogin(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource("UserLoginScene.fxml"));
		Scene userLoginScene = new Scene(parent);
		mainStage.setScene(userLoginScene);
		mainStage.show();
	}

	public void consultantLogin(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource("ConsultantLoginScene.fxml"));
		Scene consultantLoginScene = new Scene(parent);
		mainStage.setScene(consultantLoginScene);
		mainStage.show();
	}

	public void bankManagerLogin(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource("BankManagerLoginScene.fxml"));
		Scene bankManagerLoginScene = new Scene(parent);
		mainStage.setScene(bankManagerLoginScene);
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