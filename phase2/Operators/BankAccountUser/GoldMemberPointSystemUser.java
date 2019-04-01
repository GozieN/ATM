package phase2.Operators.BankAccountUser;

import phase2.FundStores.Account;

import java.util.ArrayList;

public class GoldMemberPointSystemUser extends PointSystemUser{
    private static ArrayList<User> userDatabase;
    private static int numUsers = 0;
    private int numChequingAccounts = 0;
    private String password;
    private String username;
    private String userType;
    private ArrayList<Account> accountsCreated;
    private int numPoints = 0;

    /**
     * GoldMemberPointSystemUser constructor
     * @param username Username for login
     * @param password Password for login
     */
    public GoldMemberPointSystemUser(String username, String password){
        super(username, password);
        numUsers++;
        this.userDatabase = new ArrayList<User>(); //- WOULD ERASE OLD INFO!
        userDatabase.add(this);
        this.accountsCreated = new ArrayList<Account>();
        numPoints = 120;
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has
     */
    @Override
    public String viewCapabilities(){
        String s = "";
        s = "As a Gold Member Point System User, the following applies: \n" +
                "- Gain an initial 400 points.\n" +
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
     * @return String - the features this user has
     */
    public String viewContract(){
        String s = "";
        s = "As a Gold Member Point System User of the Bank, " +
                "you agree not to engage in fraudulent behavior, " +
                "especially when filling in information for account consultation purposes." +
                "You agree not to abuse the point system, whether by engaging in point" +
                " gain motivated transactions.Click next to agree.";
        return s;
    }
}