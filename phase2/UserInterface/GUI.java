package phase2.UserInterface;

import java.io.*;
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
import phase2.otherfiles.DateTimeManager;
import java.util.*;

public class GUI extends Application implements Serializable {
    private static ATM atm = new ATM();
    private static BankManager BM;
    private static UserConsultant UC = new UserConsultant("UCuser", "UCpass");
    private static User U = new User("zzzzz", "zzzzz");
    public static boolean running = true;

    @Override
    public void start(Stage mainStage) throws Exception {
        begin();
        BM.setAtm(atm);
        mainStage.setTitle("ATM");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuScene.fxml"));
		Parent parent = loader.load();
		Scene mainMenuScene = new Scene(parent);
		mainStage.setScene(mainMenuScene);
        mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::end);
        mainStage.show();
    }

    private void begin() {
        try {
           FileInputStream file = new FileInputStream("phase2/txtfiles/BankManager.txt");
           ObjectInputStream in = new ObjectInputStream(file);
           BM = (BankManager) in.readObject();
           if (BM == null) {
               BM = new BankManager("BMuser", "BMpass");
           }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    private void end(WindowEvent event) {
        try {
            FileOutputStream file = new FileOutputStream("phase2/txtfiles/BankManager.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(getBM());
            out.close();
            file.close();
        } catch (Exception ex) {ex.printStackTrace();}
    }

    public static void setBM(BankManager bm) {
        BM = bm;
    }

    public static void  setUC(UserConsultant uc) {
        UC = uc;
    }

    public static void setU(User u) {
         U = u;
    }

    public static void setAtm(ATM atm_) {
        atm = atm_;
    }

    public static BankManager getBM() {
        return BM;
    }

    public  static UserConsultant getUC() {
        return UC;
    }

    public  static User getU() {
        return U;
    }

    public static ATM getAtm() {
        return atm;
    }

    public static void updateDate(String date, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write(date);
        fw.close();
    }

    public static void main(String[] args) {

        DateTimeManager dtm = new DateTimeManager();
        dtm.newDay();
        dtm.startDateTime();


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