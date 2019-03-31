package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class MessageBankManagerMenuController extends Menu implements java.io.Serializable {
	@FXML
	private TextField messageIn;
	@FXML
	Label messageInStatus;

	public void sendMessage(ActionEvent event) throws Exception {

		if (!(this.messageIn.getText().isEmpty())) {
			GUI.getUC().contactBM(this.messageIn.getText(), GUI.getBM());
		} else {
			this.messageInStatus.setText("this field cannot be empty. try again");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ConsultantWorkInteractionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene consultantWorkInteractionsMenuScene = new Scene(parent);
		mainStage.setScene(consultantWorkInteractionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}