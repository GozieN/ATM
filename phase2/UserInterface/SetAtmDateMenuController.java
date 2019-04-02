package phase2.UserInterface;

import java.io.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;
import java.util.*;

public class SetAtmDateMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField dayIn;
	@FXML
	private Label dayInStatus;
	@FXML
	private TextField monthIn;
	@FXML
	private Label monthInStatus;
	@FXML
	private TextField yearIn;
	@FXML
	private Label yearInStatus;
	@FXML
	private Label endStatus;

	public void setDate(ActionEvent event) throws Exception {
		int day = 0;
		int month = 0;
		int year = 0;
		if (!(dayIn.getText().isEmpty())) {
			day = Integer.parseInt(this.dayIn.getText());
			this.dayInStatus.setText("");
		} else {
			day = 0;
			this.dayInStatus.setText("this field cannot be empty. try again");
			this.endStatus.setText("");
		}
		if (!(monthIn.getText().isEmpty())) {
			month = Integer.parseInt(this.monthIn.getText());
			this.monthInStatus.setText("");
		} else {
			month = 0;
			this.monthInStatus.setText("this field cannot be empty. try again");
			this.endStatus.setText("");
		}
		if (!(yearIn.getText().isEmpty())) {
			year = Integer.parseInt(this.yearIn.getText());
			this.yearInStatus.setText("");
		} else {
			year = 0;
			this.yearInStatus.setText("this field cannot be empty. try again");
			this.endStatus.setText("");
		}
		if (!(day == 0) && !(month == 0) && !(year == 0)) {
			try {
				GUI.getBM().ATMSetDate(GUI.getAtm(), day, month, year);
				this.endStatus.setText("the date has been set");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.endStatus.setText("");
		}
	}

	public void back(ActionEvent event) throws Exception {
		String previousMenu = "BankManagerInteractionsMenuController.fxml";
		super.back(event, previousMenu);
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}