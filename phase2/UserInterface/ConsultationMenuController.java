package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
	@FXML
	private Label consultantMessage;
	@FXML
	private TextField ageIn;
	@FXML
	private Label ageInStatus;
	@FXML
	private ComboBox<String> studentYesNo;
	@FXML
	private Label studentYesNoStatus;
	@FXML
	private Label reportInfo;

	public void initialize() {
		this.consultantMessage.setText(GUI.getUC().consultantMessage());
		this.studentYesNo.getItems().addAll("yes", "no");
	}

	public void requestNewUserAccountCreation (ActionEvent event) throws Exception {
		int age;
		if (!(ageIn.getText().isEmpty())) {
			this.ageInStatus.setText("");
			age = Integer.parseInt(this.ageIn.getText());
		} else {
			this.ageIn.setText("this field cannot be empty. try again");
		}
		boolean studentYesNo = false; // temporary holder value
		if (!(this.studentYesNo.getSelectionModel().isEmpty())) {
			this.studentYesNoStatus.setText("");
			if (this.studentYesNo.getValue().equals("yes")) {
				studentYesNo = true;
			} else if (this.studentYesNo.getValue().equals("no")) {
				studentYesNo = false;
			}
		} else {
			this.studentYesNoStatus.setText("no option selected. try again");
		}
		if (this.ageInStatus.getText().equals("") &&
				this.studentYesNoStatus.getText().equals("")) {
			if (Integer.parseInt(this.ageIn.getText()) >= 16) {
				this.reportInfo.setText(GUI.getUC().reportUserAccountAdvice((Integer.parseInt(this.ageIn.getText())), studentYesNo));
				Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("RequestNewUserAccountCreationMenuScene.fxml"));
				Parent parent = loader.load();
				Scene requestNewUserAccountCreationMenuScene = new Scene(parent);
				mainStage.setScene(requestNewUserAccountCreationMenuScene);
				mainStage.show();
			} else {
				this.reportInfo.setText(GUI.getUC().reportUserAccountAdvice(Integer.parseInt((this.ageIn.getText())), studentYesNo));
			}
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "NewUserMenuScene.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}