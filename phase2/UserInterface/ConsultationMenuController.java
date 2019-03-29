package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import javafx.stage.Stage;
import phase2.Operators.BankAccountUser.User;
import phase2.UserInterface.GUI;
import phase2.UserInterface.Menu;

import java.util.*;

public class ConsultationMenuController extends Menu implements java.io.Serializable {

	public void requestNewUserAccountCreation (ActionEvent event) throws Exception {

	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "NewUserMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}