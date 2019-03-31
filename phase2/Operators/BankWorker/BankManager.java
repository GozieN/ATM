package phase2.Operators.BankWorker;

import phase2.FundStores.*;
import phase2.FundStores.Account;
import phase2.FundStores.Asset.ChequingAccount;
import phase2.FundStores.Asset.SavingsAccount;
import phase2.FundStores.Debt.Credit;
import phase2.FundStores.Debt.CreditCard;
import phase2.FundStores.Debt.lineofcredit;
import phase2.Operators.BankAccountUser.User;

import java.io.*;
import java.util.*;
import java.io.Serializable;
import java.io.BufferedReader;

public class BankManager extends BankTeller implements Iterable<User>, Serializable {
    private static ArrayList<BankManager> bankManagerDatabase = new ArrayList<>();
    private static int numBankManagers = 0;
    private String username;
    private String password;
    private int numMessages;
    private ATM atm;
    private static ArrayList<User> users = new ArrayList<>();
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

    @SuppressWarnings("unchecked")
    /**
     * Create and update the list of accounts that a user has
     * @param user Array list of user accounts
     * @param startingAmount Amount of money the account will start off with
     * @param accountType Type of account: Credit Card, Line of Credit, Chequing, or Savings
     */
    public void createNewAccount(double startingAmount, String accountType, User user) {
        Account newAccount = null;
        if (accountType.equals("LineOfCreditAccount")) {
            newAccount = new lineofcredit(user);

        } else if (accountType.equals("credit")) {
            newAccount = new CreditCard(user);

        } else if (accountType.equals("savings")) {
            newAccount = new SavingsAccount(user);
        } else if (accountType.equals("chequing")) {
            user.setNumChequingAccounts();
            if (user.getNumChequingAccounts() == 0) {
                newAccount = new ChequingAccount(user, true);
            } else {
                newAccount = new ChequingAccount(user, false);
            }
        }newAccount.setATM(atm);

        ArrayList<User> usersCopy = new ArrayList<>();
        usersCopy = (ArrayList<User>) users.clone();
        for (User obj: usersCopy) {
            if (obj.getUsername().equals(user.getUsername())) {
                obj.getAccountsCreated().add(newAccount);
            }
        }

        try {
            FileOutputStream file = new FileOutputStream("phase2/txtfiles/Users.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(usersCopy);
            out.close();
            file.close();
        } catch (Exception ex) {ex.printStackTrace();}

        if (newAccount == null) {
            System.out.println("Sorry, it seems as though an error occurred when creating your account. Please " +
                    "make sure that the account type input is one of the following options: LineOfCredit, Credit, " +
                    "Savings, Chequing");
        } else {
            user.addToAccountsCreated(newAccount);
            System.out.println("Hello " + user.getUsername() + " " +
                    ", the following account: " +
                    newAccount.accountType + " with account Number: "
                    + newAccount.getAccountNum() + " was created upon your request.");

        }

        for (Account acct: user.getAccountsCreated()) {
            if (acct instanceof ChequingAccount && ((ChequingAccount) acct).isPrimary) {
                System.out.println("And you primary chequing account is " + acct.getAccountNum());
                break;
            }
        }
    }

    public void deleteAccount(User user) {

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
                "especially when dealing with user related transactions and ATM restocking. Click back or exit.";
        return s;
    }

    /**
     * Create a user
     * @param username Username used for login into accounts
     * @param password Password used for login into accounts
     */
    public void createUser (String username, String password) {
        User newUser = new User(username, password);
        if (!(users.contains(newUser))) {
            users.add(newUser);
        }

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

    @SuppressWarnings("unchecked")

    /**
     * Delete a user
     * @param user the User object that needs to be deleted.
     */
    public void deleteUser(User user) {

        try {
            FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            users = (ArrayList<User>) in.readObject();
            for (User obj: users) {
                if (obj.getUsername().equals(user.getUsername())) {
//                    usersCopy.remove(obj);
                    users.remove(obj);
                }
            }
        } catch (Exception ex) {ex.printStackTrace();}

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

    public void setAtm(ATM atm) {
        this.atm = atm;
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
     * Set the number of 5$ bills
     * @param atm Instance of ATM machine
     * @param num5bills Number of $5 bills in ATM
     */
    public void ATMSetNum5Bills(ATM atm, int num5bills) {
        atm.setNum5Bills(this.ATMGetNum5Bills(atm) + num5bills);
        if (num5bills >= 0) {
            System.out.println("number of $5 bills added to the ATM: " + num5bills + "\n" +
                    "the number of $5 bills in the ATM is now : " + atm.getNum5Bills());
        }
        else {
            System.out.println("the number of $5 bills in the ATM is now: " + num5bills);
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
     *Set the number of 10$ bills
     * @param atm Instance of ATM machine
     * @param num10bills Number of $10 bills in ATM
     */
    public void ATMSetNum10Bills(ATM atm, int num10bills) {
        atm.setNum10Bills(this.ATMGetNum10Bills(atm) + num10bills);
        if (num10bills >= 0) {
            System.out.println("number of $10 bills added to the ATM: " + num10bills + "\n" +
                    "the number of $10 bills in the ATM is now : " + atm.getNum10Bills());
        }
        else {
            System.out.println("the number of $10 bills in the ATM is now: " + num10bills);
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
     * Set the number of 20$ bills
     * @param atm Instance of ATM machine
     * @param num20bills Number of $20 bills in ATM
     */
    public void ATMSetNum20Bills(ATM atm, int num20bills) {
        atm.setNum20Bills(this.ATMGetNum20Bills(atm) + num20bills);
        if (num20bills >= 0) {
            System.out.println("number of $20 bills added to the ATM: " + num20bills + "\n" +
                    "the number of $20 bills in the ATM is now : " + atm.getNum20Bills());
        }
        else {
            System.out.println("the number of $20 bills in the ATM is now: " + num20bills);
        }
    }

    /**
     * Get the number of 50$ bills
     * @param atm Instance of ATM machine
     */

    public int ATMGetNum50Bills(ATM atm) {
        System.out.println("the number of $50 bills in the ATM is: " + atm.getNum50Bills());
        return atm.getNum50Bills();
    }


    /**
     * Set the number of 50$ bills
     * @param atm Instance of ATM machine
     * @param num50bills Number of $50 bills in ATM
     */
    public void ATMSetNum50Bills(ATM atm, int num50bills) {
        atm.setNum50Bills(this.ATMGetNum50Bills(atm) + num50bills);
        if (num50bills >= 0) {
            System.out.println("number of $50 bills added to the ATM: " + num50bills + "\n" +
                    "the number of $50 bills in the ATM is now : " + (atm.getNum50Bills() + num50bills));
        }
        else {
            System.out.println("the number of $50 bills in the ATM is now: " + num50bills);
        }
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

    public void viewConsultationRecords(UserConsultant consultant){
        String history = "";

        try {
            BufferedReader read = new BufferedReader(new FileReader("phase2/txtfiles/UserAdviceHistory.txt"));
            String line = read.readLine();
            while (line != null) {
                System.out.println(line + "\n");
                line = read.readLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();}

//        for(String s : consultant.getUserAdviseHistory()){
//            history += s;
//        }
//        return history;
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

    public void populateInbox(String msg) {
        String s = "";
        s = numMessages + ". " + msg;
        numMessages++;
        inbox.add(s);

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("phase2/txtfiles/BankManagerInbox.txt"));
            writer.write(s + "\n");
            writer.close();
        } catch (Exception ex) {ex.printStackTrace();}

    }

    /**
     * View the messages in inbox from the consultant!
     */
   public String viewInbox() throws Exception{
        String s = "";
        if (inbox.isEmpty()){
            s = "You have no messages at the moment!";

        } else{
            s = inbox.toString();
        }

        return s;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }

    class UserIterator implements Iterator<User> {
        int i = 0;

        @Override
        public boolean hasNext() {
            if (i >= users.size()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public User next() {
            return users.get(i++);
        }

        @Override
        public void remove() {
            users.remove(--i);
        }
    }


    public static void main(String[] args) {
        BankManager bm = new BankManager("", "");
//        User user = new User("", "");
        UserConsultant UC = new UserConsultant("UCuser", "UCpass");
        User user1 = new User("a", "a");
        User user2 = new User("b", "b");
        User user3 = new User("c", "c");
        User user4 = new User("d", "d");
        User user5 = new User("e", "e");

        bm.createUser(UC.getUsername(), UC.getPassword());
        bm.createUser(user1.getUsername(), user1.getPassword());
        bm.createUser(user2.getUsername(), user2.getPassword());
        bm.createUser(user3.getUsername(), user3.getPassword());
        bm.createUser(user5.getUsername(), user5.getPassword());
//
        bm.deleteUser(user3);
        bm.createUser(user4.getUsername(), user4.getPassword());
        bm.deleteUser(user1);
////
//        bm.createNewAccount(10, "savings", user2);
//        bm.createNewAccount(20, "chequing", user2);
//        bm.createNewAccount(30, "chequing", user2);
//        bm.createNewAccount(40, "chequing", user2);
//        bm.createNewAccount(20, "credit", user5);
//        bm.createNewAccount(20, "savings", user4);
//        bm.createNewAccount(20, "chequing", user5);
//
        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            users = (ArrayList<User>) in.readObject();
            for (User obj : users) {
//                System.out.println(obj.getAccountsCreated());
                System.out.println(obj.getUsername());
            }

        } catch (Exception e) {e.printStackTrace();}

//        ArrayList<User> users = new ArrayList<>();
//        try {
//            FileInputStream file = new FileInputStream("phase2/txtfiles/Users.txt");
//            ObjectInputStream in = new ObjectInputStream(file);
//            users = (ArrayList<User>) in.readObject();
//            for (User obj : users) {
//                System.out.println(obj.getAccountsCreated());
//                System.out.println(obj.getUsername());
//            }
//
//        } catch (Exception e) {e.printStackTrace();}

    }
}

