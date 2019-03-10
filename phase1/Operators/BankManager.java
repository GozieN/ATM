package phase1.Operators;

import phase1.FundHolders.ATM;
import phase1.FundHolders.Account;
import java.io.*;
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
        User newUser = new User(username, password);
        System.out.println("Your account has been created! Your username is: " + newUser.getUsername() + " and" +
                "your initial password is: " + newUser.getPassword());

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
        atm.setNum5Bills(atm.getNum5Bills() + num5bills);
        if (num5bills >= 0) {
            System.out.println("number of $5 bills added to the ATM: " + num5bills + "\n" +
                    "the number of $5 bills in the ATM is now : " + atm.getNum5Bills());
        }
        else {
            System.out.println("the number of $5 bills in the ATM is now: " + num5bills);
        }
    }

    /**
     * Get the list of User
     * @return
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Get the number of 5$ bills
     * @param atm
     */
    public int ATMGetNum5Bills(ATM atm) {
        System.out.println("the number of $5 bills in the ATM is: " + atm.getNum5Bills());
        return atm.getNum5Bills();
    }

    /**
     *Set the number of 10$ bills
     * @param atm
     * @param num10bills
     */
    public void ATMSetNum10Bills(ATM atm, int num10bills) {
        atm.setNum10Bills(atm.getNum10Bills() + num10bills);
        if (num10bills >= 0) {
            System.out.println("number of $10 bills added to the ATM: " + num10bills + "\n" +
                    "the number of $10 bills in the ATM is now : " + atm.getNum10Bills());
        }
        else {
            System.out.println("the number of $10 bills in the ATM is now: " + num10bills);
        }
    }

    /**
     * Set the number of 10$ bills
     * @param atm
     */
    public int ATMGetNum10Bills(ATM atm) {
        System.out.println("the number of $10 bills in the ATM is: " + atm.getNum10Bills());
        return atm.getNum10Bills();
    }

    /**
     * Add the number of 5$ bills
     * @param atm
     * @param num10bills
     */

    /**
     * Set the number of 20$ bills
     * @param atm
     * @param num20bills
     */
    public void ATMSetNum20Bills(ATM atm, int num20bills) {
        atm.setNum20Bills(atm.getNum20Bills() + num20bills);
        if (num20bills >= 0) {
            System.out.println("number of $20 bills added to the ATM: " + num20bills + "\n" +
                    "the number of $20 bills in the ATM is now : " + atm.getNum20Bills());
        }
        else {
            System.out.println("the number of $20 bills in the ATM is now: " + num20bills);
        }
    }

    /**
     * Get the number of 20$ bills
     * @param atm
     */
    public int ATMGetNum20Bills(ATM atm) {
        System.out.println("the number of $20 bills in the ATM is: " + atm.getNum20Bills());
        return atm.getNum20Bills();
    }

    /**
     * Add the number of 20$ bills
     * @param atm
     * @param num20bills
     */

    /**
     * Set the number of 50$ bills
     * @param atm
     * @param num50bills
     */
    public void ATMSetNum50Bills(ATM atm, int num50bills) {
        atm.setNum5Bills(num50bills);
        if (num50bills >= 0) {
            System.out.println("number of $50 bills added to the ATM: " + num50bills + "\n" +
                    "the number of $50 bills in the ATM is now : " + atm.getNum50Bills());
        }
        else {
            System.out.println("the number of $50 bills in the ATM is now: " + num50bills);
        }
    }

    /**
     * Get the number of 5$ bills
     * @param atm
     */

    public int ATMGetNum50Bills(ATM atm) {
        System.out.println("the number of $50 bills in the ATM is: " + atm.getNum50Bills());
        return atm.getNum50Bills();
    }

    /**
     * Read the file to restock the ATM
     * @param atm
     */
    public void restockFromFile(ATM atm) {

        try {
            File file = new File("./alerts.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("Five dollar bills low in stock!")) {
                    this.ATMSetNum5Bills(atm, 100);
                } else if (line.equals("Ten dollar bills low in stock!")) {
                    this.ATMSetNum10Bills(atm, 100);
                } else if (line.equals("Twenty dollar bills low in stock!")) {
                    this.ATMSetNum20Bills(atm, 100);
                } else if (line.equals("Fifty dollar bills low in stock!")) {
                    this.ATMSetNum50Bills(atm, 100);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
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