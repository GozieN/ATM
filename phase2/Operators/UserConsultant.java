package phase2.Operators;

import phase2.FundHolders.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class UserConsultant extends BankWorker{
    private ArrayList<BankWorker> bankWorkerDatabase = new ArrayList<>();
    private int numBankWorkers = 0;
    private ArrayList<Account> AccountsCreated = new ArrayList<Account>();
    private String username;
    private String password;
    private String currentUserBeingConsulted;
    private String lastMessagetoBM;

    public UserConsultant(String username, String password){
        super(username, password);
    }

    // input prompts, --> create account button
    /**
     * Advise to the user base on their information.
     * @param age
     * @return
     */
    public String reportUserAccountAdvice(int age, boolean joinPointSystem){
        String s = "";
            if (age < 16) {
                s = "Sorry, you must be at least 16 years of age to create an account" +
                        " with us! Come back in " + (16 - age) + " years!";
            }
            else if (age >= 16 && 25 >= age) {
                currentUserBeingConsulted = "Student";
                s = "Based on your information, it looks like a Student account is the " +
                        "right fit for you! We are transferring you over to the account creation page!";}
            else if(age > 25 && 60 < age){
                currentUserBeingConsulted = "Standard";
                s = "Based on your information, it looks like our standard account is the " +
                        "right fit for you! We are transferring you over to the account creation page!";}
            else if(age > 60){
                currentUserBeingConsulted = "Retired/Pension";
                s = "Based on your information, it looks like a pension/retirement account is the " +
                        "right fit for you! We are transferring you over to the account creation page!";}
        return s;
    }

    /**
     * Return The kind of user to create.
     * @return The kind of user to create.
     */
    public String getCurrentUserBeingConsulted() {
        return currentUserBeingConsulted;
    }

//    /**
//     * Contact the Bank Manager!
//     *
//     */
//    public contactBM(String message){
//        lastMessagetoBM = message;
//        se
//    }
}