package phase1.Operators;

import phase1.FundHolders.*;
import phase1.FundTransfers.*;
import java.util.*;

/**
 *
 */
public class User extends Observable implements Operator {
    private static ArrayList<User> userDatabase = new ArrayList<User>();
    private static int numUsers = 0;
    private String username;
    private String password;
    //private Credit cca = null;
    //private Credit loca = null;
    //private ChequingAccount ca = null;
    //private SavingsAccount sa = null;
    private ArrayList<Account> AccountsCreated = new ArrayList<Account>();
    private double cash;


    /**
     * User constructor
     * @param username
     * @param password
     */
// user constructor (BM use ONLY in console)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        numUsers++;
        userDatabase.add(this);
        this.cash = 0;
    }

    /**
     * Set the user's initial password
     * @param newPassword
     */
    public void setInitialPassword(String newPassword) {
        this.password = newPassword;
        setChanged();
        notifyObservers(newPassword);
        clearChanged();
    }

    /**
     * Change the user's password
     * @param currentPassword
     * @param newPassword
     */
    public void changePassword(String currentPassword, String newPassword) {
        if (currentPassword.equals(this.password)) {
            this.password = newPassword;
            System.out.println("your password has successfully been changed");
        } else {
            System.out.println("you have entered the wrong current password. " +
                    "unable to change password");
        }
    }

    /**
     * Request an account to be created
     * @param account
     */
     public void requestAccountCreation(Account account) {
         setChanged();
         notifyObservers(account);
         clearChanged();
     }

    /**
     * Return the user's username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return a list of the accounts of the users
     * @return ArrayList
     */
    public ArrayList<Account> getAccountsCreated() {
        return AccountsCreated;
    }

    /**
     * Set
     * @param accountsCreated
     */
    public void setAccountsCreated(ArrayList<Account> accountsCreated) {
        AccountsCreated = accountsCreated;
    }

    /**
     *Add to the accounts created
     * @param account
     */
    public void addToAccountsCreated(Account account) {
        AccountsCreated.add(account);
    }

    /**
     * Return a summary of the accounts
     * @param account
     */
    @Override
    public void singleAccountSummary(Account account) {
        System.out.println("Account holder: " + this.username + " "
                + "DATE AND TIME " +
                "" + "Account summary:" + account.getAccountType() +"Account Number: "
                + account.getAccountNum() + " contains: " + account.getBalance() + "currency");}

    /**
     * Vie the balance related to the account
     * @param account
     */
    @Override
    public void viewBalance(Account account) {
        System.out.println("Account: " + account.getAccountNum() + " has a balance of: " + account.getBalance());
    }


    /**
     * Get a summary of the user's accounts
     */
    public void viewInfo(){

        int totalDebitAmount = 0;
        int totalCreditAmount = 0;

        String s = "Account holder: " + this.username + " Report of FundHolders:";
        for(int i = 0; i < AccountsCreated.size(); i++){
            s += AccountsCreated.get(i).getAccountType() + "Number: " + AccountsCreated.get(i).getAccountNum() + "\n" +
                    " created on: GETDATEOFCREATION" + "\n Current Balance:" +
                    AccountsCreated.get(i).getBalance() + " Most Recent Transaction: " + "BM GET MOSTRECENTTRANSACTION";
            if (AccountsCreated.get(i) instanceof Debit){
                totalDebitAmount += AccountsCreated.get(i).getBalance();
            }else{
                totalCreditAmount += AccountsCreated.get(i).getBalance();
            }
        }
        s += "Net Total: " + (totalDebitAmount - totalCreditAmount);
        System.out.println(s);
    }
}

    // user will not have to input any parameters (direct call)

    // user input parameters: account num/type
/**
 *
 */
//    public void transfer(int amount, Account from, Account to) {
//        from.setBalance(from.getBalance() - amount);
//        to.setBalance(to.getBalance() + amount);
//
//        //[Angela]
//        try {
//            PrintStream originalOut = System.out;
//            PrintStream fileOut = new PrintStream("/.Outgoing.txt");
//            System.setOut(fileOut);
//            originalOut.println("[Amount] transferred to [Receiver Account]");
//            System.setOut(originalOut);
//        } catch (FileNotFoundException ex) {ex.printStackTrace();}
//
//    }
//