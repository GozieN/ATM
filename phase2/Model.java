package phase2;

import phase2.FundHolders.ATM;
import phase2.FundHolders.Account;
import phase2.FundHolders.ChequingAccount;
import phase2.FundHolders.SavingsAccount;
import phase2.FundTransfers.Transactions;
import phase2.Operators.BankManager;
import phase2.Operators.User;

import java.io.File;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Model implements java.io.Serializable {
    private BankManager BM = new BankManager("BMuser", "BMpass");
    private ATM atm = new ATM();

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
        // options: 1. select/input user, e. logoff and exit
        System.out.println("enter 1 to input a user \n" +
                "enter e to logoff and exit");
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                System.out.println("input a user's username to select that user");
                Scanner userUsernameScan = new Scanner(System.in);
                while (userUsernameScan.hasNext()) {
                    String userUsernameIn = userUsernameScan.next();
                    // [Angela]
                    try {
                        User user = null;
                        FileInputStream file = new FileInputStream("phase2/Users.txt");
                        ObjectInputStream in = new ObjectInputStream(file);
                        user = (User)in.readObject();
                        in.close();
                        file.close();
                        if (user.getUsername().equals(userUsernameIn)) {
                            System.out.println("enter in the master access key to access this user");
                            Scanner masterAccessKeyScan = new Scanner(System.in);
                            while (masterAccessKeyScan.hasNext()) {
                                String masterAccessKeyIn = masterAccessKeyScan.nextLine();
                                if (masterAccessKeyIn.equals(BM.getMasterAccessKey())) {
                                    System.out.println("proceeding to user interactions menu");
                                    menuBM3(user);
                                } else {
                                    System.out.println("wrong master access key. try again");
                                }
                            }
                        } else {
                            System.out.println("that user doesn't exist. enter an existing username");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (optionIn.equals("e")) {
                System.out.println("logging off and returning to main menu");
                mainMenu();
            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 to input a user \n" +
                        "enter e to logoff and exit");
            }
        }
    }

    public void menuBM3(User user) {
        // options: 1. view user accounts summary, 2. perform transaction on user, 3. delete user, e. logoff and exit
        System.out.println("enter 1 to view this user's accounts summary \n" +
                "enter 2 to perform a transaction on this user \n" +
                "enter 3 to delete this user \n" +
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
                        System.out.println("returning to user interactions menu");
                        menuBM3(user);
                    } else if (optionIn2.equals("e")) {
                        System.out.println("logging off and returning to main menu");
                        mainMenu();
                    } else {
                        System.out.println("that is not an option \n" +
                                "enter b to go back \n" +
                                "enter e to logoff and exit");
                    }
                }
            } else if (optionIn.equals("2")) {
                System.out.println("proceeding to user transactions menu");
                menuBM4(user);
            } else if (optionIn.equals("3")) {
                BM.deleteUser(user);
                System.out.println("the user has been deleted");
                mainMenu();
            } else if (optionIn.equals("e")) {
                System.out.println("logging off and returning to main menu");
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
        // options:
//        System.out.println("Enter 'I' to View Info, Enter 'T' to Perform a Transaction, or Enter 'A' to Request a New Account");
//        Scanner menuOption = new Scanner(System.in);
//        String optionIn = menuOption.next();
//        ArrayList<Account> accounts = user.getAccountsCreated(); // NEED TO FIGURE OUT HOW TO STORE THE ACCOUNTS FROM BEFORE!!!!
//        if (optionIn.equals("I")) {
//            user.viewInfo();
//        } else if (optionIn.equals("T")) {
//            System.out.println("Enter '' to Withdraw from Account, Enter 'WM' to Withdraw from ATM, Enter 'DA' to Deposit into Account, Enter 'DM' to Deposit into ATM, Enter 'CA' to Deposit Cheque into ATM, Enter 'TA' to Transfer from Account to Account, Enter 'PB' to Pay Bill, A to create new Account: ");
//            Scanner transOption = new Scanner(System.in);
//            String transIn = transOption.next();
//            System.out.println("Enter the amount: \n");
//            Scanner amtOption = new Scanner(System.in);
//            double amt = Double.parseDouble(amtOption.next());
//            Transactions transactions = new Transactions(new ChequingAccount(100, "me", 1000, true)); // FIX THIS!!! JUST TESTING
//            if (transIn.equals("WA")) {
//                transactions.withdrawFromAccount(amt);
//            } else if (transIn.equals("WM")) {
//                transactions.withdrawFromATM(atm, (int) amt);
//            } else if (transIn.equals("DA")) {
//                transactions.depositToAccount(amt);
//            } else if (transIn.equals("DM")) {
//                transactions.depositIntoATM(atm, (int) amt);
//            } else if (transIn.equals("CA")) {
//                transactions.depositChequeToAccount(amt);
//            } else if (transIn.equals("TA")) { // SENDING TO AN ACC NEEDS TO BE ALTERED, TOO MUCH INFO NEEDED
//                System.out.println("Enter the Account Number of the Receiving Account: ");
//                Scanner accN = new Scanner(System.in);
//                int accNum = Integer.parseInt(accN.next());
//                System.out.println("Enter the Holder Name of the Receiving Account: ");
//                Scanner holderN = new Scanner(System.in);
//                String holder = holderN.next();
//                System.out.println("Enter the Balance of the Receiving Account: ");
//                Scanner balanceN = new Scanner(System.in);
//                double balance = Double.parseDouble(balanceN.next());
//                System.out.println("Enter the Type of the Receiving Account: ");
//                Scanner typeS = new Scanner(System.in);
//                String type = typeS.next();
//                Account receiverAccount = new SavingsAccount(accNum, holder, balance); // NEED TO CHANGE HOW TO CHANGE TRANSACTIONS
//                transactions.transfer((int) amt, receiverAccount);
//            } else if (transIn.equals("PB")) {
//                transactions.payBill(amt);
//            } else if (optionIn.equals("A")) {
//                user.addToAccountsCreated(new ChequingAccount(101, "me", 1000, true)); // NEED TO CHANGE AFTER TESTING
//            }
//        }
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
                    // angela, j
                    try {
                        User user = null;
                        FileInputStream file = new FileInputStream("phase2/Users.txt");
                        ObjectInputStream in = new ObjectInputStream(file);
                        user = (User)in.readObject();
                        in.close();
                        file.close();
                        if (user.getUsername().equals(usernameIn)) {
                            System.out.println("enter your password");
                            Scanner passwordScan = new Scanner(System.in);
                            while (passwordScan.hasNext()) {
                                String passwordIn = passwordScan.next();
                                if (user.getPassword().equals(passwordIn)) {
                                    System.out.println("proceeding to user interactions menu");
                                    menuU2(user);
                                } else {
                                    System.out.println("wrong password. enter your password");
                                }
                            }
                        } else {
                            System.out.println("that username does not exist. enter an existing username");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (optionIn.equals("2")) {
                System.out.println("enter a new username");
                Scanner newUsernameScan = new Scanner(System.in);
                while (newUsernameScan.hasNext()) {
                    String newUsernameIn = newUsernameScan.next();
                    // angela, j
                    try {
                        User user = null;
                        FileInputStream file = new FileInputStream("phase2/Users.txt");
                        ObjectInputStream in = new ObjectInputStream(file);
                        user = (User) in.readObject();
                        in.close();
                        file.close();
                        if (!(user.getUsername().equals(newUsernameIn))) {
                            System.out.println("enter a new password");
                            Scanner newPasswordScan = new Scanner(System.in);
                            String newPasswordIn = newPasswordScan.next();
                            BM.createUser(newUsernameIn, newPasswordIn);
                            System.out.println("your user creation request is being processed \n" +
                                    "returning to main menu");
                            mainMenu();
                        } else {
                            System.out.println("this username is not available \n" +
                                    "enter another new username");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
                        System.out.println("returning to user interactions menu");
                        menuU2(user);
                    } else if (optionIn2.equals("e")) {
                        System.out.println("logging off and returning to main menu");
                        mainMenu();
                    } else {
                        System.out.println("that is not an option \n" +
                                "enter b to go back \n" +
                                "enter e to logoff and exit");
                    }
                }
            } else if (optionIn.equals("2")) {
                System.out.println("proceeding to user transactions menu");
                menuU3(user);
            } else if (optionIn.equals("3")) {
                System.out.println("enter the account type that you want to create");
                Scanner newAccountTypeScan = new Scanner(System.in);
                String newAccountTypeIn = newAccountTypeScan.next();
                BM.createNewAccount(0, newAccountTypeIn, user);
            } else if (optionIn.equals("e")) {
                System.out.println("logging off and returning to main menu");
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

    public void menuU3 (User user) {
        // user transactions menu
        // options: 1. withdraw from ATM, 2. transfer money between accounts, 3. deposit to account (cash), 4. deposit cheque to account, b. back, e. logoff and exit
        System.out.println("enter 1 to withdraw cash from ATM \n" +
                "enter 2 to transfer money between accounts \n" +
                "enter 3 to deposit cash into an account \n" +
                "enter 4 to deposit a cheque into an account \n" +
                "enter 5 to pay a bill \n" +
                "enter b to go back \n" +
                "enter e to logoff and exit");
        int numUserAccounts = 0;
        ArrayList<Integer> numUserAccountsAL = new ArrayList<>();
        for (Account account : user.getAccountsCreated()) {
            numUserAccounts += 1;
            numUserAccountsAL.add(numUserAccounts);
        }
        Scanner optionScan = new Scanner(System.in);
        while (optionScan.hasNext()) {
            String optionIn = optionScan.next();
            if (optionIn.equals("1")) {
                System.out.println("select the specified number prefixing the account that you are withdrawing cash from \n" +
                        "or enter b to go back");
                for (int i = 1; i < numUserAccounts + 1; i++) {
                    System.out.println(i + ": " + user.getAccountsCreated().get(i).getAccountType() +
                            ' ' + user.getAccountsCreated().get(i).getAccountNum());
                }
                Scanner selectedNumPrefixAccScan = new Scanner(System.in);
                while (selectedNumPrefixAccScan.hasNext()) {
                    String selectedNumPrefixAccIn = selectedNumPrefixAccScan.next();
                    int selectedNumPrefixAcc = 0;
                    if (numUserAccountsAL.contains( Integer.valueOf(selectedNumPrefixAccIn)) ) {
                        selectedNumPrefixAcc = Integer.valueOf(selectedNumPrefixAccIn);
                        boolean flag = false;
                        while (flag == false) {
                            System.out.println("enter the amount that you would like to withdraw");
                            Scanner withdrawAmountScan = new Scanner(System.in);
                            int withdrawAmountIn = withdrawAmountScan.nextInt();
                            if (user.getAccountsCreated().get(selectedNumPrefixAcc - 1).getTransactionsInstance().withdrawFromATM(withdrawAmountIn) == true) {
                                flag = true;
                            }
                        }
                        System.out.println("returning to transactions menu");
                        menuU3(user);
                    } else if (selectedNumPrefixAccIn.equals("b")) {
                        System.out.println("returning to transactions menu");
                        menuU3(user);
                    } else {
                        System.out.println("that is not an option \n " +
                                "enter a valid option or enter b to return to the transactions menu");
                    }
                }
            } else if (optionIn.equals("2")) {
                System.out.println("enter 1 to transfer money between your own accounts \n" +
                        "enter 2 to transfer money from one of your accounts to another bank user's account \n" +
                        "enter b to go back");
                Scanner optionScan2 = new Scanner(System.in);
                while (optionScan2.hasNext()) {
                    String optionIn2 = optionScan2.next();
                    if (optionIn2.equals("1")) {
                        System.out.println("select the specified number prefixing the account that you are transferring money from");
                        for (int i = 1; i < numUserAccounts + 1; i++) {
                            System.out.println(i + ": " + user.getAccountsCreated().get(i).getAccountType() +
                                    ' ' + user.getAccountsCreated().get(i).getAccountNum());
                        }
                        Scanner selectedNumPrefixFromAccScan = new Scanner(System.in);
                        while (selectedNumPrefixFromAccScan.hasNext()) {
                            String selectedNumPrefixFromAccIn = selectedNumPrefixFromAccScan.next();
                            int selectedNumPrefixFromAcc = 0;
                            if (numUserAccountsAL.contains( Integer.valueOf(selectedNumPrefixFromAccIn)) ) {
                                selectedNumPrefixFromAcc = Integer.valueOf(selectedNumPrefixFromAccIn);
                                System.out.println("select the specified number prefixing the account that you are transferring money to");
                                for (int i = 1; i < numUserAccounts + 1; i++) {
                                    System.out.println(i + ": " + user.getAccountsCreated().get(i).getAccountType() +
                                            ' ' + user.getAccountsCreated().get(i).getAccountNum());
                                }
                                Scanner selectedNumPrefixToAccScan = new Scanner(System.in);
                                while (selectedNumPrefixToAccScan.hasNext()) {
                                    String selectedNumPrefixToAccIn = selectedNumPrefixToAccScan.next();
                                    int selectedNumPrefixToAcc = 0;
                                    if (numUserAccountsAL.contains( Integer.valueOf(selectedNumPrefixToAccIn)) ) {
                                        selectedNumPrefixToAcc = Integer.valueOf(selectedNumPrefixToAccIn);
                                        boolean flag = false;
                                        while (flag == false) {
                                            System.out.println("enter the amount of money you want to transfer");
                                            Scanner transferAmountScan = new Scanner(System.in);
                                            int transferAmountIn = transferAmountScan.nextInt();
                                            if (user.getAccountsCreated().get(selectedNumPrefixFromAcc - 1).getTransactionsInstance().transfer(transferAmountIn, user.getAccountsCreated().get(selectedNumPrefixToAcc - 1))) {
                                                flag = true;
                                            }
                                        }
                                        System.out.println("returning to transactions menu");
                                        menuU3(user);
                                    } else if (selectedNumPrefixFromAccIn.equals("b")) {
                                        System.out.println("returning to transactions menu");
                                        menuU3(user);
                                    } else {
                                        System.out.println("that is not an option \n " +
                                                "enter a valid option or enter b to return to the transactions menu");
                                    }
                                }
                            } else if (selectedNumPrefixFromAccIn.equals("b")) {
                                System.out.println("returning to transactions menu");
                                menuU3(user);
                            } else {
                                System.out.println("that is not an option \n " +
                                        "enter a valid option or enter b to return to the transactions menu");
                            }
                        }
                    } else if (optionIn2.equals("2")) {
                        System.out.println("select the specified number prefixing the account that you are transferring money from");
                        for (int i = 1; i < numUserAccounts + 1; i++) {
                            System.out.println(i + ": " + user.getAccountsCreated().get(i).getAccountType() +
                                    ' ' + user.getAccountsCreated().get(i).getAccountNum());
                        }
                        Scanner selectedNumPrefixFromAccScan = new Scanner(System.in);
                        while (selectedNumPrefixFromAccScan.hasNext()) {
                            String selectedNumPrefixFromAccIn = selectedNumPrefixFromAccScan.next();
                            int selectedNumPrefixFromAcc = 0;
                            if (numUserAccountsAL.contains( Integer.valueOf(selectedNumPrefixFromAccIn)) ) {
                                selectedNumPrefixFromAcc = Integer.valueOf(selectedNumPrefixFromAccIn);
                                System.out.println("enter the account number of the account that you are transferring money to");
                                Scanner toAccountScan = new Scanner(System.in);
                                while (toAccountScan.hasNext()) {
                                    String toAccountIn = toAccountScan.next();
                                    Account toAccount = null;
                                    try {
                                        FileInputStream input = new FileInputStream("phase2/accountDatabase.txt");
                                        ObjectInputStream in = new ObjectInputStream(input);
                                        toAccount = (Account) in.readObject();
                                        in.close();
                                        input.close();
                                        if (toAccount.getAccountNum() == Integer.valueOf(toAccountIn)) {
                                            boolean flag = false;
                                            while (flag == false) {
                                                System.out.println("enter the amount of money you want to transfer");
                                                Scanner transferAmountScan = new Scanner(System.in);
                                                int transferAmountIn = transferAmountScan.nextInt();
                                                if (user.getAccountsCreated().get(selectedNumPrefixFromAcc - 1).getTransactionsInstance().transfer(transferAmountIn, toAccount)) {
                                                    flag = true;
                                                }
                                            }
                                            System.out.println("returning to transactions menu");
                                            menuU3(user);
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    System.out.println("returning to transactions menu");
                                    menuU3(user);
                                }
                            } else if (selectedNumPrefixFromAccIn.equals("b")) {
                                System.out.println("returning to transactions menu");
                                menuU3(user);
                            } else {
                                System.out.println("that is not an option \n " +
                                        "enter a valid option or enter b to return to the transactions menu");
                            }
                        }
                    } else if (optionIn2.equals("b")) {
                        System.out.println("returning to transactions menu");
                        menuU3(user);
                    } else {
                        System.out.println("that is not an option \n " +
                                "enter 1 to transfer money between your own accounts \n" +
                                "enter 2 to transfer money from one of your accounts to another user's account \n" +
                                "enter b to go back");
                    }
                }
            } else if (optionIn.equals("3")) {

            } else if (optionIn.equals("4")) {

            } else if (optionIn.equals("5")) {

            } else if (optionIn.equals("b")) {

            } else if (optionIn.equals("e")) {

            } else {
                System.out.println("that is not an option \n" +
                        "enter 1 to withdraw cash from ATM \n" +
                        "enter 2 to transfer money between accounts \n" +
                        "enter 3 to deposit cash into an account \n" +
                        "enter 4 to deposit a cheque into an account \n" +
                        "enter b to go back \n" +
                        "enter e to logoff and exit");
            }
        }
    }

//    public void menuU3(User user) {
//        // options:
//        System.out.println("Enter 'I' to View Info, Enter 'T' to Perform a Transaction, or Enter 'A' to Request a New Account");
//        Scanner menuOption = new Scanner(System.in);
//        String optionIn = menuOption.next();
//        ArrayList<Account> accounts = user.getAccountsCreated(); // NEED TO FIGURE OUT HOW TO STORE THE ACCOUNTS FROM BEFORE!!!!
//        if (optionIn.equals("I")) {
//            user.viewInfo();
//        } else if (optionIn.equals("T")) {
//            System.out.println("Enter '' to Withdraw from Account, Enter 'WM' to Withdraw from ATM, Enter 'DA' to Deposit into Account, Enter 'DM' to Deposit into ATM, Enter 'CA' to Deposit Cheque into ATM, Enter 'TA' to Transfer from Account to Account, Enter 'PB' to Pay Bill, A to create new Account: ");
//            Scanner transOption = new Scanner(System.in);
//            String transIn = transOption.next();
//            System.out.println("Enter the amount: \n");
//            Scanner amtOption = new Scanner(System.in);
//            double amt = Double.parseDouble(amtOption.next());
//            Transactions transactions = new Transactions(new ChequingAccount(user, false));
//            if (transIn.equals("WA")) {
//                transactions.withdrawFromAccount(amt);
//            } else if (transIn.equals("WM")) {
//                transactions.withdrawFromATM((int) amt);
//            } else if (transIn.equals("DA")) {
//                transactions.depositToAccount(amt);
//            } else if (transIn.equals("DM")) {
//                transactions.depositIntoATM(atm, (int) amt);
//            } else if (transIn.equals("CA")) {
//                transactions.depositChequeToAccount(amt);
//            } else if (transIn.equals("TA")) { // SENDING TO AN ACC NEEDS TO BE ALTERED, TOO MUCH INFO NEEDED
//                System.out.println("Enter the Account Number of the Receiving Account: ");
//                Scanner accN = new Scanner(System.in);
//                int accNum = Integer.parseInt(accN.next());
//                System.out.println("Enter the Holder Name of the Receiving Account: ");
//                Scanner holderN = new Scanner(System.in);
//                String holder = holderN.next();
//                System.out.println("Enter the Balance of the Receiving Account: ");
//                Scanner balanceN = new Scanner(System.in);
//                double balance = Double.parseDouble(balanceN.next());
//                System.out.println("Enter the Type of the Receiving Account: ");
//                Scanner typeS = new Scanner(System.in);
//                String type = typeS.next();
//                //Account receiverAccount = new SavingsAccount(accNum, holder, balance); // NEED TO CHANGE HOW TO CHANGE TRANSACTIONS
//                //transactions.transfer((int) amt, receiverAccount);
//            } else if (transIn.equals("PB")) {
//                transactions.payBill(amt);
//            } else if (optionIn.equals("A")) {
//                //user.addToAccountsCreated(new ChequingAccount(101, "me", 1000, true)); // NEED TO CHANGE AFTER TESTING
//            }
//        }
//    }



    public static void updateDate(String date, File f) throws IOException{
        FileWriter fw = new FileWriter(f);
        fw.write(date);
        fw.close();
    }

    public static void main(String[] args) {

        System.out.println("current directory: " + System.getProperty("user.dir"));

        try {
            File f = new File("phase2/date.txt");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddmmyyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            updateDate(dtf.format(now), f);

        } catch (Exception e) {}

        Model model = new Model();
        model.mainMenu();
    }
}