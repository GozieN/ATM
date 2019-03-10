package phase1;

import phase1.FundHolders.ATM;
import phase1.FundHolders.Account;
import phase1.FundHolders.ChequingAccount;
import phase1.FundHolders.SavingsAccount;
import phase1.FundTransfers.Transactions;
import phase1.Operators.BankManager;
import phase1.Operators.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    private BankManager BM = new BankManager("BMuser", "BMpass");
    private ATM atm = new ATM();
    private ArrayList<String> userUsernames = new ArrayList<>();
    private ArrayList<String> userPasswords = new ArrayList<>();

    // model constructor
    public Model() {
    }

    // SET THE TRANSACTION ATM TO THIS ATM
    public ATM getAtm() {
        return atm;
    }

    public void mainMenu() {
        // options: 1. bankmanager, 2. normal user
        System.out.println("enter 1 for bankmanager \n" +
                "enter 2 for normal user");
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                menuBM1();
            } else if (optionIn.equals("2")) {
                menuU1();
            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 for bankmanager \n" +
                        "enter 2 for normal user");
            }
        }
    }

    public void menuBM1() {
        // options: 1. login, e. exit
        System.out.println("enter 1 to login \n" +
                "enter e to exit");
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                System.out.println("enter your username");
                Scanner usernameScan = new Scanner(System.in);
                while (usernameScan.hasNext()) {
                    String usernameIn = usernameScan.next();
                    if (usernameIn.equals(this.BM.getUsername())) {
                        System.out.println("enter your password");
                        Scanner passwordScan = new Scanner(System.in);
                        while (passwordScan.hasNext()) {
                            String passwordIn = passwordScan.next();
                            if (passwordIn.equals(this.BM.getPassword())) {
                                menuBM2();
                            } else {
                                System.out.println("wrong password. enter your password");
                            }
                        }
                    } else {
                        System.out.println("wrong username. enter your username");
                    }
                }
            } else if (optionIn.equals("e")) {
                System.out.println("returning to main menu");
                mainMenu();
            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 to login \n" +
                        "enter e to exit");
            }
        }
    }

    public void menuBM2() {
        // options: 1. user account access
        Scanner optionScan = new Scanner(System.in);
        System.out.println("enter 1 to input a user account\n" +
                "enter e to logoff and exit");
        String option  = optionScan.nextLine();
        if (option.equals("1")) {
            if (BM.getUsers() == null){
                System.out.println("There are no users of the bank");
            }else {
                Scanner optionScan2 = new Scanner(System.in);
                System.out.println("enter an existing username input a user's username from the following:");
                for (String username : this.userUsernames) {
                    System.out.println(username);
                }
                String option2 = optionScan2.nextLine();
                for (int i = 0; i < BM.getUsers().size(); i++) {
                    if (option2.equals(BM.getUsers().get(i).getUsername())) {
                        User inputUser = BM.getUsers().get(i);
                        Scanner optionScan3 = new Scanner(System.in);
                        System.out.println("enter in the master access key");
                        String option3 = optionScan3.nextLine();
                        if (option3 == BM.getMasterAccessKey()) {

                            this.menuBM3(inputUser);
                        } else {
                            System.out.println("Sorry, that key is incorrect!");
                        }
                    }
                }
                Scanner optionScan4 = new Scanner(System.in);
                System.out.println("It looks like that user does not exist, press b to go back!");
                String option4 = optionScan4.nextLine();
                if (option4.equals("b")) {
                    this.menuBM2();
                }
            }
            //menuBM3();
        } else if (option.equals("e")) {
            mainMenu();
        } else {
            System.out.println("that is not an option \n" +
                    "enter 1 to select a user account \n" +
                    "enter e to logoff and exit");
        }
    }

    public void menuBM3(User user){
        // options: 1. view user accounts summary, 2. perform transaction on user, e. logoff and exit
        System.out.println("enter 1 to view this user's accounts summary \n" +
                "enter 2 to perform a transaction on this user \n" +
                "enter e to logoff and exit");
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                user.viewInfo();
                // options: b. back, e. exit
                System.out.println("enter b to go back \n" +
                        "enter e to exit");
                Scanner optionScan2 = new Scanner(System.in);
                while (optionScan2.hasNext()) {
                    String optionIn2 = optionScan2.next();
                    if (optionIn2.equals("b")) {
                        menuBM3(user);
                    } else if (optionIn2.equals("e")) {
                        System.out.println("returning to main menu");
                        mainMenu();
                    } else {
                        System.out.println("that is not an option \n" +
                                "enter b to go back \n" +
                                "enter e to exit");
                    }
                }
            } else if (optionIn.equals("2")) {
                menuBM4(user);
            } else if (optionIn.equals("e")) {
                System.out.println("returning to main menu");
                mainMenu();
            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 to view this user's accounts summary \n" +
                        "enter 2 to perform a transaction on this user \n" +
                        "enter e to logoff and exit");
            }
        }
    }

    public void menuBM4(User user) {

    }

    public void menuU1() {
        // options: 1. login, 2. request creation of new user, e. exit
        System.out.println("enter 1 to login to existing user \n" +
                "enter 2 to request creation of new user \n" +
                "enter e to exit");
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                System.out.println("enter your username");
                Scanner usernameScan = new Scanner(System.in);
                while (usernameScan.hasNext()) {
                    String usernameIn = usernameScan.next();
                    int index = -1;
                    for (String username : this.userUsernames) {
                        index += 1;
                        if (username.equals(usernameIn)) {
                            System.out.println("enter your password");
                            Scanner passwordScan = new Scanner(System.in);
                            while (passwordScan.hasNext()) {
                                String passwordIn = passwordScan.next();
                                User user = null;
                                if (passwordIn.equals(this.userPasswords.get(index))) {
                                    for (User u : BM.getUsers()) {
                                        if (user.getUsername().equals(usernameIn)) {
                                            user = u;
                                        }
                                    }
                                    menuU2(user);
                                } else {
                                    System.out.println("wrong password. enter your password");
                                }
                            }
                        } else {
                            System.out.println("wrong username. enter your username");
                        }
                    }
                }
            } else if (optionIn.equals("2")) {
                System.out.println("enter a new username");
                Scanner newUsernameScan = new Scanner(System.in);
                while (newUsernameScan.hasNext()) {
                    String newUsernameIn = newUsernameScan.next();
                    if (!(this.userUsernames.contains(newUsernameIn))) {
                        this.userUsernames.add(newUsernameIn);
                        System.out.println("enter a new password");
                        Scanner newPasswordScan = new Scanner(System.in);
                        String newPasswordIn = newPasswordScan.next();
                        this.userPasswords.add(newPasswordIn);
                        BM.createUser(newUsernameIn, newPasswordIn);
                        BM.getUsers().get(BM.getUsers().size()).setBM(BM);
                        System.out.println("your user creation request is being processed \n" +
                                "returning to main menu");
                        mainMenu();
                    } else {
                        System.out.println("this username is not available \n" +
                                "enter another new username");
                    }
                }
            } else if (optionIn.equals("e")) {
                System.out.println("returning to main menu");
                mainMenu();
            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 to login to existing user \n" +
                        "enter 2 to request creation of new user \n" +
                        "enter e to exit");
            }
        }
    }

    public void menuU2(User user) {
        // options: 1. view accounts summary, 2. perform transaction, 3. request creation of new account, e. logoff and exit
        System.out.println("enter 1 to view your accounts summary \n" +
                "enter 2 to perform a transaction \n" +
                "enter 3 to request creation of new account \n" +
                "enter e to logoff and exit");
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                user.viewInfo();
                // options: b. back, e. logoff and exit
                System.out.println("enter b to go back \n" +
                        "enter e to logoff and exit");
                Scanner optionScan2 = new Scanner(System.in);
                while (optionScan2.hasNext()) {
                    String optionIn2 = optionScan2.next();
                    if (optionIn2.equals("b")) {
                        menuU2(user);
                    } else if (optionIn2.equals("e")) {
                        System.out.println("returning to main menu");
                        mainMenu();
                    } else {
                        System.out.println("that is not an option \n" +
                                "enter b to go back \n" +
                                "enter e to logoff and exit");
                    }
                }
            } else if (optionIn.equals("2")) {
                menuU3(user);
            } else if (optionIn.equals("3")) {
                // request creation of new account
                System.out.println("enter the account type that you want to create");
                Scanner newAccountTypeScan = new Scanner(System.in);
                String newAccountTypeIn = newAccountTypeScan.next();
                BM.createNewAccount(0, newAccountTypeIn, user);
            } else if (optionIn.equals("e")) {
                System.out.println("returning to main menu");
                mainMenu();
            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 to view your accounts summary \n" +
                        "enter 2 to perform a transaction \n" +
                        "enter 3 to request creation of new account \n" +
                        "enter e to logoff and exit");
            }
        }
    }

    public void menuU3(User user) {
        // options:
        System.out.println("Enter 'I' to View Info, Enter 'T' to Perform a Transaction, or Enter 'A' to Request a New Account");
        Scanner menuOption = new Scanner(System.in);
        String optionIn = menuOption.next();
        ArrayList<Account> accounts = user.getAccountsCreated(); // NEED TO FIGURE OUT HOW TO STORE THE ACCOUNTS FROM BEFORE!!!!
        if (optionIn.equals("I")) {
            user.viewInfo();
        } else if (optionIn.equals("T")) {
            System.out.println("Enter '' to Withdraw from Account, Enter 'WM' to Withdraw from ATM, Enter 'DA' to Deposit into Account, Enter 'DM' to Deposit into ATM, Enter 'CA' to Deposit Cheque into ATM, Enter 'TA' to Transfer from Account to Account, Enter 'PB' to Pay Bill, A to create new Account: ");
            Scanner transOption = new Scanner(System.in);
            String transIn = transOption.next();
            System.out.println("Enter the amount: \n");
            Scanner amtOption = new Scanner(System.in);
            double amt = Double.parseDouble(amtOption.next());
            Transactions transactions = new Transactions(new ChequingAccount(100, "me", 1000, true)); // FIX THIS!!! JUST TESTING
            if (transIn.equals("WA")) {
                transactions.withdrawFromAccount(amt);
            } else if (transIn.equals("WM")) {
                transactions.withdrawFromATM(atm, (int) amt);
            } else if (transIn.equals("DA")) {
                transactions.depositToAccount(amt);
            } else if (transIn.equals("DM")) {
                transactions.depositIntoATM(atm, (int) amt);
            } else if (transIn.equals("CA")) {
                transactions.depositChequeToAccount(amt);
            } else if (transIn.equals("TA")) { // SENDING TO AN ACC NEEDS TO BE ALTERED, TOO MUCH INFO NEEDED
                System.out.println("Enter the Account Number of the Receiving Account: ");
                Scanner accN = new Scanner(System.in);
                int accNum = Integer.parseInt(accN.next());
                System.out.println("Enter the Holder Name of the Receiving Account: ");
                Scanner holderN = new Scanner(System.in);
                String holder = holderN.next();
                System.out.println("Enter the Balance of the Receiving Account: ");
                Scanner balanceN = new Scanner(System.in);
                double balance = Double.parseDouble(balanceN.next());
                System.out.println("Enter the Type of the Receiving Account: ");
                Scanner typeS = new Scanner(System.in);
                String type = typeS.next();
                Account receiverAccount = new SavingsAccount(accNum, holder, balance); // NEED TO CHANGE HOW TO CHANGE TRANSACTIONS
                transactions.transfer((int) amt, receiverAccount);
            } else if (transIn.equals("PB")) {
                transactions.payBill(amt);
            } else if (optionIn.equals("A")) {
                user.addToAccountsCreated(new ChequingAccount(101, "me", 1000, true)); // NEED TO CHANGE AFTER TESTING
            }
        }
    }


    public static void updateDate(String date, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write(date);
        fw.close();
    }

    public static void main(String[] args) {

        try {
            File f = new File("./src/date.txt");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            updateDate(dtf.format(now), f);

        } catch (IOException e) {}

        Model model = new Model();
        model.mainMenu();

    }
}