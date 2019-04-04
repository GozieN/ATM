package phase2.Operators.BankAccountUser;

import phase2.FundStores.Account;

import java.util.ArrayList;

public class StudentPointSystemUser extends PointSystemUser{
    private static ArrayList<User> userDatabase;
    private static int numUsers = 0;
    private int numChequingAccounts = 0;
    private String password;
    private String username;
    private String userType;
    private ArrayList<Account> accountsCreated;
    private int numPoints = 0;

    /**
     * StudentPointSystemUser constructor
     * @param username Username for login
     * @param password Password for login
     */
    public StudentPointSystemUser(String username, String password){
        super(username, password);
        numUsers++;
        this.accountsCreated = new ArrayList<Account>();
        numPoints = 120;

    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    @Override
    public String viewCapabilities(){
        String s = "";
        s = "As a new Student Point System User, you are able to do the following: \n" +
                "- Gain an initial balance of 50$.\n" +
                "- Gain an initial 120 points.\n" +
                "- Cash Points (when you have accumulated at least 20.\n" +
                "- Opt out of being a point system user at any point in time. \n" +
                "- Request to delete your account at any point in time.\n" +
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
    @Override
    public String viewContract(){
        String s = "";
        s = "As a new Student Point System User of the Bank, " +
                "you agree not to engage in fraudulent behavior, " +
                "especially when filling in information for account consultation purposes." +
                "You agree not to abuse the point system, by engaging in point" +
                " gain motivated transactions. Click next to agree.";
        return s;
    }
}
