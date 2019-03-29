package phase2.UserInterface;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class ViewConsultationHistoryMenuController extends Menu implements java.io.Serializable {
	@FXML
	Label consultationHistory;

	public void initialize() {
		// angela TODO: assign consultation history from file to this variable
	}

	public void sendToBankManager(ActionEvent event) throws Exception {
		// angela TODO: get UC from file, then UCinstance.sendMethod
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