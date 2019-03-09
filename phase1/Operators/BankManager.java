package phase1.Operators;

import phase1.FundHolders.ATM;
import phase1.FundHolders.Account;

import java.util.*;

public class BankManager extends BankWorker implements Observer {
    private static ArrayList<BankManager> bankManagerDatabase = new ArrayList<>();
    private static int numBankManagers = 0;
    private String username;
    private String password;
    public ArrayList<User> users = new ArrayList<>();

    public BankManager(String username, String password) {
        this.username = username;
        this.password = password;
        numBankManagers += 1;
        bankManagerDatabase.add(this);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * Update the list of accounts that a user has
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Account){
            ((User) o).addToAccountsCreated((Account) arg);
             System.out.println(((User) o).getUsername() +
                     ", the following account:" +
                     ((Account) arg).accountType + "with account Number: "
                     + ((Account) arg).getAccountNum() +  "was created upon your request");
            }
        else if(arg instanceof String){
            System.out.println("Your initial password has been set. You are able to change it later.");
        }
        else if(arg instanceof User){
            users.add((User) arg);
            createUser(((User) arg).getUsername(), ((User) arg).getPassword());
        }
    }

    /**
     * Create a user
     * @param username
     * @param password
     */
    public void createUser(String username, String password) {
        if ( (username.substring(0, 4)).equals("USER") ) {
            new User(username, password);
            System.out.println("The User with name: " + username + " has been created");
        } else {
            System.out.println("Invalid username for a user");
        }
    }


    /**
     * Set the user's password
     * @param user
     * @param newpassword
     */
    public void setUserPassword(User user, String newpassword) {
        user.setInitialPassword(newpassword);
        System.out.println("User " + user.getUsername() + "'s password has been changed");
    }

    /**
     * Set the date to be displayed on the ATM
     * @param atm
     * @param day
     * @param month
     * @param year
     */
    public void ATMSetDate(ATM atm, int day, int month, int year) { // format dd:mm:yy
       atm.setDate(day, month, year);
        System.out.println("the date has been set to " + day + ':' + month + ':' + year);
    }

    /**
     * Set the time displayed on the ATM
     * @param atm
     * @param hour
     * @param minute
     * @param second
     */
    public void ATMSetTime(ATM atm, int hour, int minute, int second) { // format hh:mm:ss
        atm.setTime(hour, minute, second);
        System.out.println("the time has been set to " + hour + ':' + minute + ':' + second);
    }

    /**
     * Set the number of 5$ bills
     * @param atm
     * @param num5bills
     */
    public void ATMSetNum5Bills(ATM atm, int num5bills) {
        atm.setNum5Bills(num5bills);
        System.out.println("the number of $5 bills in the ATM is now: " + num5bills);
    }

    /**
     * Get the number of 5$ bills
     * @param atm
     */
    public void ATMGetNum5Bills(ATM atm) {
        System.out.println("the number of $5 bills in the ATM is: " + atm.getNum5Bills());
    }

    /**
     * Add the number of 5$ bills
     * @param atm
     * @param num5bills
     */
    public void ATMAddNum5Bills(ATM atm, int num5bills) {
        atm.addNum5Bills(num5bills);
        System.out.println("number of $5 bills added to the ATM: " + num5bills + "\n" +
                "the number of $5 bills in the ATM is now : " + atm.getNum5Bills());
    }

    /**
     *Set the number of 10$ bills
     * @param atm
     * @param num10bills
     */
    public void ATMSetNum10Bills(ATM atm, int num10bills) {
        atm.setNum10Bills(num10bills);
        System.out.println("the number of $10 bills in the ATM is now: " + num10bills);
    }

    /**
     * Set the number of 10$ bills
     * @param atm
     */
    public void ATMGetNum10Bills(ATM atm) {
        System.out.println("the number of $10 bills in the ATM is: " + atm.getNum10Bills());
    }

    /**
     * Add the number of 5$ bills
     * @param atm
     * @param num10bills
     */
    public void ATMAddNum10Bills(ATM atm, int num10bills) {
        atm.addNum10Bills(num10bills);
        System.out.println("number of $10 bills added to the ATM: " + num10bills + "\n" +
                "the number of $10 bills in the ATM is now : " + atm.getNum10Bills());
    }

    /**
     * Set the number of 20$ bills
     * @param atm
     * @param num20bills
     */
    public void ATMSetNum20Bills(ATM atm, int num20bills) {
        atm.setNum20Bills(num20bills);
        System.out.println("the number of $20 bills in the ATM is now: " + num20bills);
    }

    /**
     * Get the number of 20$ bills
     * @param atm
     */
    public void ATMGetNum20Bills(ATM atm) {
        System.out.println("the number of $20 bills in the ATM is: " + atm.getNum20Bills());
    }

    /**
     * Add the number of 20$ bills
     * @param atm
     * @param num20bills
     */
    public void ATMAddNum20Bills(ATM atm, int num20bills) {
        atm.addNum20Bills(num20bills);
        System.out.println("number of $20 bills added to the ATM: " + num20bills + "\n" +
                "the number of $20 bills in the ATM is now : " + atm.getNum20Bills());
    }

    /**
     * Set the number of 50$ bills
     * @param atm
     * @param num50bills
     */
    public void ATMSetNum50Bills(ATM atm, int num50bills) {
        atm.setNum5Bills(num50bills);
        System.out.println("the number of $50 bills in the ATM is now: " + num50bills);
    }

    /**
     * Get the number of 5$ bills
     * @param atm
     */
// ATMGetNum50bills method
    public void ATMGetNum50Bills(ATM atm) {
        System.out.println("the number of $50 bills in the ATM is: " + atm.getNum50Bills());
    }

    /**
     * Add the number of 50$ bills
     * @param atm
     * @param num50bills
     */
    public void ATMAddNum50Bills(ATM atm, int num50bills) {
        atm.addNum50Bills(num50bills);
        System.out.println("number of $50 bills added to the ATM: " + num50bills + "\n" +
                "the number of $50 bills in the ATM is now : " + atm.getNum50Bills());
    }


    /**
     * Print a summary of the user's accounts
     * @param user
     */
    public void viewInfo(User user) {
        user.viewInfo();
    }

    /**
     * Undo the most recent transaction in the account
     * @param account
     */
    public void undoMostRecentTransaction(Account account) {
        //NEED AN UNDO ACTION METHOD INSIDE THE ACCOUNT CLASS that deals with the  ARRayYLIST OF TRANSACTIONS
        //REAL LIFE USER NEEDS AN UNDO METHOD
    }

    //    public void undoMostRecentTransaction(User user, Account account) {
//    String msg=transactions.get(-1); // Gain access to last element (popping)
//    if (transactions.size()>0){
//        transactions.remove(transactions.size()-1);
//    }
//    if (){ // "deposit 20" then if deposit in msg you go here, consider ALL cases that involve gaining money
//
//    }else if(){ // "withdraw 20" then if withdraw go here, consider ALL cases that involve losing money
//
//    }
//    }
//    // input parameters: user instance, account num/type
}