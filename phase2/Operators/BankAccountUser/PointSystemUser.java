package phase2.Operators.BankAccountUser;

import phase2.FundStores.Account;
import phase2.Operators.Contract;

import java.util.ArrayList;

public class PointSystemUser extends User implements Contract, Rewardable {
    private static ArrayList<User> userDatabase;
    private static int numUsers = 0;
    private int numChequingAccounts = 0;
    private String password;
    private String username;
    private String userType;
    private boolean isGoldMember;
    private ArrayList<Account> accountsCreated;
    private int numPoints = 0;

    /**
     * PointSystemUser constructor
     * @param username Username for login
     * @param password Password for login
     */
    public PointSystemUser(String username, String password){
        super(username, password);
        numUsers++;
        this.userDatabase.add(this);
        userDatabase.add(this);
        this.accountsCreated = new ArrayList<Account>();
        numPoints = 100;
    }

    /**
     * Return whether the user is a gold member
     * @return if it is or isn't
     */
    public boolean getIsGoldMember(){
        return isGoldMember;
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewCapabilities(){
        String s = "";
        s = "As a new Point System User, you are able to do the following: \n" +
                "- Cash Points (when you have accumulated at least 20.\n" +
                "- Gain an initial 100. \n" +
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
                "You agree not to abuse the point system, by engaging in point" +
                " gain motivated transactions. Click next to agree.";
        return s;
    }

    /**
     * set the number of points that the user should should contain.
     * @param numPoints the number of points
     */
    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    /**
     *Add to the accounts created
     * @param account Instance of account
     */
    @Override
    public void addToAccountsCreated(Account account) {
        accountsCreated.add(account);
        addObserver(account);
    }

    /**
     * Return the number of points that the account contains
     * @return int - representing the number of points.
     */
    public int getNumPoints() {
        return numPoints;
    }

    /**
     * Return the number of points that the account contains
     * @return int - representing the number of points.
     */
    public int viewRewards() {
        return getNumPoints();
    }

    /**
     * Set the number of points that the account should contain
     */
    public void setNumPointsIncrease() {
        this.numPoints += 5;
        if (isGoldMember){
            numPoints += 5*5;
        }
    }

    /**
     * Set the number of points that the account should contain
     */
    public void setNumPointsDecrease() {
        this.numPoints -= 20;
    }


    /**
     * Prompt the point rewards to be cashed by notifying each account.
     */
    public void retrieveRewards(){
        if (numPoints > 100){
            isGoldMember = true;
//            BankUserFactory buf = new BankUserFactory();
        }
        notifyObservers();
        }

    /**
     * update the accounts so they cash their points.
     */
    @Override
     public void notifyObservers(){
        for (Account a: accountsCreated){
            a.update(this, true);
        }
     }

    /**
     * Opt out of the point system
     * @return String - the confirmation.
     */
    public String optOutOfPointSystem(){
        String s = "";
        User alteredUser;
        BankUserFactory b = new BankUserFactory(this.getUserType());
        alteredUser = b.determineOptOutUserType(this);
        alteredUser.setAccountsCreated(this.accountsCreated);
        //IN GUI CALL BM.delete(this);
        //iterate over arrayList of users and replace instance!!

        return  "You have successfully opted out of the point system! You can always chose to later on but" +
                "you will not receive the original 50 points to deter abuse of the free points!";
    }
}
