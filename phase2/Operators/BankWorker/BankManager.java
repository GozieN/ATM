package phase2.Operators.BankWorker;

import phase2.FundStores.*;
import phase2.FundStores.Account;
import phase2.FundStores.Asset.ChequingAccount;
import phase2.FundStores.Asset.SavingsAccount;
import phase2.FundStores.Debt.Credit;
import phase2.Operators.BankAccountUser.User;

import java.io.*;
import java.util.*;
import java.io.Serializable;

public class BankManager extends BankEmployee implements Observer, Serializable{
    private static ArrayList<BankManager> bankManagerDatabase = new ArrayList<>();
    private static int numBankManagers = 0;
    private String username;
    private String password;
    private int numExistingAccounts;
    private int numMessages;
    private ArrayList<User> users = new ArrayList<>();
    private String accessKey = "900";
    private Queue<String> inbox = new ArrayDeque<String>();

    public BankManager(String username, String password) {
        super(username, password);
        numBankManagers += 1;
        bankManagerDatabase.add(this);
    }

    public List<User> getUserList() {return users;}


    public String getMasterAccessKey() {
        return this.accessKey;
    }

    /**
     * Create and update the list of accounts that a user has
     * @param user Array list of user accounts
     * @param startingAmount Amount of money the account will start off with
     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
     */
    public void createNewAccount(double startingAmount, String accountType, User user) {
        Account newAccount = null;

        if (accountType.equals("LineOfCreditAccount")) {
            newAccount = new Credit(user, true);

        } else if (accountType.equals("Credit")) {
            newAccount = new Credit(user, false);

        } else if (accountType.equals("SavingsAccount")) {
            newAccount = new SavingsAccount(user);
        } else if (accountType.equals("ChequingAccount")) {
            user.setNumChequingAccounts();
            if (user.getNumChequingAccounts() == 1){
                newAccount = new ChequingAccount(user, "Chequing", true);
            }
            else {newAccount = new ChequingAccount(user, "Chequing", false);}
        }

        //[Angela]
        newAccount.getAccountsDatabase().add(newAccount);

        try {
            FileOutputStream file = new FileOutputStream("phase2/txtfiles/AccountDatabase.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(newAccount.getAccountsDatabase());

        } catch (Exception ex) {ex.printStackTrace();}


        for (User obj: users) {
            if (obj == user) {
                obj.getAccountsCreated().add(newAccount);
            }
        }

        try {
            FileOutputStream file = new FileOutputStream("phase2/txtfiles/Users.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(users);
            out.close();
            file.close();
        } catch (Exception ex) {ex.printStackTrace();}


        if (newAccount == null) {
            System.out.println("Sorry, it seems as though an error occurred when creating your account. Please" +
                    "make sure that the account type input is one of the following options: LineOfCredit, Credit, " +
                    "Savings, Chequing");
        } else {
            numExistingAccounts++;
            user.addToAccountsCreated(newAccount);
            System.out.println("Hello " + user.getUsername() + " " +
                    ", the following account: " +
                    newAccount.accountType + " with account Number: "
                    + newAccount.getAccountNum() + " was created upon your request.");
        }
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewCapabilities(){
        String s = "";
        s = "As the Bank Manger, you are able to do the following: \n" +
                "- Approve and create all kinds of users\n" +
                "- Create user accounts upon creation \n" +
                "- Delete a user account upon request.\n" +
                "- Create a user account upon request. \n" +
                "- View a summary of a user's single account. \n" +
                "- View a summary of all of a user's existing accounts \n" +
                "- Restock the ATM \n" +
                "- View your message inbox, that can be populated with Consultant Messages!";

        return s;
    }

    /**
     * Display the features that this user has.
     * @return String - the features this user has!
     */
    public String viewContract(){
        String s = "";
        s = "As the designated Bank Manager, " +
                "you agree not to engage in fraudulent behavior, " +
                "especially when dealing with user related transactions and ATM restocking. Click next to agree.";
        return s;
    }

    /**
     * Create a user
     * @param username Username used for login into accounts
     * @param password Password used for login into accounts
     */
    public void createUser (String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);

        try {
            FileOutputStream file2 = new FileOutputStream("phase2/txtfiles/Users.txt");
            ObjectOutputStream out = new ObjectOutputStream(file2);

            out.writeObject(users);

            out.close();
            file2.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //[Angela]
    /**
     * Delete a user
     * @param user the User object that needs to be deleted
     */

    public void deleteUser(User user) {

        for (User obj: users) {
            if (user.getUsername().equals(obj.getUsername())) {
                users.remove(obj);
            }
        }

        try {
            FileOutputStream file = new FileOutputStream("phase2/txtfiles/Users.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(users);

            out.close();
            file.close();

        } catch (Exception ex) {ex.printStackTrace();}
    }


    /**
     * Set the date to be displayed on the ATM
     * @param atm Instance of ATM machine
     * @param day
     * @param month
     * @param year
     */
    public void ATMSetDate(ATM atm, int day, int month, int year) throws IOException{ // format dd:mm:yy
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
    public void ATMSetTime(ATM atm, int hour, int minute, int second) throws IOException { // format hh:mm:ss
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
     * @return List of users from bank
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Get the number of 5$ bills
     * @param atm Instance of ATM machine
     * @return Int of the number of $5 bills in ATM machine
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
     * @return Int of number of $10 bills in ATM machine
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
     * @return Int of number of $20 bills in ATM machine
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
        atm.setNum5Bills(atm.getNum50Bills() + num50bills);
        if (num50bills >= 0) {
            System.out.println("number of $50 bills added to the ATM: " + num50bills + "\n" +
                    "the number of $50 bills in the ATM is now : " + (atm.getNum50Bills() + num50bills));
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
    public void restockFromFile(ATM atm) {

        try {
            File file = new File("phase2/txtfiles/alerts.txt");
            Scanner scan = new Scanner(file);
            int state = 0;

            while (scan.hasNext()) {
                String line = scan.nextLine();
                switch (state) {

                    case 0:
                        if (line.equals("Five dollar bills low in stock!")) {
                            this.ATMSetNum5Bills(atm, 100);
                            line = scan.nextLine();
                            state = 1;
                        }

                    case 1:
                        if (line.equals("Ten dollar bills low in stock!")) {
                            this.ATMSetNum10Bills(atm, 100);
                            line = scan.nextLine();
                            state = 2;
                        }


                    case 2:
                        if (line.equals("Twenty dollar bills low in stock!")) {
                            this.ATMSetNum20Bills(atm, 100);
                            line = scan.nextLine();
                            state = 3;

                        }

                    case 3:
                        if (line.equals("Fifty dollar bills low in stock!")) {
                            line = scan.toString();
                            this.ATMSetNum50Bills(atm, 100);
                        }
                }

            }
            scan.close();

        } catch (NoSuchElementException e) {
            System.out.println("Scanning finished");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
        account.undoTransaction();
    }

    @Override
    public void update(Observable o, Object arg) {
        String s = "";
        s = numMessages + ". " + arg;
        numMessages++;
//        inbox.add(s);
    }

    /**
     * View the messages in inbox from the consultant!
     */
    public String viewInbox() {
        String s = "";
        if (inbox.isEmpty()){
            s = "You have no messages at the moment!";
        } else{
            s = inbox.toString();
        } return s;
    }

    public static void main(String[] args) {
        BankManager bm = new BankManager("", "");
        bm.createUser("b", "b");
        bm.createUser("c", "c");
        bm.createUser("a", "a");
        bm.createUser("d", "d");
    }
}

