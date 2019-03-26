package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class RequestNewBankAccountCreationMenuController extends Menu implements java.io.Serializable {
	private User user;

	@FXML
	private ComboBox<String> bankAccountTypes;
	@FXML
	private Label bankAccountTypesStatus;
	@FXML
	private Label endStatus;

	public void initialize(User user) {
		this.user = user;
		this.bankAccountTypes.getItems().addAll("chequing", "credit", "debit", "savings");
	}

	public void requestNewBankAccountCreation(ActionEvent event) throws Exception {
		if (!(this.bankAccountTypes.getSelectionModel().isEmpty())) {
			if ((this.bankAccountTypes.getValue()).equals("chequing")) {
				GUI.getBM().createNewAccount(0, "chequing", this.user);
				this.bankAccountTypesStatus.setText("");
				this.endStatus.setText("you have requested a new chequing bank account");
			} else if ((this.bankAccountTypes.getValue()).equals("credit")) {
				GUI.getBM().createNewAccount(0, "credit", this.user);
				this.bankAccountTypesStatus.setText("");
				this.endStatus.setText("you have requested a new credit bank account");
			} else if ((this.bankAccountTypes.getValue()).equals("debit")) {
				GUI.getBM().createNewAccount(0, "debit", this.user);
				this.bankAccountTypesStatus.setText("");
				this.endStatus.setText("you have requested a new debit bank account");
			} else if ((this.bankAccountTypes.getValue()).equals("savings")) {
				GUI.getBM().createNewAccount(0, "savings", this.user);
				this.bankAccountTypesStatus.setText("");
				this.endStatus.setText("you have requested a new savings bank account");
			}
		} else {
			this.bankAccountTypesStatus.setText("no bank account type selected. try again");
			this.endStatus.setText("");
		}
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserInteractionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene userInteractionsMenuScene = new Scene(parent);
		UserInteractionsMenuController controller = loader.getController();
		controller.initialize(this.user);
		mainStage.setScene(userInteractionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}