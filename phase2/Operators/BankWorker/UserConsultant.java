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
    static int numUsersConsulted = 1;
    private String currentUserBeingConsultedType;
    private ArrayList<String> UserAdviseHistory = new ArrayList<>();

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

    /**
     * Add to the record of the account recommendations
     * @param record
     */
    public void addToUserAdviseHistory(String record){
        UserAdviseHistory.add(record);

    }

    // input prompts, --> create account button
    /**
     * Advise to the user base on their information.
     * @param age
     * @return A string detailing the outcome of the account creation based on user input.
     */
    public String reportUserAccountAdvice(int age, boolean inSchool){
        numUsersConsulted++;
        String s = "";
        String record = "";

        if (age < 16) {
                s = "Sorry, you must be at least 16 years of age to create an account" +
                        " with us! Come back in " + (16 - age) + " years!";
                record = "Number of users consulted: " + numUsersConsulted + "\n Account type recommendation: illegible " +
                        "for account creation.";
            }
            else if (age >= 16 && 25 >= age && inSchool) {
                currentUserBeingConsultedType = "Student";
                s = "Based on your information, it looks like a Student Bank Account is the " +
                        "right fit for you! We are transferring you over to the account creation page!";
                record = "Number of users consulted: " + numUsersConsulted + " Account type recommendation: " +
                        currentUserBeingConsultedType;
            }
            else if(age > 60){
                currentUserBeingConsultedType = "Retired/Pension";
                s = "Based on your information, it looks like a Pension/Retirement Bank Account is the " +
                        "right fit for you! We are transferring you over to the account creation page!";
                record = "Number of users consulted: " + numUsersConsulted + " Account type recommendation: " +
                        currentUserBeingConsultedType;
            }
            else{
                currentUserBeingConsultedType = "Standard";
                s = "Based on your information, it looks like our Standard Bank Account is the " +
                        "right fit for you! We are transferring you over to the account creation page!";
                record = "Number of users consulted: " + numUsersConsulted + " Account type recommendation: " +
                        currentUserBeingConsultedType;
                }
        addToUserAdviseHistory(record);
        return s;
    }

    /**
     * Get the history of actions!
     * @return History of advice given
     */
    public ArrayList<String> getUserAdviseHistory() {
        return UserAdviseHistory;
    }

    /**
     * Contact the Bank Manager!
     *
     */
    public void contactBM(String message, BankManager BM){
        BM.populateInbox(message);
    }
}