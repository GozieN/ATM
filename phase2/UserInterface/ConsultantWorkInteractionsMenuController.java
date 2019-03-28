package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class ConsultantWorkInteractionsMenuController extends Menu implements java.io.Serializable {

	public void viewConsultationHistory(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewConsultationHistoryMenuScene.fxml"));
		Parent parent = loader.load();
		Scene viewConsultationHistoryMenuScene = new Scene(parent);
		ViewConsultationHistoryMenuController controller = loader.getController();
		controller.initialize();
		mainStage.setScene(viewConsultationHistoryMenuScene);
		mainStage.show();
	}

	public void messageBankManager(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MessageBankManagerMenuScene.fxml"));
		Parent parent = loader.load();
		Scene messageBankManagerMenuScene = new Scene(parent);
		mainStage.setScene(messageBankManagerMenuScene);
		mainStage.show();
	}

	public void viewSignedContract(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewSignedContractMenuScene.fxml"));
		Parent parent = loader.load();
		Scene viewSignedContractMenuScene = new Scene(parent);
		ViewSignedContractMenuController controller = loader.getController();
		controller.initialize("Consultant");
		mainStage.setScene(viewSignedContractMenuScene);
		mainStage.show();
	}

	public void viewCapabilities(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewCapabilitiesMenuScene.fxml"));
		Parent parent = loader.load();
		Scene viewCapabilitiesMenuScene = new Scene(parent);
		ViewCapabilitiesMenuController controller = loader.getController();
		controller.initialize("Consultant");
		mainStage.setScene(viewCapabilitiesMenuScene);
		mainStage.show();
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