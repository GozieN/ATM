package phase2.UserInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private  static ATM atm = new ATM();
    private  static BankManager BM = new BankManager("BMuser", "BMpass");
    private  static UserConsultant UC = new UserConsultant("UCuser", "UCpass");
    private  static User U = new User("zzzzz", "zzzzz");
    public static boolean running = true;

    @Override
    public void start(Stage mainStage) throws Exception {
        BM.setAtm(atm);
        mainStage.setTitle("ATM");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuScene.fxml"));
		Parent parent = loader.load();
		Scene mainMenuScene = new Scene(parent);
		mainStage.setScene(mainMenuScene);
		mainStage.show();
    }

    public static void setBM(BankManager bm) { BM = bm;
    }

    public static void  setUC(UserConsultant uc) {
        UC = uc;
    }

    public static void setU(User u) {
         U = u;
    }

    public static void setAtm(ATM atm_) {atm = atm_;}

    public static BankManager getBM() {
        return BM;
    }

    public  static UserConsultant getUC() {
        return UC;
    }

    public  static User getU() {
        return U;
    }

    public static ATM getAtm() {return atm;}

    public static void updateDate(String date, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write(date);
        fw.close();
    }

    public static void main(String[] args) {
        //FROM A2
//        // The window of the main menu.
//        MainMenuController mmc = new MainMenuController();
//        //mmc.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                running = false;
//            }
//        });

        //set date to 00:00AM
        GUI gui = new GUI();
        //read from file but could be empty - if it's empty, set to null!

        if (gui == null){
            BankManager bm = new BankManager("", "");
//            User user = new User("", "");
            UserConsultant UC = new UserConsultant("UCuser", "UCpass");
            UC.setBM(bm);
//            bm.createUser(user.getUsername(), user.getPassword());
//            bm.createUser(UC.getUsername(), UC.getPassword());
            gui = new GUI();
            gui.setBM(bm);
            gui.setAtm(gui.getBM().getAtm());
            gui.setU(gui.getBM().getCurrentUserInteractingWithSystem()); //FROM MENU CONTROLLER, SET IT
            // to the user who logged in
            gui.setUC(UC);

// while (!running){
            // write BM to file when running has halted, ! }

        }else{
            BankManager bm = new BankManager("", "");
//            User user = new User("", "");
            UserConsultant UC = new UserConsultant("UCuser", "UCpass");
            UC.setBM(bm);
            BankManager existingBM = bm;
            //existingBM = read from file
            gui.setU(existingBM.getCurrentUserInteractingWithSystem());
            gui.setAtm(existingBM.getAtm());
            gui.setAtm(atm);
        }

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