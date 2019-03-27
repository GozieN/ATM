package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class ConsultantWorkInteractionsMenuController extends Menu implements java.io.Serializable {

	public void reportConsultationHistory(ActionEvent event) throws Exception {

	}

	public void messageBankManager(ActionEvent event) throws Exception {

	}

	public void viewSignedContract(ActionEvent event) throws Exception {

	}

	public void viewCapabilities(ActionEvent event) throws Exception {

	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ConsultantUseOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene consultantUseOptionsMenuScene = new Scene(parent);
		mainStage.setScene(consultantUseOptionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}