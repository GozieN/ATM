package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.event.*;
import phase2.Operators.BankAccountUser.PointSystemUser;
import phase2.Operators.BankAccountUser.User;

import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;

public class UserInteractionsMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private Label noAccounts1;
	@FXML
	private Label noAccounts2;
	@FXML
	private Label optInStatus;
	@FXML
	private Label optOutStatus;

	public void initialize(User user) {
		this.user = user;
	}

	public void viewAccountsSummary(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AccountsSummaryOptionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene accountsSummaryOptionsMenuScene = new Scene(parent);
			AccountsSummaryOptionsMenuController controller = loader.getController();
			controller.initialize(this.user, "User");
			mainStage.setScene(accountsSummaryOptionsMenuScene);
			mainStage.show();
		} else {
			this.noAccounts1.setText("you have no accounts");
		}
	}

	public void performTransaction(ActionEvent event) throws Exception {
		if (!(this.user.getAccountsCreated().isEmpty())) {
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("UserTransactionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene userTransactionsMenuScene = new Scene(parent);
			UserTransactionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(userTransactionsMenuScene);
			mainStage.show();
		} else {
			this.noAccounts2.setText("you have no accounts");
		}
	}

	public void requestNewBankAccountCreation(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("RequestNewBankAccountCreationMenuScene.fxml"));
		Parent parent = loader.load();
		Scene requestNewBankAccountCreationMenuScene = new Scene(parent);
		RequestNewBankAccountCreationMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(requestNewBankAccountCreationMenuScene);
		mainStage.show();
	}

	public void changeUserAccountPassword(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ChangeUserAccountPasswordMenuScene.fxml"));
		Parent parent = loader.load();
		Scene changeUserAccountPasswordMenuScene = new Scene(parent);
		ChangeUserAccountPasswordMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(changeUserAccountPasswordMenuScene);
		mainStage.show();
	}

	public void optIn(ActionEvent event) throws Exception {
		if (!(this.user instanceof PointSystemUser)) {
			this.user.optIntoPointSystem();
			this.optInStatus.setText("you are now a member of the point system");
			this.optOutStatus.setText("");
		} else {
			this.optInStatus.setText("you are currently already a member of the point system");
			this.optOutStatus.setText("");
		}
	}

	public void optOut(ActionEvent event) throws Exception {
		if (this.user instanceof PointSystemUser) {
			((PointSystemUser)this.user).optOutOfPointSystem();
			this.optOutStatus.setText("you are not a member of the point system anymore");
			this.optInStatus.setText("");
		} else {
			this.optOutStatus.setText("you are currently not a member of the point system");
			this.optInStatus.setText("");
		}
	}

	public void viewSignedContract(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewSignedContractMenuScene.fxml"));
		Parent parent = loader.load();
		Scene viewSignedContractMenuScene = new Scene(parent);
		ViewSignedContractMenuController controller = loader.getController();
		controller.initialize(this.user, "User");
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
		controller.initialize(this.user,"User");
		mainStage.setScene(viewCapabilitiesMenuScene);
		mainStage.show();
	}

	public void back(ActionEvent event) throws Exception {
		if (this.user.getUsername().equals("UCuser")) {
			Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ConsultantUseOptionsMenuScene.fxml"));
			Parent parent = loader.load();
			Scene consultantUseOptionsMenuScene = new Scene(parent);
			ConsultantUseOptionsMenuController controller = loader.getController();
			controller.initialize(this.user);
			mainStage.setScene(consultantUseOptionsMenuScene);
			mainStage.show();
		} else {
			String previousMenu = "UserLoginMenuScene.fxml";
			super.back(event, previousMenu);
		}
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}