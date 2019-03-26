package phase2.Operators.BankWorker;

import phase2.FundStores.Account;
import phase2.Operators.Contract;

import java.util.ArrayList;


public class UserConsultant extends BankEmployee implements Contract {
    private ArrayList<BankEmployee> bankEmployeeDatabase = new ArrayList<>();
    private int numBankWorkers = 0;
    private ArrayList<Account> AccountsCreated = new ArrayList<Account>();
    private String username;
    private String password;
    private String currentUserBeingConsulted;
    private String lastMessagetoBM;
    private BankManager BM;

    public UserConsultant(String username, String password){
        super(username, password);
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewCapabilities(){
        String s = "";
        s = "As a User Consultant for the Bank, you are able to do the following: \n" +
                "- Advise users on account creation.\n" +
                "- Message Bank Manager about complaints.\n" +
                "- Change your password at any time. \n" +
                "- Create a new account at any time. \n" +
                "- View a summary of a single account. \n" +
                "- View a summary of all your existing accounts";

        return s;
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewContract(){
        String s;
        s = "As a User Consultant for the Bank, " +
                "you agree not to engage in fraudulent behavior, " +
                "and agree not to abuse the Bank Manager messaging system. Click next to agree.";
        return s;
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
     * Set Bank Manager
     */
    public void setBM(BankManager BM) {
        this.BM = BM;
    }

    /**
     * Return The kind of user to create.
     * @return The kind of user to create.
     */
    public String getCurrentUserBeingConsulted() {
        return currentUserBeingConsulted;
    }

    /**
     * Contact the Bank Manager!
     *
     */
    public void contactBM(String message){
        lastMessagetoBM = message;
        setChanged();
        notifyObservers();
        BM.update(this, lastMessagetoBM);
    }
}