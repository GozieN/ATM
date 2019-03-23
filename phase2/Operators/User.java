package phase2.Operators;

import phase2.FundHolders.*;

import java.util.*;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 */
public class User extends Operator implements Serializable, Iterable<Account>{
    private static ArrayList<User> userDatabase;
    private static int numUsers = 0;
    private String username;
    private String password;
    private String userType;
    private ArrayList<Account> accountsCreated;


    /**
     * User constructor
     * @param username Username used to login to account
     * @param password Password used to login to account
     */
    public User(String username, String password) {
        super(username, password);
        numUsers++;
        this.userDatabase = new ArrayList<User>();
        userDatabase.add(this);
        this.accountsCreated = new ArrayList<Account>();
    }


    /**
     * Change the user's password
     * @param currentPassword The current password used to login to account
     * @param newPassword Create new password for login to account
     */
    public void changePassword(String currentPassword, String newPassword) {
        if (currentPassword.equals(this.password)) {
            this.password = newPassword;
            System.out.println(username + ", your password has successfully been changed");
        } else {
            System.out.println(username + ",you have entered the wrong current password. " +
                    "unable to change password");
        }
    }

    /**
     * Set the type of the account.
     * @param userType the type of the user.
     */
    public void setUserType(String userType){
        this.userType = userType;
    }


    /**
     * Return a list of the accounts of the users
     * @return ArrayList of user accounts created
     */
    public ArrayList<Account> getAccountsCreated() {
        return accountsCreated;
    }

    /**
     * Set a list of bank accounts created
     * @param accountsCreated A list of bank accounts created
     */
    public void setAccountsCreated(ArrayList<Account> accountsCreated) {
        this.accountsCreated = accountsCreated;
    }

    /**
     *Add to the accounts created
     * @param account Instance of account
     */
    public void addToAccountsCreated(Account account) {
        accountsCreated.add(account);
    }


    /**
     * Get a summary of the user's accounts
     */
    public void viewInfo(){

        int totalDebitAmount = 0;
        int totalCreditAmount = 0;
        if (accountsCreated == null){
            System.out.println("Nothing to view, you have not created an account yet!");
        }else{

        String s = "Account holder: " + this.username + " Report of FundHolders:";
        for(int i = 0; i < accountsCreated.size(); i++){
            s += accountsCreated.get(i).getAccountType() + "Number: " + accountsCreated.get(i).getAccountNum() + "\n" +
                     "\n Current Balance:" +
                    accountsCreated.get(i).getBalance() + " Most Recent Transactions: " + "BM GET MOSTRECENTTRANSACTION";
            if (accountsCreated.get(i) instanceof Debit){
                totalDebitAmount += accountsCreated.get(i).getBalance();
            }else{
                totalCreditAmount += accountsCreated.get(i).getBalance();
            }
        }
        s += "Net Total: " + (totalDebitAmount - totalCreditAmount);
        System.out.println(s);
    }}

    @Override
    public Iterator<Account> iterator() {
        return accountsCreated.iterator();
    }

    class AccountIterator implements Iterator<Account> {
        int i = 0;

        @Override
        public boolean hasNext() {
            if (i >= accountsCreated.size()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Account next() {
            return accountsCreated.get(i++);
        }

        @Override
        public void remove() {
            accountsCreated.remove(--i);
        }
    }
}