package phase2.Operators.BankAccountUser;

import phase2.FundStores.Account;
import phase2.FundStores.Asset.Debit;
import phase2.FundStores.Debt.Credit;
import phase2.Operators.Contract;

import java.util.ArrayList;

public class PointSystemUser extends User implements Contract {
    private static ArrayList<User> userDatabase;
    private static int numUsers = 0;
    private int numChequingAccounts = 0;
    private String password;
    private String username;
    private String userType;
    private ArrayList<Account> accountsCreated;
    private int numPoints = 0;


    public PointSystemUser(String username, String password){
        super(username, password);
        numUsers++;
        this.userDatabase = new ArrayList<User>(); //- WOULD ERASE OLD INFO!
        userDatabase.add(this);
        this.accountsCreated = new ArrayList<Account>();
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewCapabilities(){
        String s = "";
        s = "As a new Point System User, you are able to do the following: \n" +
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
    public String viewContract(){
        String s = "";
        s = "As a new Point System User of the Bank, " +
                "you agree not to engage in fraudulent behavior, " +
                "especially when filling in information for account consultation purposes." +
                "You agree not to abuse the point system, whether by engaging in point" +
                " gain motivated transactions or frequently switching between a point System User and" +
                "a standard user to gain the initial 50 points freely. Click next to agree.";
        return s;
    }

    /**
     * Increase the balance of the account based on the number of points that the account holds
     * @param account
     */
    public String cashPoints(Account account){
        if (!account.pointsToCash()){
            return "Sorry, you do not have enough points to cash at the moment!";
        }else{
            return "You have successfully cashed all of your available points." +
                    " Your new number of points is: " + account.getNumPoints();
        }
    }

    /**
     * Increase the balance of the account based on the number of points that all of the user's accounts holds
     *
     */
    public double getNetTotal(){
        double totalAsset = 0;
        double totalDebt = 0;
        for (Account account: accountsCreated){
            if (account instanceof Debit){
                totalAsset += account.getBalance();
            } else if (account instanceof Credit){
                totalDebt -= account.getBalance();
            }
        } return totalAsset - totalDebt;
    }

    /**
     * Opt out of the point system
     * @return String - the confirmation.
     */
    public String optOutOfPointSystem(){
        String s = "";
        User alteredUser;
        alteredUser = new User(getUsername(), getPassword());
        alteredUser.setAccountsCreated(this.getAccountsCreated());
        //IN GUI CALL BM.delete(this);
        //iterate over arrayList of users and replace instance!!
        s = "You have successfully opted out of the point system! You can always chose to join once again.";
        return s;
    }
}
