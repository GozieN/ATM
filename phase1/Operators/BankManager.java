package phase1.Operators;

import phase1.FundHolders.*;

import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class BankManager extends BankWorker implements Serializable{
    private ArrayList<BankManager> bankManagerDatabase = new ArrayList<>();
    private static int numBankManagers = 0;
    private String username;
    private String password;
    private int numExistingAccounts;
    private ArrayList<User> users = new ArrayList<>();
    public BankManager(String username, String password) {
        super(username, password);
        numBankManagers += 1;
        bankManagerDatabase.add(this);
    }

    /**
     * Create and update the list of accounts that a user has
     * @param user Array list of user accounts
     * @param startingAmount Amount of money the account will start off with
     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
     */
    public void createNewAccount(double startingAmount, String accountType, User user){
        Account newAccount = null;

            if (accountType == "LineOfCredit") {
                newAccount = new Credit(numExistingAccounts, user.getUsername(), startingAmount, true);

            } else if (accountType == "Credit"){
                newAccount = new Credit(numExistingAccounts, user.getUsername(), startingAmount, false);

            } else if (accountType == "Savings"){
                newAccount = new SavingsAccount(numExistingAccounts, user.getUsername(), startingAmount);
            } else if (accountType == "Chequing"){
                if (user.getAccountsCreated() != null){
                    for (Account i:user.getAccountsCreated()){
                        if (i.getAccountType() == "Chequing"){
                                newAccount = new ChequingAccount(numExistingAccounts, user.getUsername(), startingAmount,
                                        true);
                            }
                        }
                        newAccount = new ChequingAccount(numExistingAccounts, user.getUsername(), startingAmount,
                            false);
                    }
                }

            if (newAccount == null){
                System.out.println("Sorry, it seems as though an error occurred when creating your account. Please" +
                        "make sure that the account type input is one of the following options: LineOfCredit, Credit, " +
                        "Savings, Chequing");
            }else{
            numExistingAccounts++;
            user.addToAccountsCreated(newAccount);
            System.out.println("Hello " + user.getUsername() + " " +
                    ", the following account:" +
                    newAccount.accountType + "with account Number: "
                    + newAccount.getAccountNum() +  "was created upon your request.");
        }}

    /**
     * Create a user
     * @param username Username used for login into accounts
     * @param password Password used for login into accounts
     */
    public void createUser(String username, String password) {
        User newUser = new User(username, password);

        try {
            String filename = "Users.txt";

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(newUser);

            out.close();
            file.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Your account has been created! Your username is: " + newUser.getUsername() + " and" +
                "your initial password is: " + newUser.getPassword());

    }


    /**
     * Set the date to be displayed on the ATM
     * @param atm Instance of ATM machine
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
     * @param atm Instance of ATM machine
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
     * @param atm Instance of ATM machine
     * @param num5bills Number of $5 bills in ATM
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
     * @param atm Instance of ATM machine
     */
    public int ATMGetNum5Bills(ATM atm) {
        System.out.println("the number of $5 bills in the ATM is: " + atm.getNum5Bills());
        return atm.getNum5Bills();
    }

    /**
     *Set the number of 10$ bills
     * @param atm Instance of ATM machine
     * @param num10bills Number of $10 bills in ATM
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
     * @param atm Instance of ATM machine
     */
    public int ATMGetNum10Bills(ATM atm) {
        System.out.println("the number of $10 bills in the ATM is: " + atm.getNum10Bills());
        return atm.getNum10Bills();
    }

    /**
     * Add the number of 5$ bills
     * @param atm Instance of ATM machine
     * @param num10bills Number of $10 bills in ATM
     */

    /**
     * Set the number of 20$ bills
     * @param atm Instance of ATM machine
     * @param num20bills Number of $20 bills in ATM
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
     * @param atm Instance of ATM machine
     */
    public int ATMGetNum20Bills(ATM atm) {
        System.out.println("the number of $20 bills in the ATM is: " + atm.getNum20Bills());
        return atm.getNum20Bills();
    }

    /**
     * Add the number of 20$ bills
     * @param atm Instance of ATM machine
     * @param num20bills Number of $20 bills in ATM
     */

    /**
     * Set the number of 50$ bills
     * @param atm Instance of ATM machine
     * @param num50bills Number of $50 bills in ATM
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
     * @param atm Instance of ATM machine
     */

    public int ATMGetNum50Bills(ATM atm) {
        System.out.println("the number of $50 bills in the ATM is: " + atm.getNum50Bills());
        return atm.getNum50Bills();
    }

    /**
     * Read the file to restock the ATM
     * @param atm Instance of ATM machine
     */
    public void restockFromFile(ATM atm) throws FileNotFoundException {

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

            try{
                File temptFile = new File("./myTempFile.txt");

                BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(temptFile));

                String lineToRemove = line;
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(lineToRemove)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                boolean successful = temptFile.renameTo(file);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }}

    /**
     * Print a summary of the user's accounts
     * @param user Instance of user
     */
    public void viewInfo(User user){user.viewInfo();}

    /**
     * Undo the most recent transaction in the account
     * @param account Instance of the account
     */
    public void undoMostRecentTransaction(Account account) {
        account.alterHistory();
        }

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
