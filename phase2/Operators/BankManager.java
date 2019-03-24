package phase2.Operators;

import phase2.FundStores.*;
import phase2.FundStores.Account;
import phase2.FundStores.Asset.ChequingAccount;
import phase2.FundStores.Asset.SavingsAccount;
import phase2.FundStores.Debt.Credit;

import java.io.*;
import java.util.*;
import java.io.Serializable;

public class BankManager extends BankWorker implements Observer, Serializable{
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
        User userInFile = null;
        try {
            FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            userInFile = (User) in.readObject();
            in.close();
            file.close();
            if (userInFile.getUsername().equals(user.getUsername())) {
                userInFile.addToAccountsCreated(newAccount);
            }
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
     * Create a user
     * @param username Username used for login into accounts
     * @param password Password used for login into accounts
     */
    public void createUser (String username, String password) {

        User newUser = new User(username, password);
        User existingUser = null;
        List<User> userInFileList = new ArrayList<User>();

        try {

            FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            existingUser = (User) in.readObject();

            userInFileList.add(existingUser);

            in.close();
            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        try {
            FileOutputStream file2 = new FileOutputStream("phase2/txtfiles/Users.txt");
            ObjectOutputStream out = new ObjectOutputStream(file2);

            out.writeObject(newUser);
            for (User obj: userInFileList) {
                if (!(obj.getUsername().equals(newUser.getUsername()))) {
                    out.writeObject(obj);
                }
            }

            out.close();
            file2.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        User user = null;

        try {
            FileInputStream file3 = new FileInputStream("phase2/txtfiles/Users.txt");
            ObjectInputStream in = new ObjectInputStream(file3);

            user = (User) in.readObject();

//            System.out.println(user.getUsername());

            in.close();
            file3.close();

        } catch (Exception ex) {ex.printStackTrace();}

    }

    //[Angela]
    /**
     * Delete a user
     * @param user the User object that needs to be deleted
     */

    public void deleteUser(User user) {

        User newUser = new User("", "");

        try {
            String filename = "phase2/txtfiles/Users.txt";

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(newUser);

            out.close();
            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        User userToRemove = null;

        try {
            FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            userToRemove = (User) in.readObject();

            in.close();
            file.close();

            if (userToRemove.getUsername().equals(user.getUsername())) {

                userToRemove.setUsername(null);
                userToRemove.setPassword(null);
                userToRemove.setAccountsCreated(null);
            }

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
        bm.createUser("angela", "chung");
        bm.createUser("chung", "angela");
        bm.createUser("hey", "you");
//        User userInFile = new User("hey", "you");
//        bm.deleteUser(userInFile);

//        User user = null;
//        try {
//            FileInputStream file = new FileInputStream("phase2/Users.txt");
//            ObjectInputStream in = new ObjectInputStream(file);
//
//            user = (User) in.readObject();
//
//            System.out.println(user.getUsername());
//
//            in.close();
//            file.close();

//            System.out.println(user.getUsername());

//        } catch (Exception ex) {ex.printStackTrace();}
    }

}

