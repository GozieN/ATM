package phase2.UserInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import phase2.Operators.BankAccountUser.User;
import phase2.Operators.BankWorker.BankManager;
import phase2.Operators.BankWorker.UserConsultant;
import phase2.FundStores.ATM;
import java.io.Serializable;

public class GUI extends Application implements Serializable {
    private static BankManager BM = new BankManager("BMuser", "BMpass");
    private static UserConsultant UC = new UserConsultant("UCuser", "UCpass");
    private static User U = new User("zzzzz", "zzzzz");
    private static ATM atm = new ATM();

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("ATM");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuScene.fxml"));
		Parent parent = loader.load();
		Scene mainMenuScene = new Scene(parent);
		mainStage.setScene(mainMenuScene);
		mainStage.show();
    }

    public static BankManager getBM() {
        return BM;
    }

    public static UserConsultant getUC() {
        return UC;
    }

    public static User getU() {
        return U;
    }

    public static ATM getAtm() {return atm;}

    public static void updateDate(String date, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write(date);
        fw.close();
    }

    public static void main(String[] args) {
        System.out.println("current directory: " + System.getProperty("user.dir"));
        try {
            File f = new File("phase2/txtfiles/date.txt");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            updateDate(dtf.format(now), f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }
}