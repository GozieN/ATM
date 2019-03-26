package phase2.UserInterface;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.event.*;
import phase2.Operators.BankAccountUser.User;

public class AllAccountsSummaryMenuController extends Menu implements java.io.Serializable {
	private User user;
	private String operatorType;

	@FXML
	private TextArea allAccountsSummary;

	public void initialize(User user, String operatorType) {
		this.user = user;
		this.operatorType = operatorType;
		this.allAccountsSummary.setText(this.user.viewInfo());
	}

	public void back(ActionEvent event) throws Exception {
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AccountsSummaryOptionsMenuScene.fxml"));
		Parent parent = loader.load();
		Scene accountsSummaryOptionsMenuScene = new Scene(parent);
		AccountsSummaryOptionsMenuController controller = loader.getController();
		controller.initialize(this.user, this.operatorType);
		mainStage.setScene(accountsSummaryOptionsMenuScene);
		mainStage.show();
	}

	public void exit(ActionEvent event) throws Exception {
		super.exit(event);
	}
}