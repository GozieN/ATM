package phase2.UserInterface;

import java.awt.event.WindowAdapter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import phase2.Operators.BankAccountUser.RetiredPointSystemUser;
import phase2.Operators.BankAccountUser.StudentPointSystemUser;
import phase2.Operators.BankAccountUser.User;
import phase2.Operators.BankWorker.BankManager;
import phase2.Operators.BankWorker.UserConsultant;
import phase2.FundStores.ATM;
import phase2.otherfiles.DateTimeManager;
import java.util.*;

public class GUI extends Application implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static ATM atm;
    protected static UserConsultant UC;
    protected static BankManager BM;
    protected static User U = new User("", "");
    protected static StudentPointSystemUser SU = new StudentPointSystemUser("su", "");
    protected static RetiredPointSystemUser RU = new RetiredPointSystemUser("ru", "");


    // public boolean running = true;

    public GUI(){

    }

    @Override
    public void init() throws Exception {
        super.init();
            File fCheck = new File("phase2/txtfiles/BankManager.txt");
            if (fCheck.length() == 0){
                System.out.println("file len is 0");
                BM = new BankManager("BMuser", "BMpass");
                BM.setAtm(new ATM());
                UC = new UserConsultant("UCuser", "UCpass");
                atm =  BM.getAtm();
//                atm.setDate(04,03,2019);
                BM.setUC(UC);
                UC.setBM(BM);
                UC = BM.getUC();
                System.out.println("end of len 0");
                System.out.println(BM);
            }else{
                System.out.println("reading from file:" + BM);
                System.out.println("reading from file:" + BM.getUsername());
                System.out.println("read:" + BM);
                BM = (BankManager) deSerialization("phase2/txtfiles/BankManager.txt");
                System.out.println("read post deserialization:" + BM);
                System.out.println("read post deserialization:" + BM.getUsers());
                System.out.println("read:" + BM.getUsername());
            }}


    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("ATM");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuScene.fxml"));
		Parent parent = loader.load();
		Scene mainMenuScene = new Scene(parent);
		mainStage.setScene(mainMenuScene);
        mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::end);
        mainStage.show();
    }

    /**
     * detecting right before the program stops running in order to write to file!
     * @param event
     */
    private void end(WindowEvent event){
        System.out.println("in end: " + BM);
        System.out.println("in end: " + BM.getUsers());

        try{
            serialization("phase2/txtfiles/BankManager.txt", BM);
            System.out.println("in end2: " + BM);
            System.out.println("in end2: " + BM.getUsers());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used to read data from file for deSerialization.
     *
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * SOURCE: https://www.codota.com/code/java/classes/java.io.ObjectInputStream
     */
    public static Object deSerialization(String file) throws IOException, ClassNotFoundException {
        try{
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return object;
        }
        catch (Exception e){
            e.printStackTrace();
        } return null;
    }


    /**
     * This method is used to write data to file for Serialization.
     *
     * @param file
     * @param object
     * @throws IOException
     *
     * SOURCE: https://www.codota.com/code/java/classes/java.io.ObjectInputStream
     */
    public static void serialization(String file, Object object) throws IOException {
        try{
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * the Bank Manager
     * @return BM the manager of the bank
     */
    public BankManager getBM() {
        return BM;
    }

    public static StudentPointSystemUser getSU() {
        return SU;
    }

    public static RetiredPointSystemUser getRU() {
        return RU;
    }

    /**
     * return the UserConsultant instance
     * @return Consultant
     */
    public  UserConsultant getUC() {
        return UC;
    }

    /**
     * get the user
     * @return u, the user
     */
    public  User getU() {
        return U;
    }

    /**
     * return the atm
     * @return atm, the atm
     */
    public ATM getAtm() {
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
        launch(args);
}}